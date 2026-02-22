import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conectores2 {
    private static Connection conexion;

    public static void abrirConexion(String bd, String servidor, String usuario, String password) {
        try {

            try {
                Class.forName("org.mariadb.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            String url = String.format("jdbc:mariadb://%s:3306/%s", servidor, bd);

            conexion = DriverManager.getConnection(url, usuario, password);
            if (conexion != null && !conexion.isClosed()) {
                System.out.println("Conectado a " + bd + " en " + servidor);
            } else {
                System.out.println("No conectado a " + bd + " en " + servidor);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getLocalizedMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Código error: " + e.getErrorCode());
        }
    }

    public static void cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getLocalizedMessage());
        }

    }

    public static void consultarAlumnos(String cad) {
        int cont = 0;
        try (PreparedStatement ps = conexion
                .prepareStatement("Select * from alumnos where nombre like ? GROUP BY  nombre")) {
            ps.setString(1, "%" + cad + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("nombre") + "," + rs.getInt("altura"));

                cont++;

            }
            System.out.println(cont);

        } catch (SQLException e) {
            System.out.println("error");
        }
    }

    public static void darALtaAlumnos(int id, String nombre, String ape, int alt, int aula) {
        try (PreparedStatement ps = conexion
                .prepareStatement("Insert into alumnos(id,nombre,apellidos,altura,aula) VALUES(?,?,?,?,?)")) {
            ps.setInt(1, id);
            ps.setString(2, nombre);
            ps.setString(3, ape);
            ps.setInt(4, alt);
            ps.setInt(5, aula);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error");
        }
    }

    public static void dardeBAja(int id) {
        try (PreparedStatement ps = conexion.prepareStatement("Delete from alumnos where id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("error");
        }
    }

    public static void aulasConAlumnos() {

        try (Statement st = conexion.createStatement()) {
            ResultSet rs = st
                    .executeQuery("Select Distinct aulas.nombre from aulas join alumnos on alumnos.aula = aulas.id");
            while (rs.next()) {
                System.out.println(rs.getString(1));

            }

        } catch (SQLException e) {
            System.out.println("Error");
        }
    }

    public static void ejercicio5_2() {
        try (Statement st = conexion.createStatement()) {
            ResultSet rs = st.executeQuery(
                    "Select  alumnos.nombre,asignaturas.nombre,notas.nota from notas join alumnos on alumnos.id = notas.id_alumno join asignaturas on asignaturas.id = notas.id_asignatura where notas.nota >=5  ");

            while (rs.next()) {
                System.out.println(rs.getString(1) + "," + rs.getString(2) + "," + rs.getInt(3));

            }
        } catch (SQLException e) {
            System.out.println("error");
        }
    }

    public static void ejercicio5_3() {
        try (Statement st = conexion.createStatement()) {

        } catch (SQLException e) {
            System.out.println("Error");
        }

    }

    public static void ejercicio6(String cad, int altura) {
        try (PreparedStatement ps = conexion
                .prepareStatement("Select * from alumnos where nombre like ? && altura >= ?")) {
            ps.setString(1, "%" + cad);
            ps.setInt(2, altura);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("nombre"));
                System.out.println(rs.getInt("altura"));
                
            }


        } catch (SQLException e) {
            System.out.println("Error");
        }
    }

    public static void main(String[] args) {
        abrirConexion("practica", "localhost", "root", "");
        // consultarAlumnos("l");
        // darALtaAlumnos(10, "efgreg", "efgregr", 190, 33);
        // dardeBAja(10);
        // aulasConAlumnos();
        // ejercicio5_2();
        ejercicio6("a", 165);

        cerrarConexion();
    }
}
