package Backend;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Asus FX
 */
public class JadwalBerangkat implements IButton{
    private int id_jadwal;
    private String jadwalBerangkat;

    public JadwalBerangkat() {
    }

    public JadwalBerangkat(int id_jadwal, String jadwalBerangkat) {
        this.id_jadwal = id_jadwal;
        this.jadwalBerangkat = jadwalBerangkat;
    }
    
    @Override
    public String toString(){
        return jadwalBerangkat;
    }

    public int getId_jadwal() {
        return id_jadwal;
    }

    public void setId_jadwal(int id_jadwal) {
        this.id_jadwal = id_jadwal;
    }

    public String getJadwalBerangkat() {
        return jadwalBerangkat;
    }

    public void setJadwalBerangkat(String jadwalBerangkat) {
        this.jadwalBerangkat = jadwalBerangkat;
    }
    
    public JadwalBerangkat getById(int id){
        JadwalBerangkat j = new JadwalBerangkat();
        ResultSet rs = DBHelper.selectQuery("Select * FROM jadwalberangkat WHERE idjadwal = '" + id + "'");
        try{
            while (rs.next()) {
                j = new JadwalBerangkat();
                j.setId_jadwal(rs.getInt("idjadwal"));
                j.setJadwalBerangkat(rs.getString("jadwal"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return j;
    }
    
    public ArrayList<JadwalBerangkat> getAll(){
        ArrayList<JadwalBerangkat> ListJadwalBerangkat = new ArrayList();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM jadwalberangkat");
        
        try{
            while (rs.next()) {
                JadwalBerangkat j = new JadwalBerangkat();
                j.setId_jadwal(rs.getInt("idjadwal"));
                j.setJadwalBerangkat(rs.getString("jadwal"));
                
                ListJadwalBerangkat.add(j);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ListJadwalBerangkat;
    }

    public ArrayList<JadwalBerangkat> search(String keyword){
        ArrayList<JadwalBerangkat> ListJadwalBerangkat = new ArrayList();
        
        String sql = "SELECT * FROM jadwalberangkat WHERE "
                    + "     jadwal LIKE '%" + keyword + "%' ";
        ResultSet rs = DBHelper.selectQuery(sql);
        
        try{
            while (rs.next()) {
                JadwalBerangkat j = new JadwalBerangkat();
                j.setId_jadwal(rs.getInt("idjadwal"));
                j.setJadwalBerangkat(rs.getString("jadwal"));
                
                ListJadwalBerangkat.add(j);
            } 
        }catch (Exception e){
            e.printStackTrace();
        }
        return ListJadwalBerangkat;
    }
    
    @Override
    public void save(){
        if(getById(id_jadwal).getId_jadwal()== 0){
            String SQL = "INSERT INTO jadwalberangkat (jadwal) VALUES("
                    + "     '" + this.jadwalBerangkat + "' "
                    + "     )";
            this.id_jadwal = DBHelper.insertQueryGetId(SQL);
        }else{
            String SQL = "UPDATE jadwalberangkat SET "
                    + "     jadwal = '" + this.jadwalBerangkat + "' "
                    + "     WHERE idjadwal = '" + this.id_jadwal + "'";
            DBHelper.executeQuery(SQL);
        }
    }
    
    @Override
    public void delete(){
        String SQL = "DELETE FROM jadwalberangkat WHERE idjadwal = '" + this.id_jadwal + "'";
        DBHelper.executeQuery(SQL);
    }
}
