package model;

public class DokterGigi extends Dokter {
    public DokterGigi(String nama, String jadwal) {
        super(nama, jadwal);
    }

    @Override
    public String toString() {
        return "Dokter Gigi: " + getNama() + " - " + getJadwal();
    }
}
