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
public class MiniBus extends Kendaraan{
    private int id_minibus;
    private KategoriMiniBus kategoriminibus = new KategoriMiniBus();
    private JadwalBerangkat jadwalberangkat = new JadwalBerangkat();
    private Tujuan tujuan = new Tujuan();

    public MiniBus() {
    }
    
    public MiniBus(String merk, int kapasitas, int harga) {
        super(merk, kapasitas, harga);
    }

    public int getId_minibus() {
        return id_minibus;
    }

    public void setId_minibus(int id_minibus) {
        this.id_minibus = id_minibus;
    }

    public KategoriMiniBus getKategoriminibus() {
        return kategoriminibus;
    }

    public void setKategoriminibus(KategoriMiniBus kategoriminibus) {
        this.kategoriminibus = kategoriminibus;
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
    
    public MiniBus getById(int id){
        MiniBus mb = new MiniBus();
        ResultSet rs = DBHelper.selectQuery("Select "
                + " b.idminibus as idminibus, "
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
                + " FROM ((minibus b "
                + " left join kategoriminibus k on b.idkategori = k.idkategori) "
                + " left join tujuan t on b.idtujuan = t.idtujuan) "
                + " left join jadwalberangkat j on b.idjadwal = j.idjadwal "
                + " where b.idminibus = '" + id + "'");
        try {
            while (rs.next()) {
                mb = new MiniBus();
                mb.setId_minibus(rs.getInt("idminibus"));
                mb.getKategoriminibus().setIdkategori(rs.getInt("idkategori"));
                mb.getKategoriminibus().setNama(rs.getString("nama"));
                mb.getKategoriminibus().setKeterangan(rs.getString("keterangan"));
                mb.getTujuan().setId_tujuan(rs.getInt("idtujuan"));
                mb.getTujuan().setTujuan(rs.getString("tujuan"));
                mb.getJadwalberangkat().setId_jadwal(rs.getInt("idjadwal"));
                mb.getJadwalberangkat().setJadwalBerangkat(rs.getString("jadwal"));
                mb.setMerk(rs.getString("merk"));
                mb.setKapasitas(rs.getInt("kapasitas"));
                mb.setHarga(rs.getInt("harga"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mb;
    }
    
    public ArrayList<MiniBus> getAll(){
        ArrayList<MiniBus> ListMiniBus = new ArrayList();
        
        ResultSet rs = DBHelper.selectQuery("Select "
                + " b.idminibus as idminibus, "
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
                + " FROM ((minibus b "
                + " left join kategoriminibus k on b.idkategori = k.idkategori) "
                + " left join tujuan t on b.idtujuan = t.idtujuan) "
                + " left join jadwalberangkat j on b.idjadwal = j.idjadwal ");
        
        try {
            while(rs.next()){
                MiniBus mb = new MiniBus();
                mb.setId_minibus(rs.getInt("idminibus"));
                mb.getKategoriminibus().setIdkategori(rs.getInt("idkategori"));
                mb.getKategoriminibus().setNama(rs.getString("nama"));
                mb.getKategoriminibus().setKeterangan(rs.getString("keterangan"));
                mb.getTujuan().setId_tujuan(rs.getInt("idtujuan"));
                mb.getTujuan().setTujuan(rs.getString("tujuan"));
                mb.getJadwalberangkat().setId_jadwal(rs.getInt("idjadwal"));
                mb.getJadwalberangkat().setJadwalBerangkat(rs.getString("jadwal"));
                mb.setMerk(rs.getString("merk"));
                mb.setKapasitas(rs.getInt("kapasitas"));
                mb.setHarga(rs.getInt("harga"));
                
                ListMiniBus.add(mb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListMiniBus;
    }
    
    public ArrayList<MiniBus> search(String keyword){
        ArrayList<MiniBus> ListBis = new ArrayList();
        
        ResultSet rs = DBHelper.selectQuery("Select "
                + " b.idminibus as idminibus, "
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
                + " FROM ((minibus b "
                + " left join kategoriminibus k on b.idkategori = k.idkategori) "
                + " left join tujuan t on b.idtujuan = t.idtujuan) "
                + " left join jadwalberangkat j on b.idjadwal = j.idjadwal "
                + " where b.merk like '%" +keyword+ "%' "
                + " OR b.kapasitas like '%" +keyword+ "%' "
                + " OR b.harga like '%" +keyword+ "%' ");
                
        try{
            while(rs.next()){
                MiniBus mb = new MiniBus();
                mb.setId_minibus(rs.getInt("idminibus"));
                mb.getKategoriminibus().setIdkategori(rs.getInt("idkategori"));
                mb.getKategoriminibus().setNama(rs.getString("nama"));
                mb.getKategoriminibus().setKeterangan(rs.getString("keterangan"));
                mb.getTujuan().setId_tujuan(rs.getInt("idtujuan"));
                mb.getTujuan().setTujuan(rs.getString("tujuan"));
                mb.getJadwalberangkat().setId_jadwal(rs.getInt("idjadwal"));
                mb.getJadwalberangkat().setJadwalBerangkat(rs.getString("jadwal"));
                mb.setMerk(rs.getString("merk"));
                mb.setKapasitas(rs.getInt("kapasitas"));
                mb.setHarga(rs.getInt("harga"));
                
                ListBis.add(mb);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ListBis;
    }

    @Override
    public void save() {
        if(getById(id_minibus).getId_minibus()== 0){
            String SQL = "INSERT INTO minibus (idkategori,merk,kapasitas,idtujuan,idjadwal,harga) VALUES("
                    + "     '" + this.getKategoriminibus().getIdkategori()+ "', "
                    + "     '" + this.merk + "', "
                    + "     '" + this.kapasitas + "', "
                    + "     '" + this.getTujuan().getId_tujuan() + "', "
                    + "     '" + this.getJadwalberangkat().getId_jadwal() + "', "
                    + "     '" + this.harga + "' "
                    + "     )";
            this.id_minibus = DBHelper.insertQueryGetId(SQL);
        }
        else{
            String SQL = "UPDATE minibus SET"
                    + "     idkategori = '" +this.getKategoriminibus().getIdkategori()+ "', "
                    + "     merk = '" + this.merk + "', "
                    + "     kapasitas = '" + this.kapasitas + "', "
                    + "     tujuan = '" + this.getTujuan().getId_tujuan() + "', "
                    + "     jadwal = '" + this.getJadwalberangkat().getId_jadwal()+ "', "
                    + "     harga = '" + this.harga + "' "
                    + "     WHERE id_bis = '" + this.id_minibus + "'";
            DBHelper.executeQuery(SQL);
        }
    }

    @Override
    public void delete() {
        String SQL = "DELETE FROM minibus WHERE idminibus = '" + this.id_minibus + "'";
        DBHelper.executeQuery(SQL);
    }
}
