package com.api.filtro.filtro;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.springframework.stereotype.Service;

@Service
public class ImageProcessingService {

  public String getOriginalImage() {
    return "Imagem original";
  }

  public String processImage(String imageData) {
    return "Imagem processada";
  }

  public BufferedImage processNegativeRGB(BufferedImage image) {
    int width = image.getWidth();
    int height = image.getHeight();

    BufferedImage negativeImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Color color = new Color(image.getRGB(x, y));
        int red = 255 - color.getRed();
        int green = 255 - color.getGreen();
        int blue = 255 - color.getBlue();
        Color negativeColor = new Color(red, green, blue);
        negativeImage.setRGB(x, y, negativeColor.getRGB());
      }
    }

    return negativeImage;
  }

  public BufferedImage adjustTone(BufferedImage image, int aumento, int pixel) {
    int width = image.getWidth();
    int height = image.getHeight();

    BufferedImage adjustedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int rgb = image.getRGB(x, y);
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;

        red = adjustTone(red, aumento);
        green = adjustTone(green, aumento);
        blue = adjustTone(blue, aumento);

        int adjustedRGB = (red << 16) | (green << 8) | blue;

        adjustedImage.setRGB(x, y, adjustedRGB);
      }
    }

    return adjustedImage;
  }

  private int adjustTone(int pixel, int aumento) {
    if (aumento + pixel < 0) {
      return 0;
    } else if (aumento + pixel > 255) {
      return 255;
    } else {
      return aumento + pixel;
    }
  }
}
