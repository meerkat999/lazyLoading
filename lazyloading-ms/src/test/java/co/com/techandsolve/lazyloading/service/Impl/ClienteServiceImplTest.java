package co.com.techandsolve.lazyloading.service.Impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

import co.com.techandsolve.lazyloading.domain.Cliente;
import co.com.techandsolve.lazyloading.repository.IClienteRepository;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceImplTest {

	private static final Long cedula = 123L;

	@Mock
	private Cliente cliente;
	
	@InjectMocks
	private ClienteServiceImpl service;
	
	@Mock
	private IClienteRepository repository;
	
	@Before
	public void init(){
		when(repository.findOne(cedula)).thenReturn(cliente);
		when(repository.save(cliente)).thenReturn(cliente);
	}
	
	@Test 
	public void debeGuardarUnClienteExistaoNo(){
		service.guardarCliente(cedula);
	}
	
	
}
