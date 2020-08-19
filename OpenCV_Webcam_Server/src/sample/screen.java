package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class screen implements Serializable {
    private transient Image image;
    private String type;
    private double abscissa;
    private double ordinate;

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        image = SwingFXUtils.toFXImage(ImageIO.read(s), null);
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", s);
        s.flush();
    }

    public void setAbscissa(double x) { abscissa = x; }

    public void setOrdinate(double y) { ordinate = y; }

    public double getAbscissa() { return abscissa; }

    public double getOrdinate() { return ordinate; }

    public void setType(String type) { this.type = type; }

    public String getType() { return type; }

    public void set(Image image) { this.image = image; }

    public Image get() { return  this.image; }
}
