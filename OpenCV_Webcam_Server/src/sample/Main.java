package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

        ServerSocket ss = new ServerSocket(8888);
        ServerSocket ss_screen = new ServerSocket(9999);
        ServerSocket ss_robot = new ServerSocket(7777);
        Socket s = ss.accept();
        Socket s_screen = ss_screen.accept();
        Socket s_robot = ss_robot.accept();
        ObjectInputStream is = new ObjectInputStream(s.getInputStream());
        ObjectOutputStream os_robot = new ObjectOutputStream(s_robot.getOutputStream());
        network net = new network(s, is, os_robot, controller, s_screen);
        controller.setNet(net);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
