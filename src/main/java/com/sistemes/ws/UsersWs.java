package com.sistemes.ws;

 import com.sistemes.Application;
 import com.sistemes.codi.UsersCodi;
 import com.sistemes.filtres.UsersFiltre;
 import com.sistemes.llistes.UsersLlista;
 import com.sistemes.models.UsersModel;

 import javax.ws.rs.GET;
 import javax.ws.rs.PUT;
 import javax.ws.rs.Path;
 import javax.ws.rs.Produces;
 import javax.ws.rs.PathParam;
 import javax.ws.rs.core.Context;
 import javax.ws.rs.core.MediaType;
 import javax.ws.rs.core.Response;
 import javax.ws.rs.core.Response.Status;
 import javax.ws.rs.core.UriInfo;

@Path("Users")
public class UsersWs {
	UsersLlista llista = null;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDades(@Context UriInfo info){
	//			if (llista == null ) {
		UsersCodi negoci = new UsersCodi(Application.getConnexio());
		UsersFiltre filtre = new UsersFiltre(info.getQueryParameters());
		llista = negoci.select(filtre);
	//            }
		String result = llista.toJson();
		return Response.ok().entity(result).build();
	}

    @PUT
    @Path("/{id:[0-9][0-9]*}")
    public Response saveUser(@PathParam("id") Integer id, UsersModel entity){
        UsersCodi negoci = new UsersCodi(Application.getConnexio());
        if (entity == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        if (id == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        if (!id.equals(entity.getId())) {
            return Response.status(Status.CONFLICT).entity(entity).build();
        }
        negoci.insert(entity);

        return Response.noContent().build();
    }

}
