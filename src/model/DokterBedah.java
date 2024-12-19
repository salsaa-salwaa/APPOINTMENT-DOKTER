package model;

public class DokterBedah extends Dokter {
    public DokterBedah(String nama, String jadwal) {
        super(nama, jadwal);
    }

    @Override
    public String toString() {
        return "Dokter Bedah: " + getNama() + " - " + getJadwal();
    }
}
