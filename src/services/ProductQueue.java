package services;

import entity.AntrianProduk;

public class ProductQueue {
    private AntrianProduk[] array;
    private int capacity, front, rear, size;

    public ProductQueue(int capacity) {
        this.capacity = capacity;
        this.array = new AntrianProduk[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public boolean isEmpty() { return size == 0; }
    public boolean isFull() { return size == capacity; }

    public void enqueue(AntrianProduk produk) {
        if (isFull()) {
            System.out.println("Antrian penuh!");
            return;
        }
        rear = (rear + 1) % capacity;
        array[rear] = produk;
        size++;
    }
    public AntrianProduk[] getArray() {
        return array;
    }

    public int getSize() {
        return size;
    }

    public int getFront() {
        return front;
    }

    public int getCapacity() {
        return capacity;
    }

    public AntrianProduk dequeue() {
        if (isEmpty()) {
            System.out.println("Antrian kosong!");
            return null;
        }
        AntrianProduk temp = array[front];
        front = (front + 1) % capacity;
        size--;
        return temp;
    }

    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Antrian kosong.");
            return;
        }
        System.out.println("Isi Antrian:");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            System.out.println("- " + array[index].getProduk().getNama());
        }
    }
}
