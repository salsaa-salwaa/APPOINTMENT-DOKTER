package model;

public class Dokter {
    private String nama;
    private String jadwal;

    public Dokter(String nama, String jadwal) {
        this.nama = nama;
        this.jadwal = jadwal;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJadwal() {
        return jadwal;
    }

    public void setJadwal(String jadwal) {
        this.jadwal = jadwal;
    }

    @Override
    public String toString() {
        return nama + " - " + jadwal;
    }
}
