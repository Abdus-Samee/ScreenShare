package sample;

import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.WritableImage;
import javafx.scene.robot.Robot;
import javafx.stage.Screen;

public class netScreen {
    public netScreen(){
        WritableImage wi = new WritableImage(300,300);

        new AnimationTimer(){
            int c = 1;
            final Robot robot = new Robot();
            final Rectangle2D bounds = Screen.getPrimary().getBounds();
            WritableImage i =robot.getScreenCapture(wi,bounds);
            screen id = new screen();

            @Override
            public void handle(long now) {
                if(c == 1){
                    try{
                        c++;
                        id.set(i);
                        Utils.os_screen.writeObject(id);
                        Utils.os_screen.reset();
                    }catch(Exception e){
                        System.out.println(e);
                    }
                }
                else{
                    try{
                        WritableImage oldImage = (WritableImage) id.get();
                        WritableImage newImage = robot.getScreenCapture(wi,bounds);
                        if(oldImage != newImage){
                            id.set(newImage);
                            Utils.os_screen.writeObject(id);
                            Utils.os_screen.reset();
                        }
                        else{
                            System.out.println("Same image");
                        }
                    }catch(Exception e){
                        System.out.println(e);
                    }
                }
            }
        }.start();
    }
}
