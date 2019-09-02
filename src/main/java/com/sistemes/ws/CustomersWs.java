package com.sistemes.ws;

 import com.sistemes.Application;
 import com.sistemes.codi.CustomersCodi;
 import com.sistemes.codi.CustomersCodi;
 import com.sistemes.filtres.CustomersFiltre;
 import com.sistemes.llistes.CustomersLlista;
 import com.sistemes.models.CustomersModel;

 import javax.ws.rs.*;
 import javax.ws.rs.core.Context;
 import javax.ws.rs.core.MediaType;
 import javax.ws.rs.core.Response;
 import javax.ws.rs.core.UriInfo;

@Path("Customers")
public class CustomersWs {
	CustomersLlista llista = null;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDades(@Context UriInfo info){
	//			if (llista == null ) {
		CustomersCodi negoci = new CustomersCodi(Application.getConnexio());
		CustomersFiltre filtre = new CustomersFiltre(info.getQueryParameters());
		llista = negoci.select(filtre);
	//            }
		String result = llista.toJson();
		return Response.ok().entity(result).build();
	}

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDade(@PathParam("id") String id){
//			if (llista == null ) {
        CustomersCodi negoci = new CustomersCodi(Application.getConnexio());
        CustomersModel am = negoci.select(Integer.parseInt(id));
        //            }
        String result = am.toJson();
        return Response.ok().entity(result).build();
    }

}
