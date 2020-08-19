package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller{
    private network net;
    @FXML ImageView iv_screen;
    @FXML ImageView iv_webcam;
    @FXML Label lbl;


    public void setNet(network net) { this.net = net; }

    public void setImage(Image image) { iv_webcam.setImage(image);}

    public void setScreen(Image i) { iv_screen.setImage(i);}

    public void setLbl(String s) { lbl.setText(s); }

    @FXML
    public void click(MouseEvent mouseEvent){
        double width = Screen.getPrimary().getBounds().getWidth();
        double height = Screen.getPrimary().getBounds().getHeight();
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();

        double targetX = (x/762)*width;
        double targetY = (y/428)*height;

        lbl.setText(String.valueOf(targetX));

        robo id = new robo();
        id.setAbscissa(targetX);
        id.setOrdinate(targetY);

        net.sendClick(id);

        mouseEvent.consume();
    }
}
