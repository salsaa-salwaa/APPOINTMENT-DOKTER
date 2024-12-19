package model;

import java.time.LocalDate;

public class Appointment {
    private int id;
    private String dokter;
    private String pasien;
    private LocalDate tanggal;
    private String status;

    public Appointment(int id, String dokter, String pasien, LocalDate tanggal, String status) {
        this.id = id;
        this.dokter = dokter;
        this.pasien = pasien;
        this.tanggal = tanggal;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDokter() {
        return dokter;
    }

    public void setDokter(String dokter) {
        this.dokter = dokter;
    }

    public String getPasien() {
        return pasien;
    }

    public void setPasien(String pasien) {
        this.pasien = pasien;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
