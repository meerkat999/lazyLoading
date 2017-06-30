package co.com.techandsolve.lazyloading.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import co.com.techandsolve.lazyloading.service.IEjecucionService;

@RunWith(MockitoJUnitRunner.class)
public class EjecucionRestServiceTest {
	
	private static final Integer PAGINA = 1;
	
	@Mock
	private IEjecucionService service;
	
	@InjectMocks
	private EjecucionRestService serviceRest;
	
	@Test
	public void verificarLlamadaAGetAllEjecutionsForPage(){
		serviceRest.getForPage(PAGINA);
		Mockito.verify(service).getAllEjecutionsForPage(PAGINA);
	}
	
	@Test
	public void verificarLlamadaAGetNumEjecutions(){
		serviceRest.getNumEjecutions();
		Mockito.verify(service).getNumItems();
	}

}
