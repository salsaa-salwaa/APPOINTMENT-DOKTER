package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomepageController {
    public void navigateToDokter() throws Exception {
        loadPage("/fxml/dokter.fxml");
    }

    public void navigateToPasien() throws Exception {
        loadPage("/fxml/pasien.fxml");
    }

    public void navigateToAppointment() throws Exception {
        loadPage("/fxml/appointment.fxml");
    }

    private void loadPage(String path) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(path));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
