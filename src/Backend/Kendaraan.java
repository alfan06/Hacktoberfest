/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

/**
 *
 * @author Asus FX
 */
public abstract class Kendaraan implements IButton{
    public String merk;
    public int kapasitas;
    public int harga;

    public Kendaraan() {
    }

    public Kendaraan(String merk, int kapasitas, int harga) {
        this.merk = merk;
        this.kapasitas = kapasitas;
        this.harga = harga;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
}
