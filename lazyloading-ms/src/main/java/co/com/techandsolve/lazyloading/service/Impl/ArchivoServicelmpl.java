package co.com.techandsolve.lazyloading.service.Impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import co.com.techandsolve.lazyloading.domain.Archivo;
import co.com.techandsolve.lazyloading.domain.Cliente;
import co.com.techandsolve.lazyloading.dto.ArchivoDTO;
import co.com.techandsolve.lazyloading.repository.IArchivoRepository;
import co.com.techandsolve.lazyloading.service.IArchivoService;
import co.com.techandsolve.lazyloading.service.IClienteService;
import co.com.techandsolve.lazyloading.service.IEjecucionService;

@RequestScoped
public class ArchivoServicelmpl  implements IArchivoService {

	@Inject
	private IArchivoRepository repository;

	@Inject
	private IClienteService clienteService;
	
	@Inject
	private IEjecucionService movimientoService;
	
	@Override
	@Transactional(value=TxType.REQUIRED, rollbackOn=Exception.class) 
	public Archivo saveAndGenerateMovements(ArchivoDTO archivoDTO) {
		Archivo archivo = null;
		if(archivoDTO != null){
			archivo = persistEntity(archivoDTO);
			movimientoService.calculateMovements(archivo);
		}
		return archivo;
	}

	private Archivo persistEntity(ArchivoDTO archivoDTO) {
		Archivo archivo = new Archivo(); 
		archivo.setArchivo_data(archivoDTO.getArchivo_data());
		Cliente cliente = clienteService.guardarCliente(archivoDTO.getCliente_id());
		archivo.setCliente(cliente);
		archivo = save(archivo);
		return archivo;
	}

	private Archivo save(Archivo archivo) {
		return repository.save(archivo);
	}
	

}
