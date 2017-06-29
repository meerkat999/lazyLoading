package co.com.techandsolve.lazyloading.service;

import java.util.List;

import co.com.techandsolve.lazyloading.domain.Archivo;
import co.com.techandsolve.lazyloading.domain.Ejecucion;

public interface IEjecucionService {

	Ejecucion calculateMovements(Archivo archivo);
	List<Ejecucion> getAllEjecutionsForPage(Integer page);
	Long getNumItems();

}
