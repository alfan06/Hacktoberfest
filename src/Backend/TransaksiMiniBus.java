package Backend;

/**
 *
 * @author Asus FX
 */
import java.util.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
public class TransaksiMiniBus implements IButton{
    private int id_transaksiMiniBus;
    private User user = new User();
    private MiniBus minibis = new MiniBus();
    private int biaya;
    private int jmlhOrg;
    private String date;

    public TransaksiMiniBus() {
    }

    public TransaksiMiniBus(User user,MiniBus bis,int biaya, String date) {
        this.user = user;
        this.minibis = minibis;
        this.biaya = biaya;
        this.date = date;
    }

    public int getId_transaksiMiniBus() {
        return id_transaksiMiniBus;
    }

    public void setId_transaksiMiniBus(int id_transaksiMiniBus) {
        this.id_transaksiMiniBus = id_transaksiMiniBus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MiniBus getMinibis() {
        return minibis;
    }

    public void setMinibis(MiniBus minibis) {
        this.minibis = minibis;
    }

    public int getJmlhOrg() {
        return jmlhOrg;
    }

    public void setJmlhOrg(int jmlhOrg) {
        this.jmlhOrg = jmlhOrg;
    }

    public int getBiaya() {
        return biaya;
    }

    public void setBiaya(int biaya) {
        this.biaya = biaya;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
     public TransaksiMiniBus getById(int id) {
        TransaksiMiniBus transaksi = new TransaksiMiniBus();
        ResultSet rs = DBHelper.selectQuery("Select "
                + " p.id_transaksi as id_transaksi, "
                + " p.Jumlah_tiket as Jumlah_tiket, "
                + " p.biaya as biaya, "
                + " p.tanggaltransaksi as tanggaltransaksi,"
                + " a.id_user as id_user, "
                + " a.nama as nama,"
                + " a.alamat as alamat,"
                + " a.telepon as telepon,"
                + " b.idminibus as idminibus, "
                + " b.idkategori as idkategori, "
                + " b.merk as merk,"
                + " b.kapasitas as kapasitas,"
                + " b.idtujuan as idtujuan,"
                + " b.idjadwal as idjadwal,"
                + " b.harga as harga"
                + " FROM transaksiminibus p "
                + " left join user a on p.id_user = a.id_user "
                + " left join minibus as b on p.idminibus = b.idminibus"
                + " where p.id_transaksi = '" + id + "'");
        try {
            while (rs.next()) {
                transaksi = new TransaksiMiniBus();
                transaksi.setId_transaksiMiniBus(rs.getInt("id_transaksi"));
                transaksi.getUser().setId_user(rs.getInt("id_user"));
                transaksi.getUser().setNama(rs.getString("nama"));
                transaksi.getUser().setAlamat(rs.getString("alamat"));
                transaksi.getUser().setTelepon(rs.getString("telepon"));
                transaksi.getMinibis().setId_minibus(rs.getInt("idminibus"));
                transaksi.getMinibis().setMerk(rs.getString("merk"));
                transaksi.getMinibis().setKapasitas(rs.getInt("kapasitas"));
                transaksi.getMinibis().getTujuan().setId_tujuan(rs.getInt("idtujuan"));
                transaksi.getMinibis().getJadwalberangkat().setId_jadwal(rs.getInt("idjadwal"));
                transaksi.setJmlhOrg(rs.getInt("Jumlah_tiket"));
                transaksi.getMinibis().setHarga(rs.getInt("harga"));
                transaksi.setDate(rs.getString("tanggaltransaksi"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transaksi;
    }
    
    public ArrayList<TransaksiMiniBus> getAll() {
        ArrayList<TransaksiMiniBus> ListTransaksi = new ArrayList();
        ResultSet rs = DBHelper.selectQuery("Select "
                + " p.id_transaksi as id_transaksi, "
                + " p.Jumlah_tiket as Jumlah_tiket, "
                + " p.biaya as biaya, "
                + " p.tanggaltransaksi as tanggaltransaksi,"
                + " a.id_user as id_user, "
                + " a.nama as nama,"
                + " a.alamat as alamat,"
                + " a.telepon as telepon,"
                + " b.idminibus as idminibus, "
                + " b.idkategori as idkategori, "
                + " b.merk as merk,"
                + " b.kapasitas as kapasitas,"
                + " b.idtujuan as idtujuan,"
                + " b.idjadwal as idjadwal,"
                + " b.harga as harga"
                + " FROM transaksiminibus p "
                + " left join user a on p.id_user = a.id_user "
                + " left join minibus b on p.idminibus = b.idminibus ");
        try {
            while (rs.next()) {
                TransaksiMiniBus transaksi = new TransaksiMiniBus();
                transaksi.setId_transaksiMiniBus(rs.getInt("id_transaksi"));
                transaksi.getUser().setId_user(rs.getInt("id_user"));
                transaksi.getUser().setNama(rs.getString("nama"));
                transaksi.getUser().setAlamat(rs.getString("alamat"));
                transaksi.getUser().setTelepon(rs.getString("telepon"));
                transaksi.getMinibis().setId_minibus(rs.getInt("idminibus"));
                transaksi.getMinibis().setMerk(rs.getString("merk"));
                transaksi.getMinibis().setKapasitas(rs.getInt("kapasitas"));
                transaksi.getMinibis().getTujuan().setId_tujuan(rs.getInt("idtujuan"));
                transaksi.getMinibis().getJadwalberangkat().setId_jadwal(rs.getInt("idjadwal"));
                transaksi.setJmlhOrg(rs.getInt("Jumlah_tiket"));
                transaksi.getMinibis().setHarga(rs.getInt("harga"));
                transaksi.setDate(rs.getString("tanggaltransaksi"));

                ListTransaksi.add(transaksi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListTransaksi;
    }
    
    public void searchKapasitas(int id) {
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM minibus WHERE idminibus = '" + id + "'");

        try {
            while (rs.next()) {
                getMinibis().setId_minibus(rs.getInt("idminibus"));
                getMinibis().setKapasitas(rs.getInt("kapasitas"));
                getMinibis().setHarga(rs.getInt("harga"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void save() {
        if (getById(id_transaksiMiniBus).getId_transaksiMiniBus()== 0) {
            String SQL = "INSERT INTO transaksiminibus (id_user,idminibus,Jumlah_tiket,biaya,tanggaltransaksi) VALUES("
                    + " '" + this.getUser().getId_user()+ "', "
                    + " '" + this.getMinibis().getId_minibus()+ "', "
                    + " '" + this.jmlhOrg+ "', "
                    + " '" + this.biaya + "', "
                    + " '" + this.getDate() + "' "
                    + " )";
            this.id_transaksiMiniBus = DBHelper.insertQueryGetId(SQL);
        } else {
            String SQL = "UPDATE transaksiminibus set "
                    + " user = '" + this.getUser().getNama()+ "', "
                    + " idminibus = '" + this.getMinibis().getMerk()+ "', "
                    + " Jumlah_tiket = '" + this.jmlhOrg + "', "
                    + " biaya = '" + this.biaya + "', "
                    + " tanggaltransaksi = '" + this.getDate() + "', "
                    + " WHERE id_transaksi = '" + this.id_transaksiMiniBus+ "'";
            DBHelper.executeQuery(SQL);
        }
    }

    @Override
    public void delete() {
        String SQL = "DELETE FROM `transaksiminibus` WHERE `transaksiminibus`.`id_transaksi` = '" + this.id_transaksiMiniBus+ "'";
        DBHelper.executeQuery(SQL);
    }
    
}
