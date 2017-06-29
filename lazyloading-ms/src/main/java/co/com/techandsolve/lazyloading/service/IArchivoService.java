package co.com.techandsolve.lazyloading.service;

import co.com.techandsolve.lazyloading.domain.Archivo;
import co.com.techandsolve.lazyloading.dto.ArchivoDTO;

public interface IArchivoService {

	Archivo saveAndGenerateMovements(ArchivoDTO archivoDTO);


}
