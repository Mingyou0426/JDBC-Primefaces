package com.sistemes.ws;

 import com.sistemes.codi.ArticlesCodi;
 import com.sistemes.filtres.ArticlesFiltre;
 import com.sistemes.llistes.ArticlesLlista;
 import com.sistemes.Application;
 import com.sistemes.models.ArticlesModel;

 import javax.ws.rs.GET;
 import javax.ws.rs.PUT;
 import javax.ws.rs.Path;
 import javax.ws.rs.PathParam;
 import javax.ws.rs.Produces;
 import javax.ws.rs.core.Context;
 import javax.ws.rs.core.MediaType;
 import javax.ws.rs.core.Response;
 import javax.ws.rs.core.UriInfo;

@Path("Articles")
public class ArticlesWs {
	ArticlesLlista llista = null;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDades(@Context UriInfo info){
//			if (llista == null ) {
		ArticlesCodi negoci = new ArticlesCodi(Application.getConnexio());
		ArticlesFiltre filtre = new ArticlesFiltre(info.getQueryParameters());
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
        ArticlesCodi negoci = new ArticlesCodi(Application.getConnexio());
        ArticlesModel am = negoci.select(Integer.parseInt(id));
        //            }
        String result = am.toJson();
        return Response.ok().entity(result).build();
    }
}
