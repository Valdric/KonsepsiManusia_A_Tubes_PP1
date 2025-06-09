package entity;

public class AntrianProduk {
    private HP produk;
    private int nomorAntrian;

    public AntrianProduk(HP produk, int nomorAntrian) {
        this.produk = produk;
        this.nomorAntrian = nomorAntrian;
    }

    public HP getProduk() { return produk; }
    public int getNomorAntrian() { return nomorAntrian; }
}
