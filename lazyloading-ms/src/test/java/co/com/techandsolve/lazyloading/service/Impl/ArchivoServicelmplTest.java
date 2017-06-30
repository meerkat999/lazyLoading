package co.com.techandsolve.lazyloading.service.Impl;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import co.com.techandsolve.lazyloading.constants.ConstantesErrores;
import co.com.techandsolve.lazyloading.domain.Archivo;
import co.com.techandsolve.lazyloading.domain.Cliente;
import co.com.techandsolve.lazyloading.domain.Ejecucion;
import co.com.techandsolve.lazyloading.dto.ArchivoDTO;
import co.com.techandsolve.lazyloading.repository.IArchivoRepository;
import co.com.techandsolve.lazyloading.service.IClienteService;
import co.com.techandsolve.lazyloading.service.IEjecucionService;

@RunWith(MockitoJUnitRunner.class)
public class ArchivoServicelmplTest {

	private static final Integer fileID = 1;

	private static final Long clienteID = 2L;

	@Mock
	private Ejecucion ejecucion;

	@Mock
	private Archivo archivo;

	@Mock
	private Cliente cliente;

	private byte[] dataFile;

	@Mock
	private ArchivoDTO archivoDTO;
	
	@Mock
	private IClienteService clienteService;
	
	@Mock
	private IArchivoRepository repository;
	
	@Mock
	private IEjecucionService movimientoService;
	
	@InjectMocks
	private ArchivoServicelmpl service;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Before
	public void init(){
		dataFile = "asd".getBytes();
		when(archivoDTO.getArchivo_data()).thenReturn(dataFile);
		when(archivoDTO.getArchivo_id()).thenReturn(fileID);
		when(archivoDTO.getCliente_id()).thenReturn(clienteID);
		when(clienteService.save(cliente)).thenReturn(cliente);
		when(repository.save(archivo)).thenReturn(archivo);
		when(movimientoService.calculateMovements(archivo)).thenReturn(ejecucion);
	}
	
	@Test
	public void debeGuardarYGenerarLosMovimientos(){
		service.saveAndGenerateMovements(archivoDTO);
	}
	
	@Test
	public void debeDeFallarSiAlgunCampoDelDTOEstalla(){
		
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage(ConstantesErrores.MOVIMIENTO_ERROR_DTO);
		
		when(archivoDTO.getCliente_id()).thenReturn(null);
		service.saveAndGenerateMovements(archivoDTO);
	}
	
}
