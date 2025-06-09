package services;

import entity.HP;

import java.util.*;

public class ProdukManager {
    private Map<String, HP> produkMap = new LinkedHashMap<>();

    public void tambahProduk(HP hp) {
        produkMap.put(hp.getId(), hp);
    }

    public void updateProduk(String oldId, HP baru) {
        produkMap.remove(oldId);
        produkMap.put(baru.getId(), baru);
    }

    public void hapusProduk(String id) {
        produkMap.remove(id);
    }

    public HP cariProduk(String id) {
        return produkMap.get(id); // Tidak langsung menambah jumlah dicari
    }

    // Tambah jumlah dicari secara manual
    public void tambahPencarian(String id, int jumlah) {
        HP hp = produkMap.get(id);
        if (hp != null) {
            for (int i = 0; i < jumlah; i++) {
                hp.tambahDicari();
            }
        }
    }

    public void beliProduk(String id) {
        HP hp = produkMap.get(id);
        if (hp != null && hp.getStok() > 0) {
            hp.tambahDibeli();
            hp.setStok(hp.getStok() - 1);
        }
    }

    public void beliProduk(String id, int jumlah) {
        HP hp = produkMap.get(id);
        if (hp != null) {
            if (jumlah <= hp.getStok()) {
                for (int i = 0; i < jumlah; i++) {
                    hp.tambahDibeli();
                }
                hp.setStok(hp.getStok() - jumlah);
                System.out.println("Berhasil membeli " + jumlah + " unit " + hp.getNama());
            } else {
                System.out.println("Stok tidak cukup.");
            }
        } else {
            System.out.println("Produk tidak ditemukan.");
        }
    }

    public List<HP> getSemuaProduk() {
        return new ArrayList<>(produkMap.values());
    }

    public List<HP> getProdukPopuler() {
        return produkMap.values().stream()
                .sorted((a, b) -> Integer.compare(
                        (b.getJumlahDicari() + b.getJumlahDibeli()),
                        (a.getJumlahDicari() + a.getJumlahDibeli())))
                .toList();
    }

    public Map<String, HP> getProdukMap() {
        return produkMap;
    }
}
