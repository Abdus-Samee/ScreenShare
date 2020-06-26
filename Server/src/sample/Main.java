package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import java.net.ServerSocket;
import java.net.Socket;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        Controller controller = loader.getController();
        controller.setM(this);

        try{
            ServerSocket ss = new ServerSocket(8888);
            Socket s = ss.accept();
            new conn(s, controller);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void setIV(WritableImage wi) { }

    public static void main(String[] args) {
        launch(args);
    }
}
