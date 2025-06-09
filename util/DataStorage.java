package util;

import entity.HP;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class DataStorage {
    private static final String FILE_NAME = "produk_data.ser";

    public static void simpan(Map<String, HP> produkMap) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(produkMap);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data: " + e.getMessage());
        }
    }

    public static Map<String, HP> muat() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (Map<String, HP>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new LinkedHashMap<>();
        }
    }
}
