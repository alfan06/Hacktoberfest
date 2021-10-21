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
import java.util.ArrayList;
import java.sql.*;
public class Bis extends Kendaraan{
    private int id_bis;
    private KategoriBis kategoribis = new KategoriBis();
    private JadwalBerangkat jadwalberangkat = new JadwalBerangkat();
    private Tujuan tujuan = new Tujuan();

    public Bis() {
    }

    public Bis(String merk, int kapasitas, int harga) {
        super(merk, kapasitas, harga);
    }
    
    @Override
    public String toString(){
        return merk;
    }

    public int getId_bis() {
        return id_bis;
    }

    public void setId_bis(int id_bis) {
        this.id_bis = id_bis;
    }

    public KategoriBis getKategoribis() {
        return kategoribis;
    }

    public void setKategoribis(KategoriBis kategoribis) {
        this.kategoribis = kategoribis;
    }

    public JadwalBerangkat getJadwalberangkat() {
        return jadwalberangkat;
    }

    public void setJadwalberangkat(JadwalBerangkat jadwalberangkat) {
        this.jadwalberangkat = jadwalberangkat;
    }

    public Tujuan getTujuan() {
        return tujuan;
    }

    public void setTujuan(Tujuan tujuan) {
        this.tujuan = tujuan;
    }

