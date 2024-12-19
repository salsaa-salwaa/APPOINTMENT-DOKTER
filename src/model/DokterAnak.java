package model;

public class DokterAnak extends Dokter {
    public DokterAnak(String nama, String jadwal) {
        super(nama, jadwal);
    }

    @Override
    public String toString() {
        return "Dokter Anak: " + getNama() + " - " + getJadwal();
    }
}
