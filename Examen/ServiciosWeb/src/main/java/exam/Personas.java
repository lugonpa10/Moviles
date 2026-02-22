package exam;

import java.util.ArrayList;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/personas")
public class Personas {
    static ArrayList<Persona> personas = new ArrayList<>();

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response guardar(Persona p) {
        personas.add(p);
        return Response.ok(p).build();

    }

    @GET
    @Produces({ MediaType.APPLICATION_XML })
    public Response listar() {
        GenericEntity<ArrayList<Persona>> entity = new GenericEntity<ArrayList<Persona>>(personas) {
        };
        return Response.ok(entity).build();
    }

    @Path("/{nombre}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response ver(@PathParam("nombre") String nombre) {
        for (Persona persona : personas) {
            if (persona.getNombre().equals(nombre)) {
                return Response.ok(persona).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Path("/buscar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response ver2(@DefaultValue("l") @QueryParam("cadena") String cadena) {
        ArrayList<Persona> personaConPatron = new ArrayList<>();
        for (Persona persona : personas) {
            if (persona.getNombre().toLowerCase().contains(cadena)) {
                personaConPatron.add(persona);

            }

        }
        return Response.ok(personaConPatron).build();
    }

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertarPersonas(ArrayList<Persona> p) {
        personas.addAll(p);

        return Response.ok(personas).build();

    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrar(@PathParam("id") int id) {
        for (Persona persona : personas) {
            if (persona.getId() == id) {
                personas.remove(persona);

                return Response.ok(personas).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();

    }

   

}
