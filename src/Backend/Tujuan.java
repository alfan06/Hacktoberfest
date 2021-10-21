package Backend;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Asus FX
 */
public class Tujuan implements IButton{
    private int id_tujuan;
    private String tujuan;

    public Tujuan() {
    }

    public Tujuan(int id_tujuan, String tujuan) {
        this.id_tujuan = id_tujuan;
        this.tujuan = tujuan;
    }
    
    @Override
    public String toString(){
            return tujuan;
    }

    public int getId_tujuan() {
        return id_tujuan;
    }

    public void setId_tujuan(int id_tujuan) {
        this.id_tujuan = id_tujuan;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }
     
    public Tujuan getById(int id){
        Tujuan t = new Tujuan();
        ResultSet rs = DBHelper.selectQuery("Select * FROM tujuan WHERE idtujuan = '" + id + "'");
        try{
            while (rs.next()) {
                t = new Tujuan();
                t.setId_tujuan(rs.getInt("idtujuan"));
                t.setTujuan(rs.getString("tujuan"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return t;
    }
    
    public ArrayList<Tujuan> getAll(){
        ArrayList<Tujuan> ListTujuan = new ArrayList();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM tujuan");
        
        try{
            while (rs.next()) {
                Tujuan t = new Tujuan();
                t.setId_tujuan(rs.getInt("idtujuan"));
                t.setTujuan(rs.getString("tujuan"));
                
                ListTujuan.add(t);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ListTujuan;
    }

    public ArrayList<Tujuan> search(String keyword){
        ArrayList<Tujuan> ListTujuan = new ArrayList();
        
        String sql = "SELECT * FROM tujuan WHERE "
                    + "     tujuan LIKE '%" + keyword + "%' ";
        ResultSet rs = DBHelper.selectQuery(sql);
        
        try{
            while (rs.next()) {
                Tujuan t = new Tujuan();
                t.setId_tujuan(rs.getInt("idtujuan"));
                t.setTujuan(rs.getString("tujuan"));
                
                ListTujuan.add(t);
            } 
        }catch (Exception e){
            e.printStackTrace();
        }
        return ListTujuan;
    }
    
    @Override
    public void save(){
        if(getById(id_tujuan).getId_tujuan()== 0){
            String SQL = "INSERT INTO tujuan (tujuan) VALUES("
                    + "     '" + this.tujuan + "' "
                    + "     )";
            this.id_tujuan = DBHelper.insertQueryGetId(SQL);
        }else{
            String SQL = "UPDATE tujuan SET "
                    + "     tujuan = '" + this.tujuan + "' "
                    + "     WHERE idtujuan = '" + this.id_tujuan + "'";
            DBHelper.executeQuery(SQL);
        }
    }
    
    @Override
    public void delete(){
        String SQL = "DELETE FROM tujuan WHERE idtujuan = '" + this.id_tujuan + "'";
        DBHelper.executeQuery(SQL);
    } 
}
