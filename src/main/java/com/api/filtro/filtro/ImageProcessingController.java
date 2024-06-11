package com.api.filtro.filtro;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/api/images")
public class ImageProcessingController {

  private final ImageProcessingService imageProcessingService;

  public ImageProcessingController(ImageProcessingService imageProcessingService) {
    this.imageProcessingService = imageProcessingService;
  }

  @PostMapping(value = "/process-negative-rgb", produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
  public byte[] processNegativeRGB(@RequestBody byte[] imageData) throws IOException {
    BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageData));

    BufferedImage processedImage = imageProcessingService.processNegativeRGB(image);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ImageIO.write(processedImage, "png", outputStream);

    return outputStream.toByteArray();
  }

}
