package py.edu.utic.lp3.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import py.edu.utic.lp3.conn.Conexion;
import py.edu.utic.lp3.dto.CiudadDTO;

/**
 *
 * @author bala
 */
public class CiudadDao implements GenericDAO<CiudadDTO>{

    private final static Logger logger = Logger.getLogger(CiudadDao.class);
    private Conexion conexion;
    
    @Override
    public Boolean create(CiudadDTO t) {
        try {
            conexion = new Conexion();
            String query = "INSERT INTO ciudades(descripcion) VALUES (?);";
            PreparedStatement pstm = conexion.getConnection().prepareStatement(query);
            pstm.setString(1, t.getDescripcion());
            pstm.executeQuery();
            return true;
        } catch (SQLException ex) {
            logger.error("CLASS "+CiudadDao.class.getName()+"METOD: create ", ex);
            return false;
        }
    }

    @Override
    public Boolean delete(Integer id) {
        try {
            conexion = new Conexion();
            String query = "DELETE FROM ciudades WHERE id = ?;";
            PreparedStatement pstm = conexion.getConnection().prepareStatement(query);
            pstm.setInt(1, id);
            pstm.executeQuery();
            return true;
        } catch (SQLException ex) {
            logger.error("CLASS "+CiudadDao.class.getName()+"METOD: delete ", ex);
            return false;
        }
    }

    @Override
    public Boolean update(CiudadDTO t) {
        try {
            conexion = new Conexion();
            String query = "UPDATE ciudades  SET descripcion=? WHERE id = "+t.getId();
            PreparedStatement pstm = conexion.getConnection().prepareStatement(query);
            pstm.setString(1, t.getDescripcion());
            pstm.executeQuery();
            return true;
        } catch (SQLException ex) {
            logger.error("CLASS "+CiudadDao.class.getName()+"METOD: update ", ex);
            return false;
        }
    }

    @Override
    public CiudadDTO getByID(Integer id) {
        try {
            conexion = new Conexion();
            String query = "SELECT * FROM ciudades WHERE id = "+id;
            Statement stm = conexion.getConnection().createStatement();
            ResultSet rs = stm.executeQuery(query);
            CiudadDTO ciudad = null;
            while (rs.next()) {
                ciudad = new CiudadDTO();
                ciudad.setId(rs.getInt("ïd"));
                ciudad.setDescripcion(rs.getString("descripcion"));
            }
            return ciudad;
        } catch (SQLException ex) {
            logger.error("CLASS "+CiudadDao.class.getName()+"METOD: update ", ex);
            return null;
        }
    }

    @Override
    public List<CiudadDTO> getAll() {
        try {
            conexion = new Conexion();
            String query = "SELECT * FROM ciudades";
            Statement stm = conexion.getConnection().createStatement();
            ResultSet rs = stm.executeQuery(query);
            CiudadDTO ciudad = null;
            List<CiudadDTO> ciudades = new ArrayList<>();
            while (rs.next()) {
                ciudad = new CiudadDTO();
                ciudad.setId(rs.getInt("id"));
                ciudad.setDescripcion(rs.getString("descripcion"));
                ciudades.add(ciudad);
            }
            return ciudades;
        } catch (SQLException ex) {
            logger.error("CLASS "+CiudadDao.class.getName()+"METOD: update ", ex);
            return null;
        }
    }
    
}
