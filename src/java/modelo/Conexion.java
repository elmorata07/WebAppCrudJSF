package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author ADALBERTO
 */
public class Conexion {
    
    public static final String url ="jdbc:mysql://localhost:3306/contactos?useSSL=false&useTimeZone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    public static final String usuario ="root";
    public static final String contrasena ="admin";
    
    public Connection getConnection(){
        
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection)DriverManager.getConnection(url, usuario, contrasena);
            JOptionPane.showMessageDialog(null, "Conexión Exitosa");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de Conexion >>> "+e.getMessage());
        }
        return con;
    }
    
}
