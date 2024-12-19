package model;

public class DokterObgyn extends Dokter {
    public DokterObgyn(String nama, String jadwal) {
        super(nama, jadwal);
    }

    @Override
    public String toString() {
        return "Dokter Obgyn: " + getNama() + " - " + getJadwal();
    }
}
