import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent root = loader.load();
            
            
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Appointment Dokter");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace(); 
            System.out.println("Error loading FXML: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
