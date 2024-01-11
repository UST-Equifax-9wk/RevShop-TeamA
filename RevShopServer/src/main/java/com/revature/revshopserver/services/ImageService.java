package com.revature.revshopserver.services;

import com.revature.revshopserver.entities.ImageData;
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

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = imageRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if(imageData !=null)
            return "File uploaded with name " + file.getOriginalFilename();
        return null;
    }
    public byte[] downloadImage(String fileName) throws ObjectNotFoundException {
        Optional<ImageData> imageData = imageRepository.findByName(fileName);
        if (imageData.isEmpty())
            throw new ObjectNotFoundException("Cannot find an image with that name!");
        return ImageUtils.decompressImage(imageData.get().getImageData());
    }
    //any changes test
}
