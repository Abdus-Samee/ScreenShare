package sample;

import javafx.scene.control.Control;
import javafx.scene.image.Image;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class network {
    private ObjectOutputStream os;
    private Controller controller;

    public network(Socket s, ObjectInputStream is, ObjectOutputStream os, Controller controller, Socket s_screen){
        this.os = os;
        this.controller = controller;
        new conn(s, is, controller).start();
        new connScreen(s_screen, controller).start();
    }

    public void sendClick(robo id){
        try{
            controller.setLbl("sending...");
            os.writeObject(id);
            os.reset();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    //thread to handle webcam images in the background
    private class conn extends Thread{
        private Socket s;
        private ObjectInputStream is;
        private Controller controller;

        public conn(Socket s, ObjectInputStream is, Controller controller){
            this.s = s;
            this.is = is;
            this.controller = controller;
        }

        @Override
        public void run(){
            while(true){
                try{
                    imageData id = (imageData) is.readObject();
                    if(id.getType().equals("webcam")) controller.setImage(id.get());
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        }
    }

    //thread to handle screen sharing images in the background
    private class connScreen extends Thread{
        private ServerSocket ss_screen;
        private Socket s_screen;
        private ObjectInputStream is_screen;
        private Controller controller_screen;

        public connScreen(Socket s, Controller controller){
            try{
                this.is_screen = new ObjectInputStream(s.getInputStream());
                this.controller_screen = controller;
            }catch(Exception e){
                System.out.println(e);
            }
        }

        @Override
        public void run(){
            while(true){
                try{
                    screen id = (screen) is_screen.readObject();
                    controller_screen.setScreen(id.get());
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        }
    }
}
