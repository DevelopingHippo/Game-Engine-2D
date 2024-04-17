package engine.helpers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class Utils {

    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        String token = uuid.toString();
        System.out.println("Generate UUID: " + token);
        return token;
    }


    public static BufferedImage setupImage(String imagePath, int width, int height) {
        BufferedImage image = null;
        try{
            image = ImageIO.read((Objects.requireNonNull(Utils.class.getResourceAsStream(imagePath + ".png"))));
            image = scaleImage(image, width, height);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }

    public static BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();
        return scaledImage;
    }

}
