package co.com.techandsolve.lazyloading.service.Impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import co.com.techandsolve.lazyloading.domain.Cliente;
import co.com.techandsolve.lazyloading.repository.IClienteRepository;
import co.com.techandsolve.lazyloading.service.IClienteService;

@RequestScoped
public class ClienteServiceImpl implements IClienteService {
	
	@Inject
	private IClienteRepository repository;
	
	@Override
	@Transactional(value=TxType.REQUIRED, rollbackOn=Exception.class) 
	public Cliente save(Cliente cliente) {
		return repository.save(cliente);
	}

	@Override
	public Cliente getByID(Long cedula) {
		return repository.findOne(cedula);
	}
	
	@Override
	public Cliente guardarCliente(Long cedula) {
		Cliente cliente = getByID(cedula);
		if(cliente == null && cedula != null){
			cliente = new Cliente();
			cliente.setId(cedula);
			cliente = save(cliente);
		}
		return cliente;
	}

}
