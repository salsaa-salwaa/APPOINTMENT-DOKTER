package model;

public class DokterKulit extends Dokter {
    public DokterKulit(String nama, String jadwal) {
        super(nama, jadwal);
    }

    @Override
    public String toString() {
        return "Dokter Kulit: " + getNama() + " - " + getJadwal();
    }
}
