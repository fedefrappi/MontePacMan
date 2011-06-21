package core.visuals;

import core.Constants;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class ImageLoader implements Constants {


    public static BufferedImage[] loadImages() {
        BufferedImage[] images = new BufferedImage[4];
        images[0] = getImage(mazea);
        images[1] = getImage(mazeb);
        images[2] = getImage(mazec);
        images[3] = getImage(mazed);
        return images;
    }


    public static BufferedImage getImage(String file) {
        BufferedImage image = null;
        try {


            image = ImageIO.read(new File(imagepath + file));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(file);
        }
        return image;
    }

}