    public Bis getById(int id){
        Bis b = new Bis();
        ResultSet rs = DBHelper.selectQuery("Select "
                + " b.idbis as idbis, "
                + " b.merk as merk,"
                + " b.kapasitas as kapasitas, "
                + " b.harga as harga, "
                + " k.idkategori as idkategori, "
                + " k.nama as nama, "
                + " k.keterangan as keterangan, "
                + " t.idtujuan as idtujuan, "
                + " t.tujuan as tujuan, "
                + " j.idjadwal as idjadwal, "
                + " j.jadwal as jadwal"
                + " FROM ((bis b "
                + " left join kategoribis k on b.idkategori = k.idkategori) "
                + " left join tujuan t on b.idtujuan = t.idtujuan) "
                + " left join jadwalberangkat j on b.idjadwal = j.idjadwal "
                + " where b.idbis = '" + id + "'");
        try {
            while (rs.next()) {
                b = new Bis();
                b.setId_bis(rs.getInt("idbis"));
                b.getKategoribis().setIdkategori(rs.getInt("idkategori"));
                b.getKategoribis().setNama(rs.getString("nama"));
                b.getKategoribis().setKeterangan(rs.getString("keterangan"));
                b.getTujuan().setId_tujuan(rs.getInt("idtujuan"));
                b.getTujuan().setTujuan(rs.getString("tujuan"));
                b.getJadwalberangkat().setId_jadwal(rs.getInt("idjadwal"));
                b.getJadwalberangkat().setJadwalBerangkat(rs.getString("jadwal"));
                b.setMerk(rs.getString("merk"));
                b.setKapasitas(rs.getInt("kapasitas"));
                b.setHarga(rs.getInt("harga"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }
    
    public ArrayList<Bis> getAll(){
        ArrayList<Bis> ListBis = new ArrayList();
        
        ResultSet rs = DBHelper.selectQuery("Select "
                + " b.idbis as idbis, "
                + " b.merk as merk,"
                + " b.kapasitas as kapasitas, "
                + " b.harga as harga, "
                + " k.idkategori as idkategori, "
                + " k.nama as nama, "
                + " k.keterangan as keterangan, "
                + " t.idtujuan as idtujuan, "
                + " t.tujuan as tujuan, "
                + " j.idjadwal as idjadwal, "
                + " j.jadwal as jadwal"
                + " FROM ((bis b "
                + " left join kategoribis k on b.idkategori = k.idkategori) "
                + " left join tujuan t on b.idtujuan = t.idtujuan) "
                + " left join jadwalberangkat j on b.idjadwal = j.idjadwal ");
        
        try {
            while(rs.next()){
                Bis b = new Bis();
                b.setId_bis(rs.getInt("idbis"));
                b.getKategoribis().setIdkategori(rs.getInt("idkategori"));
                b.getKategoribis().setNama(rs.getString("nama"));
                b.getKategoribis().setKeterangan(rs.getString("keterangan"));
                b.getTujuan().setId_tujuan(rs.getInt("idtujuan"));
                b.getTujuan().setTujuan(rs.getString("tujuan"));
                b.getJadwalberangkat().setId_jadwal(rs.getInt("idjadwal"));
                b.getJadwalberangkat().setJadwalBerangkat(rs.getString("jadwal"));
                b.setMerk(rs.getString("merk"));
                b.setKapasitas(rs.getInt("kapasitas"));
                b.setHarga(rs.getInt("harga"));
                
                ListBis.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListBis;
    }
    
    //---------------------------------------------------------------------------------//
    //tambahan                                                                         //
    //---------------------------------------------------------------------------------//
    public ArrayList<Bis> getByTujuan(int id1,int id2){
        ArrayList<Bis> ListBis = new ArrayList();
        
        ResultSet rs = DBHelper.selectQuery("Select "
                + " b.idbis as idbis, "
                + " b.merk as merk,"
                + " b.kapasitas as kapasitas, "
                + " b.harga as harga, "
                + " k.idkategori as idkategori, "
                + " k.nama as nama, "
                + " k.keterangan as keterangan, "
                + " t.idtujuan as idtujuan, "
                + " t.tujuan as tujuan, "
                + " j.idjadwal as idjadwal, "
                + " j.jadwal as jadwal"
                + " FROM ((bis b "
                + " left join kategoribis k on b.idkategori = k.idkategori) "
                + " left join tujuan t on b.idtujuan = t.idtujuan) "
                + " left join jadwalberangkat j on b.idjadwal = j.idjadwal "
                + " where t.tujuan like '" + id1+ "' "
                + " and where j.jadwal like '" + id2+ "' ");
        
        try {
            while(rs.next()){
                Bis b = new Bis();
                b.setId_bis(rs.getInt("idbis"));
                b.getKategoribis().setIdkategori(rs.getInt("idkategori"));
                b.getKategoribis().setNama(rs.getString("nama"));
                b.getKategoribis().setKeterangan(rs.getString("keterangan"));
                b.getTujuan().setId_tujuan(rs.getInt("idtujuan"));
                b.getTujuan().setTujuan(rs.getString("tujuan"));
                b.getJadwalberangkat().setId_jadwal(rs.getInt("idjadwal"));
                b.getJadwalberangkat().setJadwalBerangkat(rs.getString("jadwal"));
                b.setMerk(rs.getString("merk"));
                b.setKapasitas(rs.getInt("kapasitas"));
                b.setHarga(rs.getInt("harga"));
                
                ListBis.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListBis;
    }
    //---------------------------------------------------------------------------------//
    //---------------------------------------------------------------------------------//
    
    public ArrayList<Bis> search(String keyword){
        ArrayList<Bis> ListBis = new ArrayList();
        
        ResultSet rs = DBHelper.selectQuery("Select "
                + " b.idbis as idbis, "
                + " b.merk as merk,"
                + " b.kapasitas as kapasitas, "
                + " b.harga as harga, "
                + " k.idkategori as idkategori, "
                + " k.nama as nama, "
                + " k.keterangan as keterangan, "
                + " t.idtujuan as idtujuan, "
                + " t.tujuan as tujuan, "
                + " j.idjadwal as idjadwal, "
                + " j.jadwal as jadwal"
                + " FROM ((bis b "
                + " left join kategoribis k on b.idkategori = k.idkategori) "
                + " left join tujuan t on b.idtujuan = t.idtujuan) "
                + " left join jadwalberangkat j on b.idjadwal = j.idjadwal "
                + " where b.merk like '%" +keyword+ "%' "
                + " OR b.kapasitas like '%" +keyword+ "%' "
                + " OR b.idbis like '%" +keyword+ "%' "
                + " OR k.nama like'%" +keyword+ "%' "
                + " OR t.tujuan like'%" +keyword+ "%' "
                + " OR j.jadwal like'%" +keyword+ "%' "
                + " OR b.harga like '%" +keyword+ "%' ");
                
        try{
            while(rs.next()){
                Bis b = new Bis();
                b.setId_bis(rs.getInt("idbis"));
                b.getKategoribis().setIdkategori(rs.getInt("idkategori"));
                b.getKategoribis().setNama(rs.getString("nama"));
                b.getKategoribis().setKeterangan(rs.getString("keterangan"));
                b.getTujuan().setId_tujuan(rs.getInt("idtujuan"));
                b.getTujuan().setTujuan(rs.getString("tujuan"));
                b.getJadwalberangkat().setId_jadwal(rs.getInt("idjadwal"));
                b.getJadwalberangkat().setJadwalBerangkat(rs.getString("jadwal"));
                b.setMerk(rs.getString("merk"));
                b.setKapasitas(rs.getInt("kapasitas"));
                b.setHarga(rs.getInt("harga"));;
                
                ListBis.add(b);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ListBis;
    }
    
    @Override
    public void save() {
        if(getById(id_bis).getId_bis() == 0){
            String SQL = "INSERT INTO bis (idkategori,merk,kapasitas,idtujuan,idjadwal,harga) VALUES("
                    + "     '" + this.getKategoribis().getIdkategori()+ "', "
                    + "     '" + this.merk + "', "
                    + "     '" + this.kapasitas + "', "
                    + "     '" + this.getTujuan().getId_tujuan() + "', "
                    + "     '" + this.getJadwalberangkat().getId_jadwal() + "', "
                    + "     '" + this.harga + "' "
                    + "     )";
            this.id_bis = DBHelper.insertQueryGetId(SQL);
        }
        else{
            String SQL = "UPDATE bis SET"
                    + "     idkategori = '" +this.getKategoribis().getIdkategori()+ "', "
                    + "     merk = '" + this.merk + "', "
                    + "     kapasitas = '" + this.kapasitas + "', "
                    + "     idtujuan = '" + this.getTujuan().getId_tujuan() + "', "
                    + "     idjadwal = '" + this.getJadwalberangkat().getId_jadwal()+ "', "
                    + "     harga = '" + this.harga + "' "
                    + "     WHERE idbis = '" + this.id_bis + "'";
            DBHelper.executeQuery(SQL);
        }
    }

    @Override
    public void delete() {
        String SQL = "DELETE FROM bis WHERE idbis = '" + this.id_bis + "'";
        DBHelper.executeQuery(SQL);
    }
    
    
}
