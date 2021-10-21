package Backend;

/**
 *
 * @author Asus FX
 */
import java.util.ArrayList;
import java.sql.*;
public class User extends Orang{
    public int id_user;

    public User() {
    }

    public User(String nama, String alamat, String telepon) {
        super(nama, alamat, telepon);
    }

    public int getId_user() {
        return id_user;
    }
    
    public String toString(){
        return nama;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    
    public User getById(int id){
        //polypmorph
        Orang o;
        User u = new User();
        o = u;
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM user "
                                            + "WHERE id_user = '" + id + "'");
        try{
            while (rs.next()) {
                u = new User();
                u.setId_user(rs.getInt("id_user"));
                o.setNama(rs.getString("nama"));
                o.setAlamat(rs.getString("alamat"));
                o.setTelepon(rs.getString("telepon"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return u;
    }
    
    public ArrayList<User> getAll() {
        ArrayList<User> ListUser = new ArrayList();

        ResultSet rs = DBHelper.selectQuery("SELECT * FROM user");

        try {
            while (rs.next()) {
                User u = new User();
                u.setId_user(rs.getInt("id_user"));
                u.setNama(rs.getString("nama"));
                u.setAlamat(rs.getString("alamat"));
                u.setTelepon(rs.getString("telepon"));

                ListUser.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListUser;
    }

    @Override
    public void save() {
        if(getById(id_user).getId_user()== 0){
            String SQL = "INSERT INTO user (nama,alamat,telepon) VALUES(" 
                    + "     '" +this.nama + "', "
                    + "     '" +this.alamat + "', "
                    + "     '" +this.telepon + "' "
                    + "     )";
            this.id_user = DBHelper.insertQueryGetId(SQL);
        }else{
            String SQL = "UPDATE user SET"
                   + "      nama = '" +this.nama + "', "
                   + "      alamat = '" +this.alamat + "', "
                   + "      telepon = '"+this.telepon+ "' "
                    + "     WHERE id_anggota = '"+this.id_user+ "'";
            DBHelper.executeQuery(SQL);
        }
    }

    @Override
    public void delete() {
        String SQL = "DELETE FROM user WHERE id_user = '" + this.id_user + "'";
        DBHelper.executeQuery(SQL);
    }
    
    
}
