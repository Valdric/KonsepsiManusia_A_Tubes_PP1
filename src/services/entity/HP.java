package entity;

import java.io.Serializable;

public class HP implements Serializable {
    private String id;
    private String nama;
    private int stok;
    private int jumlahDicari;
    private int jumlahDibeli;

    public HP(String id, String nama, int stok) {
        this.id = id;
        this.nama = nama;
        this.stok = stok;
        this.jumlahDicari = 0;
        this.jumlahDibeli = 0;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }

    public int getJumlahDicari() { return jumlahDicari; }
    public void tambahDicari() { this.jumlahDicari++; }

    public int getJumlahDibeli() { return jumlahDibeli; }
    public void tambahDibeli() { this.jumlahDibeli++; }

    @Override
    public String toString() {
        return String.format("ID: %s | Nama: %s | Stok: %d | Dicari: %d | Dibeli: %d",
                id, nama, stok, jumlahDicari, jumlahDibeli);
    }
}
