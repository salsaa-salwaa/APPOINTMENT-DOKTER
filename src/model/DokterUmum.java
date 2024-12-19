package model;

public class DokterUmum extends Dokter {
    public DokterUmum(String nama, String jadwal) {
        super(nama, jadwal);
    }

    @Override
    public String toString() {
        return "Dokter Umum: " + getNama() + " - " + getJadwal();
    }
}
