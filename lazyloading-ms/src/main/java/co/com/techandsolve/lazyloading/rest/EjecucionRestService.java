package co.com.techandsolve.lazyloading.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.techandsolve.lazyloading.domain.Ejecucion;
import co.com.techandsolve.lazyloading.service.IEjecucionService;

@Path("/ejecucionService")
public class EjecucionRestService {

	@Inject
	private IEjecucionService service;
	
	@GET
	@Path("/getEjecutions/{pagina}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Ejecucion> getForPage(@PathParam("pagina") Integer pagina){
		List<Ejecucion> listaEjecuciones = service.getAllEjecutionsForPage(pagina);
		return listaEjecuciones;
	}
	
	@GET
	@Path("/getNumEjecutions/")
	@Produces(MediaType.APPLICATION_JSON)
	public Long getNumEjecutions(){
		return service.getNumItems();
	}
	
}
