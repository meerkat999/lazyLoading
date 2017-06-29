package co.com.techandsolve.lazyloading.service.Impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import co.com.techandsolve.lazyloading.core.LazyLoadingCore;
import co.com.techandsolve.lazyloading.domain.Archivo;
import co.com.techandsolve.lazyloading.domain.Ejecucion;
import co.com.techandsolve.lazyloading.repository.IEjecucionRepository;
import co.com.techandsolve.lazyloading.service.IEjecucionService;
import co.com.techandsolve.lazyloading.utils.ParseUtil;

@RequestScoped
public class EjecucionServiceImpl implements IEjecucionService {
	
	private static final int NUMERO_DE_EJECUCIONES_POR_PAGINA = 10;
	
	@Inject
	private IEjecucionRepository repository;
	
	@Override
	@Transactional(value=TxType.REQUIRED, rollbackOn=Exception.class) 
	public Ejecucion calculateMovements(Archivo archivo) {
		Ejecucion ejecucion = null;
		byte[] archivo_data = archivo.getArchivo_data();
		if(archivo_data != null){
			List<Integer> lineasNumericas = ParseUtil.bytesToListInteger(archivo_data);
			List<Integer> respuestas = new LazyLoadingCore().iniciarCalculo(lineasNumericas);
			ejecucion = new Ejecucion();
			ejecucion.setArchivo(archivo);
			ejecucion.setCliente(archivo.getCliente());
			ejecucion.setMovimientoJson(ParseUtil.listToJson(respuestas));
			ejecucion = repository.save(ejecucion);
		}
		return ejecucion;
	}
	
	@Override
	public List<Ejecucion> getAllEjecutionsForPage(Integer page){
		Pageable pageable = new PageRequest(page, NUMERO_DE_EJECUCIONES_POR_PAGINA);
		return repository.findAll(pageable).getContent();
	}
	
	@Override
	public Long getNumItems(){
		return repository.count();
	}
	
}
