package com.revature.revshopserver.services;

import com.revature.revshopserver.entities.ImageData;
import com.revature.revshopserver.entities.Product;
import com.revature.revshopserver.repositories.ImageRepository;
import com.revature.revshopserver.utils.ImageUtils;
import com.revature.revshopserver.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@Transactional(Transactional.TxType.REQUIRED)
public class ImageService {
    private final ImageRepository imageRepository;
    private final ProductService productService;

    @Autowired
    public ImageService(ImageRepository imageRepository, ProductService productService) {
        this.imageRepository = imageRepository;
        this.productService = productService;
    }

    public String uploadImage(MultipartFile file, Integer productId) throws IOException, ObjectNotFoundException {
        Product product = productService.getById(productId);
        ImageData imageData = imageRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .product(product)
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if(imageData !=null)
            return "File uploaded with name " + file.getOriginalFilename();
        return null;
    }
    public ImageData downloadImageWithName(String fileName) throws ObjectNotFoundException {
        //System.out.println("time to get that image from the repository");
        Optional<ImageData> imageData = imageRepository.findByName(fileName);
        if (imageData.isEmpty())
            throw new ObjectNotFoundException("Cannot find an image with that name!");
        //System.out.println("got the image, decompressing");
        return ImageData.builder()
                .name(imageData.get().getName())
                .type(imageData.get().getType())
                .imageData(ImageUtils.decompressImage(imageData.get().getImageData())).build();
        //System.out.println("retruning image all the way up!");
        //System.out.println();
        //return imageData2;
    }
    public ImageData downloadImageWithId(Integer imgId) throws ObjectNotFoundException {
        Optional<ImageData> imageData = imageRepository.findById(imgId);
        if (imageData.isEmpty())
            throw new ObjectNotFoundException("Cannot find an image with that Id!");
        return ImageData.builder()
                .name(imageData.get().getName())
                .type(imageData.get().getType())
                .imageData(ImageUtils.decompressImage(imageData.get().getImageData())).build();
        //ImageUtils.decompressImage(imageData.get().getImageData());
        //return imageData2;
    }
    //any changes test
}
