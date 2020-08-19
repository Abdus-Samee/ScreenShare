package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class imageData implements Serializable {
    private transient Image image_webcam;
    private String type;

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        image_webcam = SwingFXUtils.toFXImage(ImageIO.read(s), null);
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        ImageIO.write(SwingFXUtils.fromFXImage(image_webcam, null), "png", s);
        s.flush();
    }

    public void setType(String type) { this.type = type; }

    public String getType() { return type; }

    public void set(Image image) { this.image_webcam = image; }

    public Image get() { return  this.image_webcam; }
}
