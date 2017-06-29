package co.com.techandsolve.lazyloading.rest;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.management.RuntimeErrorException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import co.com.techandsolve.lazyloading.domain.Archivo;
import co.com.techandsolve.lazyloading.dto.ArchivoDTO;
import co.com.techandsolve.lazyloading.service.IArchivoService;
import co.com.techandsolve.lazyloading.utils.InputPartUtil;

@Path("/archivoService")
public class ArchivoRestService {
	
	@Inject
	private IArchivoService service;
	
	@POST
	@Path("/saveAndGenerateMovements")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public ArchivoDTO saveAndGenerateMovements(MultipartFormDataInput formDataInput){
		ArchivoDTO archivoDTO = getInfo(formDataInput);
		validarDTO(archivoDTO);
		Archivo archivo = service.saveAndGenerateMovements(archivoDTO);
		if(archivo != null){
			archivoDTO.setArchivo_id(archivo.getArchivo_id());
		}
		return archivoDTO;
	}

	private ArchivoDTO getInfo(MultipartFormDataInput formDataInput) {
		Map<String, List<InputPart>> uploadForm = formDataInput.getFormDataMap();
		ArchivoDTO archivoDTO = new ArchivoDTO();
		archivoDTO.setArchivo_data(InputPartUtil.getBytesFromFileFromInputPart(uploadForm.get("file")));
		archivoDTO.setCliente_id(InputPartUtil.getStringFromInputPart(uploadForm.get("cedula")));
		return archivoDTO;
	}
	
	private void validarDTO(ArchivoDTO archivoDTO) {
		if(archivoDTO.getArchivo_data() == null || archivoDTO.getCliente_id() == null || archivoDTO.getCliente_id() == ""){
			throw new RuntimeErrorException(new Error("DTO Incompleto"), "Error al recibir el dto del archivo. Tiene campos nulos");
		}
	}
	
	
}
