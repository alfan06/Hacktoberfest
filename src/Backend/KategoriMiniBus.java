package Backend;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Asus FX
 */
public class KategoriMiniBus implements IButton{
    private int idkategori;
    private String nama;
    private String keterangan;

    public KategoriMiniBus() {
    }

    public KategoriMiniBus(int idkategori, String nama, String keterangan) {
        this.idkategori = idkategori;
        this.nama = nama;
        this.keterangan = keterangan;
    }
    
    @Override
    public String toString(){
            return nama;
    }

    public int getIdkategori() {
        return idkategori;
    }

    public void setIdkategori(int idkategori) {
        this.idkategori = idkategori;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
    
    public KategoriMiniBus getById(int id){
        KategoriMiniBus kat = new KategoriMiniBus();
        ResultSet rs = DBHelper.selectQuery("Select * FROM kategoriminibus WHERE idkategori = '" + id + "'");
        try{
            while (rs.next()) {
                kat = new KategoriMiniBus();
                kat.setIdkategori(rs.getInt("idkategori"));
                kat.setNama(rs.getString("nama"));
                kat.setKeterangan(rs.getString("keterangan"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return kat;
    }
    
    public ArrayList<KategoriMiniBus> getAll(){
        ArrayList<KategoriMiniBus> ListKategoriMiniBus = new ArrayList();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM kategoriminibus");
        
        try{
            while (rs.next()) {
                KategoriMiniBus k = new KategoriMiniBus();
                k.setIdkategori(rs.getInt("idkategori"));
                k.setNama(rs.getString("nama"));
                k.setKeterangan(rs.getString("keterangan"));
                
                ListKategoriMiniBus.add(k);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ListKategoriMiniBus;
    }

    public ArrayList<KategoriMiniBus> search(String keyword){
        ArrayList<KategoriMiniBus> ListKategoriMiniBus = new ArrayList();
        
        String sql = "SELECT * FROM kategoriminibus WHERE "
                    + "     nama LIKE '%" + keyword + "%' "
                    + "     or keterangan LIKE '%" + keyword + "%' ";
        ResultSet rs = DBHelper.selectQuery(sql);
        
        try{
            while (rs.next()) {
                KategoriMiniBus k = new KategoriMiniBus();
                k.setIdkategori(rs.getInt("idkategori"));
                k.setNama(rs.getString("nama"));
                k.setKeterangan(rs.getString("keterangan"));
                
                ListKategoriMiniBus.add(k);
            } 
        }catch (Exception e){
            e.printStackTrace();
        }
        return ListKategoriMiniBus;
    }
    
    @Override
    public void save(){
        if(getById(idkategori).getIdkategori() == 0){
            String SQL = "INSERT INTO kategoriminibus (nama,keterangan) VALUES("
                    + "     '" + this.nama + "', "
                    + "     '" + this.keterangan + "' "
                    + "     )";
            this.idkategori = DBHelper.insertQueryGetId(SQL);
        }else{
            String SQL = "UPDATE kategoriminibus SET "
                    + "     nama = '" + this.nama + "', "
                    + "     keterangan = '" + this.keterangan + "' "
                    + "     WHERE idkategori = '" + this.idkategori + "'";
            DBHelper.executeQuery(SQL);
        }
    }
    
    @Override
    public void delete(){
        String SQL = "DELETE FROM kategoriminibus WHERE idkategori = '" + this.idkategori + "'";
        DBHelper.executeQuery(SQL);
    }
}
