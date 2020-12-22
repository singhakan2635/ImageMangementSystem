package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    /**
     * Start is implemented when we extend the the Application class in the Main.
     * We are calling the fxml file from the resource to start the javafx application
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("Image Management Tool By Akanksha Singh");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }

    /**
     * In main method we will launch the application
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
