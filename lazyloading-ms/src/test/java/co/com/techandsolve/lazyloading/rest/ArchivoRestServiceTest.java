package co.com.techandsolve.lazyloading.rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import co.com.techandsolve.lazyloading.constants.ConstantesErrores;
import co.com.techandsolve.lazyloading.constants.ConstantesMultipartForm;
import co.com.techandsolve.lazyloading.domain.Archivo;
import co.com.techandsolve.lazyloading.dto.ArchivoDTO;
import co.com.techandsolve.lazyloading.service.IArchivoService;

@RunWith(MockitoJUnitRunner.class)
public class ArchivoRestServiceTest {

	private static final Long CEDULA = 1L;
	private static final String FILE = "sd";
	
	@Mock
	private Archivo archivo;

	@Mock
	private MultipartFormDataInput formDataInput;
	
	@InjectMocks
	private ArchivoRestService service;
	
	@Mock
	private InputPart inputPartFile;
	
	@Mock
	private InputPart inputPartCedula;

	@Mock
	private IArchivoService archivoService;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Before
	public void init() throws IOException{

		Map<String, List<InputPart>> mapInputs = new HashMap<>();
		mapInputs.put(ConstantesMultipartForm.INPUTPART_NAME_FILE, Arrays.asList(inputPartFile));
		mapInputs.put(ConstantesMultipartForm.INPUTPART_NAME_CEDULA, Arrays.asList(inputPartCedula));
		
		InputStream inputStream = new ByteArrayInputStream(FILE.getBytes());
		Mockito.when(inputPartFile.getBody(InputStream.class, null)).thenReturn(inputStream);
		Mockito.when(inputPartCedula.getBody(Long.class, null)).thenReturn(CEDULA);
		
		Mockito.when(formDataInput.getFormDataMap()).thenReturn(mapInputs);
		Mockito.when(archivoService.saveAndGenerateMovements(Mockito.any(ArchivoDTO.class))).thenReturn(archivo);
	}
	
	@Test
	public void verificarLlamadaASaveAndGenerate(){
		service.saveAndGenerateMovements(formDataInput);
		Mockito.verify(archivoService).saveAndGenerateMovements(Mockito.any(ArchivoDTO.class));
	}
	
	@Test
	public void noDebeDeLlamarSiHayUnErrorDeParseoEnLaCedula() throws IOException{
		Mockito.when(inputPartCedula.getBody(Long.class, null)).thenThrow(new IOException());
		
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage(ConstantesErrores.PARSE_ERROR_LONG);
		
		service.saveAndGenerateMovements(formDataInput);
	}
	
	@Test
	public void noDebeDeLlamarSiHayUnErrorDeParseoEnElArchivo() throws IOException{
		Mockito.when(inputPartFile.getBody(InputStream.class, null)).thenThrow(new IOException());
		
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage(ConstantesErrores.PARSE_ERROR_FILE);
		
		service.saveAndGenerateMovements(formDataInput);
	}
	
}
