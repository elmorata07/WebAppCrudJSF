
package controlador;

import com.oracle.wls.shaded.org.apache.bcel.generic.AALOAD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import modelo.Conexion;
/*
 bean se refiere a las cclases que tinen como objetivo capturar informacion de 
una db
capsula para almacenar base de datos
es reconocida en todo el contexto de la app como clienteBean
 */

@Named(value="clienteBean")
/*
anotacion que ayuda a sociar esta clase con el protocolo http request
*/
@RequestScoped


public class clienteBean {
    
    private int idCliente;
    private String nombreCliente, apellidoCliente, direccionCliente;
    
    public clienteBean(){
        
        /*
        constructor vacio es necesario  
        cada que vamos a necesitar una instancia de clienteBean
        este contructor toma el frramework en el que estamos trabajando y crea instancias
        de esta vacia
        
 */
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }
          /*
        meotdo para listar 
        nos devuelve una lista de tipo clienteBean y llamamos tablaClientes
         */
    public List<clienteBean> getTablaClientes(){
        List<clienteBean> data = new ArrayList<>();
        Conexion con = new Conexion();
        
        try {
            Connection conexion = con.getConnection();
            Statement sql = conexion.createStatement();
            ResultSet rs = sql.executeQuery("select * from cliente");
            
            while (rs.next()) {                
                clienteBean obj =new clienteBean();
                obj.setIdCliente(rs.getInt("idCliente"));
                obj.setNombreCliente(rs.getString("nombreCliente"));
                obj.setApellidoCliente(rs.getString("apellidoCliente"));
                obj.setDireccionCliente(rs.getString("direccionCliente"));
                
                data.add(obj);
            }
            conexion.close();
        } catch (Exception e) {
            System.out.println("error al leer los datos "+e.getMessage());
        }
        
        return data;
    }
    
    public void agregarCliente(){
        Conexion  conex = new Conexion();
        
        try {
            Connection con = conex.getConnection();
            PreparedStatement ps;
            ps = con.prepareStatement("INSERT INTO cliente (nombreCliente,"
                    + "apellidoCliente, direccionCliente) values (?,?,?)");
            ps.setString(1, nombreCliente);
            ps.setString(2, apellidoCliente);
            ps.setString(3, direccionCliente);
            ps.executeUpdate();
            System.out.println("Se agregó correctamente");
        } catch (Exception e) {
            System.out.println("error al agregar al cliente "+e.getMessage());
        }
    }
    
   public void modificarCliente(){
       Conexion conex = new Conexion();
       
       try {
           Connection con = conex.getConnection();
           PreparedStatement ps;
           ps = con.prepareStatement("UPDATE cliente SET nombreCliente=?, apellidoCliente=?, direccionCliente=? WHERE  idCliente=?");
           ps.setString(1, nombreCliente);
           ps.setString(2, apellidoCliente);
           ps.setString(3, direccionCliente);
           ps.setInt(4, idCliente);
           ps.executeUpdate();
           System.out.println("Se modificó correctamente");
       } catch (Exception e) {
           System.out.println("Error al actualizar el cliente: "+e.getMessage());
       }
       
   } 
   
   public void eliminarCliente(){
       Conexion conex = new Conexion();
       
       try {
           Connection con = conex.getConnection();
           PreparedStatement ps;
           ps = con.prepareStatement("delete from cliente where idCliente=?");
           ps.setInt(1, idCliente);
           ps.executeUpdate();
           System.out.println("Se eliminó exitosamente ");
       } catch (Exception e) {
           System.out.println("Error al eliminar cliente"+e.getMessage());
       }
   }
    
   
   
}
