
package conexion;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Team
 */
public class Conexion {
    
    public Connection cargarDB() {

        Connection con;
        String barra = File.separator;
        String proyecto = System.getProperty("user.dir") + barra + "Registros";

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            String bd = "jdbc:derby:" + proyecto;
            con = DriverManager.getConnection(bd);

            System.out.println("La Base de Datos ha sido Cargada!");

            return con;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error: " + ex);
        }

        return null;

    }
    
    public Connection CrearBd() {

        Connection con;
        String barra = File.separator;
        String proyecto = System.getProperty("user.dir") + barra + "Registros";
        File url = new File(proyecto);

        if (url.exists()) {
            System.out.println("La Base de Datos ya Existe");
        } else {
            try {

                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                String db = "jdbc:derby:" + proyecto + ";create=true";
                con = DriverManager.getConnection(db);
                String tabla = "create table mensaje(id INT PRIMARY KEY, arb VARCHAR(20000),"
                        + "cph VARCHAR(25000),cps VARCHAR(25000), des VARCHAR(25000), crb VARCHAR(25000), descripcion VARCHAR(250))";

                PreparedStatement ps = con.prepareStatement(tabla);
                ps.execute();
                ps.close();

                System.out.println("Tabla Creada con Exito!");
                return con;

            } catch (ClassNotFoundException | SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }

        return null;

    }
    
}
