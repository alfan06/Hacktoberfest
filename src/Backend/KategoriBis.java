/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Asus FX
 */
public class KategoriBis implements IButton{
    private int idkategori;
    private String nama;
    private String keterangan;

    public KategoriBis() {
    }

    public KategoriBis(int idkategori, String nama, String keterangan) {
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
    
    public KategoriBis getById(int id){
        KategoriBis k = new KategoriBis();
        ResultSet rs = DBHelper.selectQuery("Select * FROM kategoribis WHERE idkategori = '" + id + "'");
        try{
            while (rs.next()) {
                k = new KategoriBis();
                k.setIdkategori(rs.getInt("idkategori"));
                k.setNama(rs.getString("nama"));
                k.setKeterangan(rs.getString("keterangan"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return k;
    }
    
    public ArrayList<KategoriBis> getAll(){
        ArrayList<KategoriBis> ListKategoriBis = new ArrayList();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM kategoribis");
        
        try{
            while (rs.next()) {
                KategoriBis k = new KategoriBis();
                k.setIdkategori(rs.getInt("idkategori"));
                k.setNama(rs.getString("nama"));
                k.setKeterangan(rs.getString("keterangan"));
                
                ListKategoriBis.add(k);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ListKategoriBis;
    }

    public ArrayList<KategoriBis> search(String keyword){
        ArrayList<KategoriBis> ListKategoriBis = new ArrayList();
        
        String sql = "SELECT * FROM kategoribis WHERE "
                    + "     nama LIKE '%" + keyword + "%' "
                    + "     or keterangan LIKE '%" + keyword + "%' ";
        ResultSet rs = DBHelper.selectQuery(sql);
        
        try{
            while (rs.next()) {
                KategoriBis k = new KategoriBis();
                k.setIdkategori(rs.getInt("idkategori"));
                k.setNama(rs.getString("nama"));
                k.setKeterangan(rs.getString("keterangan"));
                
                ListKategoriBis.add(k);
            } 
        }catch (Exception e){
            e.printStackTrace();
        }
        return ListKategoriBis;
    }
    
    @Override
    public void save(){
        if(getById(idkategori).getIdkategori() == 0){
            String SQL = "INSERT INTO kategoribis (nama,keterangan) VALUES("
                    + "     '" + this.nama + "', "
                    + "     '" + this.keterangan + "' "
                    + "     )";
            this.idkategori = DBHelper.insertQueryGetId(SQL);
        }else{
            String SQL = "UPDATE kategoribis SET "
                    + "     nama = '" + this.nama + "', "
                    + "     keterangan = '" + this.keterangan + "' "
                    + "     WHERE idkategori = '" + this.idkategori + "'";
            DBHelper.executeQuery(SQL);
        }
    }
    
    @Override
    public void delete(){
        String SQL = "DELETE FROM kategoribis WHERE idkategori = '" + this.idkategori + "'";
        DBHelper.executeQuery(SQL);
    }
}
