package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import model.*;

public class DokterController {

    @FXML
    private ComboBox<String> spesialisFilter;
    @FXML
    private TableView<Dokter> dokterTable;
    @FXML
    private TableColumn<Dokter, String> namaColumn;
    @FXML
    private TableColumn<Dokter, String> spesialisColumn;
    @FXML
    private TableColumn<Dokter, String> jadwalColumn;

    private static ObservableList<Dokter> allDokters = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        namaColumn.setCellValueFactory(new PropertyValueFactory<>("nama"));
        spesialisColumn.setCellValueFactory(cellData -> {
            Dokter dokter = cellData.getValue();
            String spesialis = dokter instanceof DokterUmum ? "Umum" :
                               dokter instanceof DokterGigi ? "Gigi" :
                               dokter instanceof DokterAnak ? "Anak" :
                               dokter instanceof DokterObgyn ? "Obgyn" :
                               dokter instanceof DokterKulit ? "Kulit" :
                               dokter instanceof DokterBedah ? "Bedah" : "Lainnya";
            return new SimpleStringProperty(spesialis);
        });
        jadwalColumn.setCellValueFactory(new PropertyValueFactory<>("jadwal"));

        spesialisFilter.setItems(FXCollections.observableArrayList("Semua", "Umum", "Gigi", "Anak", "Obgyn", "Kulit", "Bedah"));
        spesialisFilter.getSelectionModel().select("Semua");

        loadSampleData();
        dokterTable.setItems(allDokters);
        spesialisFilter.setOnAction(e -> applyFilter());
    }

    private void loadSampleData() {
        allDokters.addAll(
            new DokterUmum("Dr. Ahmad", "Senin, Rabu, Jumat"),
            new DokterGigi("Drg. Siti", "Selasa, Kamis"),
            new DokterAnak("Dr. Budi", "Sabtu, Minggu"),
            new DokterObgyn("Dr. Citra", "Senin, Kamis"),
            new DokterKulit("Dr. Dani", "Selasa, Jumat"),
            new DokterBedah("Dr. Eko", "Rabu, Sabtu")
        );
    }

    private void applyFilter() {
        String selectedFilter = spesialisFilter.getSelectionModel().getSelectedItem();
        ObservableList<Dokter> filteredDokters = FXCollections.observableArrayList();

        if (selectedFilter.equals("Semua")) {
            filteredDokters.addAll(allDokters);
        } else {
            for (Dokter dokter : allDokters) {
                if ((selectedFilter.equals("Umum") && dokter instanceof DokterUmum) ||
                    (selectedFilter.equals("Gigi") && dokter instanceof DokterGigi) ||
                    (selectedFilter.equals("Anak") && dokter instanceof DokterAnak) ||
                    (selectedFilter.equals("Obgyn") && dokter instanceof DokterObgyn) ||
                    (selectedFilter.equals("Kulit") && dokter instanceof DokterKulit) ||
                    (selectedFilter.equals("Bedah") && dokter instanceof DokterBedah)) {
                    filteredDokters.add(dokter);
                }
            }
        }
        dokterTable.setItems(filteredDokters);
    }

    public static ObservableList<String> getDokterList() {
        if (allDokters.isEmpty()) {
            new DokterController().loadSampleData();
        }
        ObservableList<String> dokterNamesWithSpesialis = FXCollections.observableArrayList();
        for (Dokter dokter : allDokters) {
            String spesialis = dokter instanceof DokterUmum ? "Umum" :
                               dokter instanceof DokterGigi ? "Gigi" :
                               dokter instanceof DokterAnak ? "Anak" :
                               dokter instanceof DokterObgyn ? "Obgyn" :
                               dokter instanceof DokterKulit ? "Kulit" :
                               dokter instanceof DokterBedah ? "Bedah" : "Lainnya";
            dokterNamesWithSpesialis.add(dokter.getNama() + " - " + spesialis);
        }
        return dokterNamesWithSpesialis;
    }
}
