package controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Pasien;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PasienController {

    @FXML
    private TextField namaField;
    @FXML
    private TextField alamatField;
    @FXML
    private TextField umurField;
    @FXML
    private TableView<Pasien> tableView;
    @FXML
    private TableColumn<Pasien, String> namaColumn;
    @FXML
    private TableColumn<Pasien, String> alamatColumn;
    @FXML
    private TableColumn<Pasien, Integer> umurColumn;

    private ObservableList<Pasien> pasienList = FXCollections.observableArrayList();
    private static ObservableList<String> pasienNames = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        namaColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNama()));
        alamatColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAlamat()));
        umurColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getUmur()).asObject());

        tableView.setItems(pasienList);

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                namaField.setText(newValue.getNama());
                alamatField.setText(newValue.getAlamat());
                umurField.setText(String.valueOf(newValue.getUmur()));
            }
        });

        loadPasien();
    }

    @FXML
    private void tambahPasien() {
        try {
            String nama = namaField.getText();
            String alamat = alamatField.getText();
            int umur = Integer.parseInt(umurField.getText());

            if (nama.isEmpty() || alamat.isEmpty() || umur <= 0) {
                showAlert("Input Error", "Semua kolom harus diisi dengan benar!");
                return;
            }

            Pasien pasien = new Pasien(nama, alamat, umur);
            pasienList.add(pasien);

            pasienNames.add(nama);

            savePasien();

            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Umur harus berupa angka.");
        }
    }

    @FXML
    private void hapusPasien() {
        Pasien selectedPasien = tableView.getSelectionModel().getSelectedItem();
        if (selectedPasien != null) {
            pasienList.remove(selectedPasien);

            pasienNames.remove(selectedPasien.getNama());

            savePasien();
        } else {
            showAlert("Tidak ada pilihan", "Pilih pasien yang ingin dihapus.");
        }
    }

    @FXML
    private void updatePasien() {
        Pasien selectedPasien = tableView.getSelectionModel().getSelectedItem();
        if (selectedPasien != null) {
            try {
                String nama = namaField.getText();
                String alamat = alamatField.getText();
                int umur = Integer.parseInt(umurField.getText());

                if (nama.isEmpty() || alamat.isEmpty() || umur <= 0) {
                    showAlert("Input Error", "Semua kolom harus diisi dengan benar!");
                    return;
                }

                selectedPasien.setNama(nama);
                selectedPasien.setAlamat(alamat);
                selectedPasien.setUmur(umur);

                tableView.refresh();

                if (!selectedPasien.getNama().equals(nama)) {
                    pasienNames.remove(selectedPasien.getNama());
                    pasienNames.add(nama);
                }

                savePasien();

                clearFields();
            } catch (NumberFormatException e) {
                showAlert("Input Error", "Umur harus berupa angka.");
            }
        } else {
            showAlert("Tidak ada pilihan", "Pilih pasien yang ingin diperbarui.");
        }
    }

    private void clearFields() {
        namaField.clear();
        alamatField.clear();
        umurField.clear();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static ObservableList<String> getPasienNames() {
        return pasienNames;
    }

    private void savePasien() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("pasien.dat"))) {
            List<Pasien> pasienData = new ArrayList<>(pasienList);
            oos.writeObject(pasienData);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Gagal menyimpan data pasien.");
        }
    }

    private void loadPasien() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("pasien.dat"))) {
            List<Pasien> loadedPasien = (List<Pasien>) ois.readObject();
            pasienList.setAll(loadedPasien);
            pasienNames.clear();
            for (Pasien pasien : loadedPasien) {
                pasienNames.add(pasien.getNama());
            }
        } catch (IOException | ClassNotFoundException e) {
            
        }
    }
}
