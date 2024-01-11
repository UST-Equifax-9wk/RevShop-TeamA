package com.revature.revshopserver.controllers;

import com.revature.revshopserver.entities.ImageData;
import com.revature.revshopserver.exceptions.ObjectNotFoundException;
import com.revature.revshopserver.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping(path = "/images")
@RestController
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/newImage")
    public ResponseEntity<?> uploadImage(@RequestParam("imageFile")MultipartFile file) throws IOException {

        String uploadImage = imageService.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
    }

    @GetMapping("/{fileName}")
    public ImageData downloadImage(@PathVariable String fileName) throws ObjectNotFoundException {
        System.out.println("getting that image from service");
        return imageService.downloadImageWithName(fileName);
        //return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
    }
    @GetMapping("/id/{fileId}")
    public ImageData downloadImage(@PathVariable Integer fileId) throws ObjectNotFoundException {
        return imageService.downloadImageWithId(fileId);
        //return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> objectNotFoundExceptionHandler(ObjectNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
