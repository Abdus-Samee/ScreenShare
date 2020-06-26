package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class imageData implements Serializable {
    private transient Image wi;

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        wi = SwingFXUtils.toFXImage(ImageIO.read(s), null);
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        ImageIO.write(SwingFXUtils.fromFXImage(wi, null), "png", s);
        s.flush();
    }

    public void setWi(WritableImage wi) { this.wi = wi; }


    public Image getWi() { return wi; }
}
