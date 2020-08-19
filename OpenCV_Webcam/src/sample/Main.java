package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.robot.Robot;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.opencv.core.Core;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Main extends Application {
    private Robot screenRobot = new Robot();

    @Override
    public void start(Stage primaryStage) throws Exception{
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXHelloCV.fxml"));
            BorderPane rootElement = (BorderPane) loader.load();
            Scene scene = new Scene(rootElement, 800, 600);
            primaryStage.setTitle("JavaFX meets OpenCV");
            primaryStage.setScene(scene);
            primaryStage.show();

            Socket s = new Socket("127.0.0.1", 8888);
            Socket s_screen = new Socket("127.0.0.1", 9999);
            Socket s_robot = new Socket("127.0.0.1", 7777);
            ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
            ObjectOutputStream os_screen = new ObjectOutputStream(s_screen.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(s_robot.getInputStream());
            Utils.setNet(s, os, os_screen);

            // set the proper behavior on closing the application
            FXHelloCVController controller = loader.getController();
            netRobot nr = new netRobot(is, controller);
            nr.setMain(this);

            primaryStage.setOnCloseRequest((new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we)
                {
                    controller.setClosed();
                }
            }));

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        launch(args);
    }

    public void move(double x, double y){
        Platform.runLater(() -> {
            System.out.println("moving robot...");
            screenRobot.mouseMove(x, y);
            screenRobot.mousePress(MouseButton.PRIMARY);
            screenRobot.mouseRelease(MouseButton.PRIMARY);
        });
    }
}
