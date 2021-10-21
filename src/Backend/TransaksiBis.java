package Backend;

/**
 *
 * @author Asus FX
 */
import java.util.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
public class TransaksiBis implements IButton{
    private int id_transaksiBis;
    private User user = new User();
    private Bis bis = new Bis();
    private int biaya;
    private int jmlhOrg;
    private String date;

    public TransaksiBis() {
    }

    public TransaksiBis(User user,Bis bis,int biaya, String date) {
        this.user = user;
        this.bis = bis;
        this.biaya = biaya;
        this.date = date;
    }

    public int getId_transaksiBis() {
        return id_transaksiBis;
    }

    public void setId_transaksiBis(int id_transaksiBis) {
        this.id_transaksiBis = id_transaksiBis;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bis getBis() {
        return bis;
    }

    public void setBis(Bis bis) {
        this.bis = bis;
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
    
     public TransaksiBis getById(int id) {
        TransaksiBis transaksi = new TransaksiBis();
        ResultSet rs = DBHelper.selectQuery("Select "
                + " p.id_transaksi as id_transaksi, "
                + " p.Jumlah_tiket as Jumlah_tiket, "
                + " p.biaya as biaya, "
                + " p.tanggaltransaksi as tanggaltransaksi,"
                + " a.id_user as id_user, "
                + " a.nama as nama,"
                + " a.alamat as alamat,"
                + " a.telepon as telepon,"
                + " b.idbis as idbis, "
                + " b.idkategori as idkategori, "
                + " b.merk as merk,"
                + " b.kapasitas as kapasitas,"
                + " b.idtujuan as idtujuan,"
                + " b.idjadwal as idjadwal,"
                + " b.harga as harga"
                + " FROM transaksibis p "
                + " left join user a on p.id_user = a.id_user "
                + " left join bis as b on p.idbis = b.idbis"
                + " where p.id_transaksi = '" + id + "'");
        try {
            while (rs.next()) {
                transaksi = new TransaksiBis();
                transaksi.setId_transaksiBis(rs.getInt("id_transaksi"));
                transaksi.getUser().setId_user(rs.getInt("id_user"));
                transaksi.getUser().setNama(rs.getString("nama"));
                transaksi.getUser().setAlamat(rs.getString("alamat"));
                transaksi.getUser().setTelepon(rs.getString("telepon"));
                transaksi.getBis().setId_bis(rs.getInt("idbis"));
                transaksi.getBis().setMerk(rs.getString("merk"));
                transaksi.getBis().setKapasitas(rs.getInt("kapasitas"));
                transaksi.getBis().getTujuan().setId_tujuan(rs.getInt("idtujuan"));
                transaksi.getBis().getJadwalberangkat().setId_jadwal(rs.getInt("idjadwal"));
                transaksi.setJmlhOrg(rs.getInt("Jumlah_tiket"));
                transaksi.getBis().setHarga(rs.getInt("harga"));
                transaksi.setDate(rs.getString("tanggaltransaksi"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transaksi;
    }
    
    public ArrayList<TransaksiBis> getAll() {
        ArrayList<TransaksiBis> ListTransaksi = new ArrayList();
        ResultSet rs = DBHelper.selectQuery("Select "
                + " p.id_transaksi as id_transaksi, "
                + " p.Jumlah_tiket as Jumlah_tiket, "
                + " p.biaya as biaya, "
                + " p.tanggaltransaksi as tanggaltransaksi,"
                + " a.id_user as id_user, "
                + " a.nama as nama,"
                + " a.alamat as alamat,"
                + " a.telepon as telepon,"
                + " b.idbis as idbis, "
                + " b.idkategori as idkategori, "
                + " b.merk as merk,"
                + " b.kapasitas as kapasitas,"
                + " b.idtujuan as idtujuan,"
                + " b.idjadwal as idjadwal,"
                + " b.harga as harga"
                + " FROM transaksibis p "
                + " left join user a on p.id_user = a.id_user "
                + " left join bis b on p.idbis = b.idbis ");
        try {
            while (rs.next()) {
                TransaksiBis transaksi = new TransaksiBis();
                transaksi.setId_transaksiBis(rs.getInt("id_transaksi"));
                transaksi.getUser().setId_user(rs.getInt("id_user"));
                transaksi.getUser().setNama(rs.getString("nama"));
                transaksi.getUser().setAlamat(rs.getString("alamat"));
                transaksi.getUser().setTelepon(rs.getString("telepon"));
                transaksi.getBis().setId_bis(rs.getInt("idbis"));
                transaksi.getBis().setMerk(rs.getString("merk"));
                transaksi.getBis().setKapasitas(rs.getInt("kapasitas"));
                transaksi.getBis().getTujuan().setId_tujuan(rs.getInt("idtujuan"));
                transaksi.getBis().getJadwalberangkat().setId_jadwal(rs.getInt("idjadwal"));
                transaksi.setJmlhOrg(rs.getInt("Jumlah_tiket"));
                transaksi.getBis().setHarga(rs.getInt("harga"));
                transaksi.setDate(rs.getString("tanggaltransaksi"));

                ListTransaksi.add(transaksi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListTransaksi;
    }
    
    
    
    public void searchKapasitas(int id) {
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM bis WHERE idbis = '" + id + "'");

        try {
            while (rs.next()) {
                getBis().setId_bis(rs.getInt("idbis"));
                getBis().setKapasitas(rs.getInt("kapasitas"));
                getBis().setHarga(rs.getInt("harga"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void save() {
        if (getById(id_transaksiBis).getId_transaksiBis()== 0) {
            String SQL = "INSERT INTO transaksibis (id_user,idbis,Jumlah_tiket,biaya,tanggaltransaksi) VALUES("
                    + " '" + this.getUser().getId_user()+ "', "
                    + " '" + this.getBis().getId_bis()+ "', "
                    + " '" + this.jmlhOrg+ "', "
                    + " '" + this.biaya + "', "
                    + " '" + this.getDate() + "' "
                    + " )";
            this.id_transaksiBis = DBHelper.insertQueryGetId(SQL);
        } else {
            String SQL = "UPDATE transaksibis set "
                    + " user = '" + this.getUser().getNama()+ "', "
                    + " idbis = '" + this.getBis().getMerk()+ "', "
                    + " Jumlah_tiket = '" + this.jmlhOrg + "', "
                    + " biaya = '" + this.biaya + "', "
                    + " tanggaltransaksi = '" + this.getDate() + "', "
                    + " WHERE id_transaksi = '" + this.id_transaksiBis + "'";
            DBHelper.executeQuery(SQL);
        }
    }

    @Override
    public void delete() {
        String SQL = "DELETE FROM transaksibis where id_transaksi = '" + this.id_transaksiBis + "'";
        DBHelper.executeQuery(SQL);
    }
    
}
