package sample;

import javafx.animation.AnimationTimer;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.WritableImage;
import javafx.scene.robot.Robot;
import javafx.stage.Screen;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class network {
    private Socket s;
    private ObjectOutputStream os;

    public network(Socket s, ObjectOutputStream os){
        this.s = s;
        this.os = os;

        WritableImage wi = new WritableImage(300,300);
        final Robot robot = new Robot();
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        WritableImage i =robot.getScreenCapture(wi,bounds);

        new AnimationTimer() {
            int c = 1;
//            final Robot robot = new Robot();
//            final Rectangle2D bounds = Screen.getPrimary().getBounds();
//            WritableImage i =robot.getScreenCapture(wi,bounds);
            imageData id = new imageData();

            @Override
            public void handle(long now) {
                if(c == 1){
                    try{
                        c++;
                        id.setWi(i);
                        os.writeObject(id);
                        os.reset();
                    }catch(Exception e){
                        System.out.println(e);
                    }
                }
                else{
                    try{
                        WritableImage oldImage = (WritableImage) id.getWi();
                        WritableImage newImage = robot.getScreenCapture(oldImage,bounds);
                        if(oldImage != newImage){
                            id.setWi(newImage);
                            os.writeObject(id);
                            os.reset();
                        }
                    }catch(Exception e){
                        System.out.println(e);
                    }
                }
            }
        }.start();
    }
}
