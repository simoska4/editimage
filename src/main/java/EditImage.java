import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.WritableRaster;

/**
 * The Edit-Image class implements methods
 * to edit a BufferedImage.
 *
 * @author  Simone Sapienza <simoska4@gmail.com>
 * @version 1.0
 */
public class EditImage {

    private static int threshold = 180;

    /**
     * Class constructor
     * @param threshold
     */
    public EditImage(int threshold) {
        this.threshold = threshold;
    }


    /**
     * Set the threshold.
     * @param threshold
     */
    public static void setThreshold(int threshold) {
        EditImage.threshold = threshold;
    }


    /**
     * This is the method that rotates a BufferedImage
     * of a given angle.
     * @param image input BufferedImage
     * @param angle rotation angle
     * @return rotated BufferedImage
     */
    public static BufferedImage rotateByDegrees(BufferedImage image,
                                                     double angle) {
        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = image.getWidth();
        int h = image.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);
        int x = w / 2;
        int y = h / 2;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, rotated.getWidth(), rotated.getHeight());
        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(image, 0, 0, null);
        g2d.drawRect(0, 0, newWidth - 1, newHeight - 1);
        g2d.dispose();
        return rotated;
    }


    /**
     * This is the method that resizes a BufferedImage.
     * @param image input BufferedImage
     * @param targetWidth target width
     * @param targetHeight target height
     * @return resized BufferedImage
     */
    public static BufferedImage getScaled(BufferedImage image,
                                          int targetWidth,
                                          int targetHeight) {
        int type = (image.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage scaled = image;
        int w = image.getWidth();
        int h = image.getHeight();

        do {
            if (w > targetWidth) {
                w /= 1.2;
                if (w < targetWidth)
                    w = targetWidth;
            }
            if (h > targetHeight) {
                h /= 2;
                if (h < targetHeight)
                    h = targetHeight;
            }
            BufferedImage tmp = new BufferedImage(w, h, type);
            Graphics2D g2 = tmp.createGraphics();
            g2.drawImage(scaled, 0, 0, w, h, null);
            g2.dispose();
            scaled = tmp;
        } while (w != targetWidth || h != targetHeight);

        return scaled;
    }


    /**
     * This method converts a BufferedImage to
     * grayscale format.
     * @param image input BufferedImage
     * @return converted BufferedImage
     */
    public static BufferedImage convertGrayScale(BufferedImage image){
        BufferedImage out = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        ColorConvertOp convertOp = new ColorConvertOp(image.getColorModel().getColorSpace(), ColorSpace.getInstance(ColorSpace.CS_GRAY),  null);
        convertOp.filter(image, out);
        return out;
    }


    /**
     * This method converts a BufferedImage
     * to a binary format (B/W)
     * @param image input BufferedImage
     * @param threshold
     * @return converted BufferedImage
     */
    public static BufferedImage thresholdImage(BufferedImage image,
                                               int threshold) {
        BufferedImage imageGray = convertGrayScale(image);
        BufferedImage result = new BufferedImage(imageGray.getWidth(), imageGray.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        result.getGraphics().drawImage(imageGray, 0, 0, null);
        WritableRaster raster = result.getRaster();
        int[] pixels = new int[imageGray.getWidth()];
        for (int y = 0; y < imageGray.getHeight(); y++) {
            raster.getPixels(0, y, imageGray.getWidth(), 1, pixels);
            for (int i = 0; i < pixels.length; i++) {
                if (pixels[i] < threshold) pixels[i] = 0;
                else pixels[i] = 255;
            }
            raster.setPixels(0, y, imageGray.getWidth(), 1, pixels);
        }
        return result;
    }

    /**
     * This method converts a BufferedImage
     * to a binary format (B/W).
     * @param image input BufferedImage
     * @return converted BufferedImage
     */
    public static BufferedImage thresholdImage(BufferedImage image) {
        return thresholdImage(image, threshold);
    }


    /**
     * This method obtains the array containing
     * the pixels of a given BufferedImage.
     * @param image
     * @return
     */
    public static int[] getRGBData(BufferedImage image) {
        return image.getRGB(0,0, image.getWidth(), image.getHeight(),null, 0, image.getWidth());
    }
}
