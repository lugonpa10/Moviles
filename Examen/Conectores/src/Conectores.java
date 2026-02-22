import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conectores {

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

	public void cerrarConexion() {
		try {
			this.conexion.close();
		} catch (SQLException e) {
			System.out.println("Error al cerrar la conexión: " + e.getLocalizedMessage());
		}
	}

	public static void Ejercicio1(String cad) {

		int cont = 0;
		String consulta = "SELECT * from jugadores_celta where NOMBRE like  ? ";
		try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
			System.out.println(consulta);
			ps.setString(1, "%" + cad + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getInt(1) + "," + rs.getString(2) + "," + rs.getString(3));
				cont++;
			}
			System.out.println(cont);

		} catch (SQLException e) {
			System.out.println("Error");
		}

	}

	public static void Ejercicio2(int num, String nombre, String pos, int edad, String pais, int convo, int partidos,
			int goles, int min) {
		String consulta = "Insert into jugadores_celta(dorsal,nombre,posicion,edad,nacionalidad,convocado,partidos_jugados,goles,minutos_jugados) VALUES(?,?,?,?,?,?,?,?,?) ";

		try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
			ps.setInt(1, num);
			ps.setString(2, nombre);
			ps.setString(3, pos);
			ps.setInt(4, edad);
			ps.setString(5, pais);
			ps.setInt(6, convo);
			ps.setInt(7, partidos);
			ps.setInt(8, goles);
			ps.setInt(9, min);
			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error");
		}

	}

	public static void Ejercicio3(int numero) {
		String consulta = "Delete from jugadores_celta where dorsal = ?";
		try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
			ps.setInt(1, numero);
			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error");
		}
	}

	public static void Ejercicio4(String nombre, int id) {
		String consulta = "update alumnos set nombre = ? where id=?";
		try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
			ps.setString(1, nombre);
			ps.setInt(2, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error");
		}
	}

	public static void Ejercicio5() {
		String consulta = "Select Distinct aulas.nombre from aulas Join alumnos on aulas.id = alumnos.aula";
		try (java.sql.Statement st = conexion.createStatement()) {
			ResultSet rs = st.executeQuery(consulta);
			while (rs.next()) {
				System.out.println(rs.getString(1));

			}

		} catch (SQLException e) {
			System.out.println("Error");
		}

	}

	public static void Ejercicio5_2() {
		String consulta = "Select alumnos.nombre,asignaturas.nombre,notas.nota from notas join asignaturas on asignaturas.id = notas.id_asignatura join alumnos on notas.id_alumno = alumnos.id where notas.nota >=5 ";
		try (java.sql.Statement st = conexion.createStatement()) {
			ResultSet rs = st.executeQuery(consulta);
			while (rs.next()) {
				System.out.println(rs.getString(1) + "," + rs.getString(2) + "," + rs.getDouble(3));

			}

		} catch (SQLException e) {
			System.out.println("Error");
		}
	}

	public static void Ejercicio5_3() {
		String consulta = "Select asignaturas.nombre from asignaturas where not exists (Select id_asignatura from notas where  asignaturas.id = notas.id_asignatura) ";
		System.out.println(consulta);
		try (java.sql.Statement st = conexion.createStatement()) {
			ResultSet rs = st.executeQuery(consulta);
			while (rs.next()) {
				System.out.println(rs.getString(1));

			}

		} catch (SQLException e) {
			System.out.println("Error");
		}
	}

	public static void Ejercicio6(String cad, int altura) {
		String consulta = "Select * from alumnos where nombre like ? and altura > ?";
		try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
			ps.setString(1, cad + "%");
			ps.setInt(2, altura);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				System.out.println( rs.getString(2) + "," + rs.getString(3)  ) ;

			}

		} catch (SQLException e) {
			System.out.println("Error");
		}

	}

	

	public static void main(String[] args) {

		abrirConexion("practica", "localhost", "root", "");
		// Ejercicio1("b");
		// Ejercicio2("lugonpa", "gonzalez", 183, "España", 33, 27, 4, 1782);
		// Ejercicio3(23);
		// Ejercicio4("Lucas", 2);
		// Ejercicio5();
		// Ejercicio5_2();
		// Ejercicio5_3();
		Ejercicio6("C", 165);

	}

}