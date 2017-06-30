package co.com.techandsolve.lazyloading.service;

import co.com.techandsolve.lazyloading.domain.Cliente;

public interface IClienteService {

	Cliente save(Cliente cliente);

	Cliente getByID(Long cedula);

	Cliente guardarCliente(Long cedula);
	
}
