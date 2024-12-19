package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void HandleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        System.out.println("Attempting login with username: " + username);

        if (isValidCredentials(username, password)) {
            navigateToHomepage();
        } else {
            showAlert("Login Failed", "Username atau password salah. Silakan coba lagi.");
        }
    }

    private boolean isValidCredentials(String username, String password) {
        return username.equals("admin") && password.equals("admin123");
    }

    private void navigateToHomepage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/homepage.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Homepage");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Terjadi kesalahan saat membuka halaman beranda.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
