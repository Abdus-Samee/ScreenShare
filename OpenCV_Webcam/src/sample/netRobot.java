package sample;

import javafx.scene.input.MouseButton;
import javafx.scene.robot.Robot;

import java.io.ObjectInputStream;

public class netRobot {
    private Main main;
    private FXHelloCVController controller;

    public netRobot(ObjectInputStream is, FXHelloCVController controller){
        this.controller = controller;
        new connRobot(is, controller).start();
    }

    public void setMain(Main main) { this.main = main; }

    private class connRobot extends Thread{
        private ObjectInputStream is;
        private FXHelloCVController controller;

        public connRobot(ObjectInputStream is, FXHelloCVController controller){
            this.is = is;
            this.controller = controller;
        }

        public void run(){
            System.out.println("started robonet...");
            while(true){
                try{
                    robo id = (robo) is.readObject();
                    controller.setLbl("got abs and ord...");
                    main.move(id.getAbscissa(), id.getOrdinate());
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        }
    }
}
