package co.com.techandsolve.lazyloading.service.Impl;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.com.techandsolve.lazyloading.domain.Archivo;
import co.com.techandsolve.lazyloading.domain.Cliente;
import co.com.techandsolve.lazyloading.domain.Ejecucion;
import co.com.techandsolve.lazyloading.repository.IEjecucionRepository;

@RunWith(MockitoJUnitRunner.class)
public class EjecucionServiceImplTest {

	private static final String JSON_ESPERADO = "[2,1,2,3,8]";

	private static final Long NUM_ELEMENTS = 3L;

	@Mock
	private Cliente cliente;

	@Mock
	private Archivo archivo;

	@Mock
	private Page<Ejecucion> pageEjecuciones;

	private Integer page = 1;

	@InjectMocks
	private EjecucionServiceImpl service;
	
	@Mock
	private Ejecucion ejecucion;
	
	@Mock
	private IEjecucionRepository repository;
	
	@Before
	public void init(){
		when(repository.count()).thenReturn(NUM_ELEMENTS);
		when(repository.findAll(Mockito.any(Pageable.class))).thenReturn(pageEjecuciones);
		List<Ejecucion> listaEjecuciones = Arrays.asList(ejecucion);
		when(pageEjecuciones.getContent()).thenReturn(listaEjecuciones);
		when(archivo.getArchivo_data()).thenReturn(Base64.decodeBase64("NQ0KNA0KMzANCjMwDQoxDQoxDQozDQoyMA0KMjANCjIwDQoxMQ0KMQ0KMg0KMw0KNA0KNQ0KNg0KNw0KOA0KOQ0KMTANCjExDQo2DQo5DQoxOQ0KMjkNCjM5DQo0OQ0KNTkNCjEwDQozMg0KNTYNCjc2DQo4DQo0NA0KNjANCjQ3DQo4NQ0KNzENCjkx"));
		when(archivo.getCliente()).thenReturn(cliente);
		when(repository.save(Mockito.any(Ejecucion.class))).thenReturn(ejecucion);
		when(ejecucion.getMovimientoJson()).thenReturn(JSON_ESPERADO);
	}
	
	@Test
	public void debeObtenerElNumeroDeEjecuciones(){
		Long numItems = service.getNumItems();
		Assert.assertTrue(numItems == NUM_ELEMENTS);
	}
	
	@Test
	public void debeObtenerEjecucionesPorPagina(){
		List<Ejecucion> allEjecutionsForPage = service.getAllEjecutionsForPage(page);
		Assert.assertNotNull(allEjecutionsForPage);
		Assert.assertTrue(allEjecutionsForPage.size()>0);
	}
	
	@Test
	public void debeCalcularLosMovimientos(){
		Ejecucion ejecucion = service.calculateMovements(archivo);
		Assert.assertNotNull(ejecucion);
		Assert.assertEquals(ejecucion.getMovimientoJson(), JSON_ESPERADO);
	}
	
	@Test
	public void noDebeCalcularLosMovimientosSiArchivoNull(){
		when(archivo.getArchivo_data()).thenReturn(null);
		Ejecucion ejecucion = service.calculateMovements(archivo);
		Assert.assertNull(ejecucion);
	}
	
}
