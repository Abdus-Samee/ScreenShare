package sample;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.*;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Utils {
    private static Socket s;
    private static ObjectOutputStream os;
    static ObjectOutputStream os_screen;

    public static void setNet(Socket s, ObjectOutputStream os, ObjectOutputStream os_screen){
        Utils.s = s;
        Utils.os = os;
        Utils.os_screen = os_screen;
    }

    public static Image mat2Image(Mat frame)
    {
        try
        {
            MatOfByte byteMat = new MatOfByte();
            Imgcodecs.imencode(".png", frame, byteMat);
            Image image = new Image(new ByteArrayInputStream(byteMat.toArray()));//added ...
            imageData id = new imageData();
            id.setType("webcam");
            id.set(image);
            os.writeObject(id);
            os.reset();
            return image;
            //return SwingFXUtils.toFXImage(matToBufferedImage(frame), null);
        }
        catch (Exception e)
        {
            System.err.println("Cannot convert the Mat obejct: " + e);
            return null;
        }
    }

    /**
     * Generic method for putting element running on a non-JavaFX thread on the
     * JavaFX thread, to properly update the UI
     *
     * @param property
     *            a {@link ObjectProperty}
     * @param value
     *            the value to set for the given {@link ObjectProperty}
     */
    public static <T> void onFXThread(final ObjectProperty<T> property, final T value)
    {
        Platform.runLater(() -> {
            property.set(value);
        });
    }

//    private static BufferedImage matToBufferedImage(Mat original)
//    {
//        // init
//        BufferedImage image = null;
//        int width = original.wid  th(), height = original.height(), channels = original.channels();
//        byte[] sourcePixels = new byte[width * height * channels];
//        original.get(0, 0, sourcePixels);
//
//        if (original.channels() > 1)
//        {
//            image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
//        }
//        else
//        {
//            image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
//        }
//        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
//        System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);
//
//        return image;
//    }
}
