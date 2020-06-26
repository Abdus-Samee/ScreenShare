package sample;

import javafx.application.Platform;
import javafx.scene.image.Image;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class conn {
    private Socket s;
    private ObjectInputStream is;
    private Main m;
    private Controller controller;

    public conn(Socket s, Controller controller) throws Exception {
        this.s = s;
        ObjectInputStream is = new ObjectInputStream(s.getInputStream());
        this.controller = controller;
        connTh cth = new connTh(s, is, controller);
        cth.start();
    }

    public void setMain(Main m) { this.m = m; }

    public void setController(Controller controller) { this.controller = controller; }

    public class connTh extends Thread{
        Socket s;
        private ObjectInputStream is;
        private Controller controller;

        public connTh(Socket s, ObjectInputStream is, Controller controller) throws Exception{
            this.s = s;
            this.is = is;
            this.controller = controller;
        }

        @Override
        public void run(){
            while(true){
                try{
                    imageData id = (imageData)is.readObject();
                    Image newImage = id.getWi();
                    controller.setIv(newImage);
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        }
    }
}