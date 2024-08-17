package com.skillbox;

import org.imgscalr.Scalr;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageResizerTask implements Runnable {
    private File[] files;
    private String dstFolder;

    public ImageResizerTask(File[] files, String dstFolder) {
        this.files = files;
        this.dstFolder = dstFolder;
    }

    @Override
    public void run() {
        for (File file : files) {
            try {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }

                // Resize image using Imgscalr
                BufferedImage resizedImage = Scalr.resize(image, Scalr.Method.QUALITY, 300);

                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(resizedImage, "jpg", newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
