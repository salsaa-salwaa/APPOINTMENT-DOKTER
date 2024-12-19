package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class AppointmentController {

    @FXML
    private ComboBox<String> dokterComboBox;
    @FXML
    private ComboBox<String> pasienComboBox;
    @FXML
    private DatePicker tanggalPicker;
    @FXML
    private ComboBox<String> statusComboBox;
    @FXML
    private TableView<Appointment> appointmentTable;
    @FXML
    private TableColumn<Appointment, Integer> idColumn;
    @FXML
    private TableColumn<Appointment, String> dokterColumn;
    @FXML
    private TableColumn<Appointment, String> pasienColumn;
    @FXML
    private TableColumn<Appointment, LocalDate> tanggalColumn;
    @FXML
    private TableColumn<Appointment, String> statusColumn;

    private ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(1);

    @FXML
    public void initialize() {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dokterColumn.setCellValueFactory(new PropertyValueFactory<>("dokter"));
        pasienColumn.setCellValueFactory(new PropertyValueFactory<>("pasien"));
        tanggalColumn.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        appointmentTable.setItems(appointmentList);

        dokterComboBox.setItems(DokterController.getDokterList());
        pasienComboBox.setItems(PasienController.getPasienNames());

        statusComboBox.setItems(FXCollections.observableArrayList("Dijadwalkan", "Selesai", "Dibatalkan"));

        appointmentTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                loadAppointmentToForm(newValue);
            }
        });
    }

    private void loadAppointmentToForm(Appointment appointment) {
        dokterComboBox.setValue(appointment.getDokter());
        pasienComboBox.setValue(appointment.getPasien());
        tanggalPicker.setValue(appointment.getTanggal());
        statusComboBox.setValue(appointment.getStatus());
    }

    @FXML
    private void tambahAppointment() {
        try {
            String dokter = dokterComboBox.getValue();
            String pasien = pasienComboBox.getValue();
            LocalDate tanggal = tanggalPicker.getValue();
            String status = statusComboBox.getValue();

            if (dokter == null || pasien == null || tanggal == null || status == null) {
                showAlert("Input Error", "Semua kolom harus diisi.");
                return;
            }

            Appointment appointment = new Appointment(ID_GENERATOR.getAndIncrement(), dokter, pasien, tanggal, status);
            appointmentList.add(appointment);

            clearFields();
        } catch (Exception e) {
            showAlert("Error", "Terjadi kesalahan saat menambah appointment.");
        }
    }

    @FXML
    private void hapusAppointment() {
        Appointment selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment != null) {
            appointmentList.remove(selectedAppointment);
        } else {
            showAlert("Tidak ada pilihan", "Pilih appointment yang ingin dihapus.");
        }
    }

    @FXML
    private void updateAppointment() {
        Appointment selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment != null) {
            try {
                String dokter = dokterComboBox.getValue();
                String pasien = pasienComboBox.getValue();
                LocalDate tanggal = tanggalPicker.getValue();
                String status = statusComboBox.getValue();

                if (dokter == null || pasien == null || tanggal == null || status == null) {
                    showAlert("Input Error", "Semua kolom harus diisi.");
                    return;
                }

                selectedAppointment.setDokter(dokter);
                selectedAppointment.setPasien(pasien);
                selectedAppointment.setTanggal(tanggal);
                selectedAppointment.setStatus(status);

                appointmentTable.refresh();
                clearFields();
            } catch (Exception e) {
                showAlert("Error", "Terjadi kesalahan saat mengupdate appointment.");
            }
        } else {
            showAlert("Tidak ada pilihan", "Pilih appointment yang ingin diupdate.");
        }
    }

    private void clearFields() {
        dokterComboBox.getSelectionModel().clearSelection();
        pasienComboBox.getSelectionModel().clearSelection();
        tanggalPicker.setValue(null);
        statusComboBox.getSelectionModel().clearSelection();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
