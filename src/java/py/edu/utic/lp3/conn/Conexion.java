package py.edu.utic.lp3.conn;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

/**
 *
 * @author bala
 */
public class Conexion {

    private final static Logger logger = Logger.getLogger(Conexion.class);
    private Connection conn;
    private String dbName;
    private String user;
    private String password;
    private String dbDriver;
    private String host;
    private String port;
    private String url;

    public Conexion() {
        try {
            Properties prop = new Properties();
            prop.load(Conexion.class.getResourceAsStream("Connection.properties"));
            dbName = prop.getProperty("dbName");
            user = prop.getProperty("user");
            password = prop.getProperty("password");
            dbDriver = prop.getProperty("dbDriver");
            host = prop.getProperty("host");
            port = prop.getProperty("port");
            url = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;
            Class.forName(dbDriver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            logger.error("CLASS "+this.getClass().getName()+" on Constructor ", ex);
        } catch (IOException ex) {
            logger.error("CLASS "+this.getClass().getName()+" on Constructor ", ex);
        }
    }

    /**
     * Cierra la conexión a la base de datos.
     */
    public void desConectarBD() {
        try {
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Durante la DesConecion", "AVISO", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * devuelve el objeto conexión.
     * @return 
     */
    public Connection getConnection() {
        return conn;
    }

}
