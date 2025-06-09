package main;

import entity.HP;
import entity.AntrianProduk;
import services.ProductQueue;
import services.ProdukManager;
import util.InputUtil;
import util.DataStorage;

public class ManajemenHPMain {
    private static int nomorAntrian = 1;

    public static void main(String[] args) {
        ProdukManager manager = new ProdukManager();
        ProductQueue queue = new ProductQueue(10);
        manager.getProdukMap().putAll(DataStorage.muat());

        while (true) {
            System.out.println("\n=== Manajemen Penjualan HP ===");
            System.out.println("");
            System.out.println("1. Tambah Produk ke Antrian");
            System.out.println("2. Proses Antrian");
            System.out.println("3. Lihat Semua Produk");
            System.out.println("4. Jumlah lihat Produk");
            System.out.println("5. Jumlah Beli Produk");
            System.out.println("6. Ubah Produk");
            System.out.println("7. Hapus Produk");
            System.out.println("8. Daftar Barang Populer");
            System.out.println("9. Simpan & Keluar");

            int pilihan = InputUtil.inputInt("Pilih: ");

            switch (pilihan) {
                case 1 -> {
                    String id = InputUtil.inputString("ID HP: ");
                    while (true) {
                        if (id.matches("\\d+")) break;
                        else System.out.println("id itu angka bukan huruf kocak,, jadi gabisa input kan -_-");
                    }
                    if (manager.cariProduk(id) != null) {
                        System.out.println("❌ Tidak dapat menambahkan produk baru, ID bentrok!");
                        break;
                    }
                    boolean idSudahDalamAntrian = false;
                    for (int i = 0; i < queue.getSize(); i++) {
                        int index = (queue.getFront() + i) % queue.getCapacity();
                        AntrianProduk antrian = queue.getArray()[index];
                        if (antrian.getProduk().getId().equalsIgnoreCase(id)) {
                            idSudahDalamAntrian = true;
                            break;
                        }
                    }

                    if (idSudahDalamAntrian) {
                        System.out.println("❌ Tidak dapat menambahkan produk baru, ID bentrok di antrian!");
                        break;
                    }

                    String nama = InputUtil.inputString("Nama HP: ");
                    int stok = InputUtil.inputInt("Stok: ");
                    HP hp = new HP(id, nama, stok);
                    AntrianProduk ap = new AntrianProduk(hp, nomorAntrian++);
                    queue.enqueue(ap);
                }
                
                case 2 -> {
                    AntrianProduk ap = queue.dequeue();
                    if (ap != null) {
                        manager.tambahProduk(ap.getProduk());
                        System.out.println("Produk diproses: " + ap.getProduk().getNama());
                    }
                }
                case 3 -> manager.getSemuaProduk().forEach(System.out::println);

                case 4 -> {
                    String id = InputUtil.inputString("ID produk: ");
                    while (true) {
                        id = InputUtil.inputString("ID produk: ");
                        if (id.matches("\\d+")) break;
                        else System.out.println("id itu angka bukan huruf kocak,, jadi gabisa input kan -_-");
                    }

                    HP hasil = manager.cariProduk(id);

                    if (hasil != null) {
                        System.out.println(hasil);
                        int jumlahDicari = InputUtil.inputInt("Berapa kali produk ini dicari? ");
                        manager.tambahPencarian(id, jumlahDicari); 
                    } else {
                        System.out.println("Produk tidak ditemukan.");
                    }

                }

                case 5 -> {
                    String id;
                    while (true) {
                        id = InputUtil.inputString("ID produk: ");
                        if (id.matches("\\d+")) break;
                        else System.out.println("id itu angka bukan huruf kocak,, jadi gabisa input kan -_-");
                    }

                    int jumlah = InputUtil.inputInt("Jumlah beli: ");
                    manager.beliProduk(id, jumlah); 
                }


                case 6 -> {
                    String oldId;
                    while (true) {
                        oldId = InputUtil.inputString("ID produk yang ingin diubah: ");
                        if (oldId.matches("\\d+")) break;
                        else System.out.println("id itu angka bukan huruf kocak,, jadi gabisa input kan -_-");
                    }

                    HP lama = manager.cariProduk(oldId);

                    if (lama != null) {
                        String newId;
                        while (true) {
                            newId = InputUtil.inputString("ID baru: ");
                            if (newId.matches("\\d+")) break;
                            else System.out.println("id itu angka bukan huruf kocak,, jadi gabisa input kan -_-");
                        }

                        String newNama = InputUtil.inputString("Nama baru: ");
                        int newStok = InputUtil.inputInt("Stok baru: ");

                        HP baru = new HP(newId, newNama, newStok);

                        for (int i = 0; i < lama.getJumlahDicari(); i++) baru.tambahDicari();
                        for (int i = 0; i < lama.getJumlahDibeli(); i++) baru.tambahDibeli();

                        manager.updateProduk(oldId, baru);
                        System.out.println("Produk berhasil diubah.");
                    } else {
                        System.out.println("Produk tidak ditemukan.");
                    }
                }

                case 7 -> {
                    String id;
                    while (true) {
                        id = InputUtil.inputString("ID produk yang ingin dihapus: ");
                        if (id.matches("\\d+")) break;
                        else System.out.println("id itu angka bukan huruf kocak,, jadi gabisa input kan -_-");
                    }

                    HP produk = manager.cariProduk(id);

                    if (produk != null) {
                        manager.hapusProduk(id);
                        System.out.println("Produk berhasil dihapus.");
                    } else {
                        System.out.println("Produk tidak ditemukan.");
                    }
                }


                case 8 -> {
                    System.out.println("=== Daftar Barang Populer ===");
                    manager.getProdukPopuler().forEach(System.out::println); 
                }

                case 9 -> {
                    DataStorage.simpan(manager.getProdukMap());
                    System.out.println("Data disimpan. Keluar dari program.");
                    System.exit(0);
                }

                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
