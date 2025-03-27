package com.challenge.ms.animal;

import com.challenge.ms.animal.service.PhotoService;
import com.challenge.ms.animal.service.RESTService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import java.io.IOException;


@SpringBootApplication
@RestController

public class TamChallengeApplication {

    private static final Logger log = LoggerFactory.getLogger(TamChallengeApplication.class);
    private final PhotoService photoService;
    public TamChallengeApplication(PhotoService photoService) {
        this.photoService = photoService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TamChallengeApplication.class, args);
    }


    @RequestMapping(path = "/fetchsave/{animalType}/{numeberOfPictures}",method = RequestMethod.GET)
    public ResponseEntity<String> fetchSave(@PathVariable String animalType, @PathVariable int numeberOfPictures) {

        RESTService rest = new RESTService();
        BufferedImage img = null;

        String uri = switch (animalType) {
            case "cat" -> "https://cataas.com/cat";
            case "bear" -> "https://placebear.com/200/300";
            case "dog" -> "https://place.dog/300/200";
            default -> null;
        };


        for(int i=0; i<numeberOfPictures; i++) {
            try {
                img = rest.getPhoto(uri);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            try {
                ImageIO.write(img, "jpeg", baos);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            byte[] imgBytes = baos.toByteArray();
            photoService.saveImage(animalType,"jpeg",imgBytes);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setAccessControlAllowOrigin("*");
        String responsetxt=numeberOfPictures + " "+animalType + " images captured successfully";
        return new ResponseEntity<>(responsetxt, headers, HttpStatus.OK);
    }

    @GetMapping("/fetchLastPhoto")
    public ResponseEntity<byte[]> fetchLastPhoto(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccessControlAllowOrigin("*");
        headers.setContentType(MediaType.valueOf("image/jpeg"));
        // byte[] imgBytes = photoService.getById(12).getData();
        byte[] imgBytes = photoService.getLastPhoto();
        ContentDisposition build = ContentDisposition.
                builder("inline").
                filename("animal.jpeg").
                build();
        headers.setContentDisposition(build);
        return new ResponseEntity<>(imgBytes, headers, HttpStatus.OK);

    }
}
