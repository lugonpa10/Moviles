package exam;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.checkerframework.common.reflection.qual.GetClass;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/deportistas")
public class GestionaDeportistas {

    private static final String URL = "jdbc:mariadb://localhost:3306/ad_tema6";
    private static final String USER = "root";
    private static final String PASS = "";
    ArrayList<Deportista> listaDeportistas = new ArrayList<>();
    Deportista deportista;

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodos() {
        try {
            listaDeportistas.clear();
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("Select * from deportistas");
                while (rs.next()) {
                    listaDeportistas.add(new Deportista(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getBoolean(3),
                            rs.getString(4),
                            rs.getString(5)

                    ));

                }

                return Response.ok(listaDeportistas).build();

            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarJugador(@PathParam("id") int id) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {

                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("Select * from deportistas where id = " + id);
                if (rs.next()) {
                    Deportista d = new Deportista(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getBoolean(3),
                            rs.getString(4),
                            rs.getString(5));

                    return Response.ok(d).build();
                }

                return Response.status(Response.Status.NOT_FOUND).build();

            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/deporte/{nombreDeporte}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response porDeporte(@PathParam("nombreDeporte") String nombreDeporte) {
        try {

            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
                PreparedStatement ps = connection.prepareStatement("Select * from deportistas where deporte like ?");
                ps.setString(1, nombreDeporte);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    listaDeportistas.add(new Deportista(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getBoolean(3),
                            rs.getString(4),
                            rs.getString(5)));
                }
                return Response.ok(listaDeportistas).build();

            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error sql").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/retirados")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response deportistasRetirados() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("Select * from deportistas where activo = 0");
                while (rs.next()) {
                    listaDeportistas.add(new Deportista(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getBoolean(3),
                            rs.getString(4),
                            rs.getString(5)));

                }
                return Response.ok(listaDeportistas).build();

            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error sql").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/deporte/{nombreDeporte}/activos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response activosDeporte(@PathParam("nombreDeporte") String deporte) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
                PreparedStatement ps = connection
                        .prepareStatement("Select * from deportistas where deporte = ? && activo = 1");
                ps.setString(1, deporte);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    listaDeportistas.add(new Deportista(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getBoolean(3),
                            rs.getString(4),
                            rs.getString(5)));
                }
                return Response.ok(listaDeportistas).build();

            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error sql").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Path("/sdepor")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response contar() {
        try {

            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
                int cont = 0;
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("Select *  from deportistas");
                while (rs.next()) {
                    cont++;
                }
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();

            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Eerror sql").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @Path("/deportes")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response deportes() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
                ArrayList<String> deportes = new ArrayList<>();
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("Select Distinct deporte from deportistas Order by deporte ASC");
                while (rs.next()) {
                    deportes.add(rs.getString(1));

                }
                return Response.ok(deportes).build();
            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Eerror sql").build();

            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

        }

    }

    @Path("/añadir")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response añadir(Deportista d) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
                PreparedStatement ps = connection.prepareStatement(
                        "Insert into deportistas (id,nombre,activo,genero,deporte) VALUES (?,?,?,?,?)");
                ps.setInt(1, d.getId());
                ps.setString(2, d.getNombre());
                ps.setBoolean(3, d.getActivo());
                ps.setString(4, d.getGenero());
                ps.setString(5, d.getDeporte());

                ps.executeUpdate();
                return Response.ok(d).build();

            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("error sql").build();

            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Path("del/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrar(@PathParam("id") int id) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
                PreparedStatement ps = connection.prepareStatement("Delete from deportistas where id = ?");
                ps.setInt(1, id);
                ps.executeUpdate();
                return Response.ok("Deportista eliminado").build();

            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Eerror").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
