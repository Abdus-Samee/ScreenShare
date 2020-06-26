package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");

        ImageView iv = new ImageView();
        iv.setFitWidth(500);
        iv.setFitHeight(400);

        Text t = new Text("Screen Sharing");
        t.setTextAlignment(TextAlignment.CENTER);
        t.setFill(Color.FIREBRICK);
        t.setStyle("-fx-font: 18 arial;");

        Button b = new Button("Exit");
        b.setAlignment(Pos.BASELINE_CENTER);
        b.setOnAction(event -> {
            Platform.exit();
        });

        VBox v = new VBox(10);
        v.getChildren().addAll(t,iv,b);
        primaryStage.setScene(new Scene(v, 500, 500));
        primaryStage.show();

        try{
            Socket s = new Socket("localhost", 8888);
            ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
            new network(s, os);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}