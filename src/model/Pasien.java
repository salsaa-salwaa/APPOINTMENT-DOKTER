package model;

import java.io.Serializable;

public class Pasien implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String nama;
    private String alamat;
    private int umur;

    public Pasien(String nama, String alamat, int umur) {
        this.nama = nama;
        this.alamat = alamat;
        this.umur = umur;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }
}
