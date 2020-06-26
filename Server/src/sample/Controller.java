package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller {
    private Main m;
    private conn c;
    @FXML private ImageView iv;
    @FXML private Label lbl;

    public void setIv(Image wi) { iv.setImage(wi); }

    public Image getIv() { return iv.getImage(); }

    public void setM(Main m) { this.m = m; }

    public void setC(conn c) { this.c = c; }
}
