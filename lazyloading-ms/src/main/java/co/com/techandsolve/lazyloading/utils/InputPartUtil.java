package co.com.techandsolve.lazyloading.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;

import co.com.techandsolve.lazyloading.constants.ConstantesErrores;

public class InputPartUtil {

	private final static Logger LOGGER = Logger.getLogger(InputPartUtil.class);
	
	public static Long getLongFromInputPart(List<InputPart> inputParts){
		Long result = null;
		try {
			result = inputParts.stream().findFirst().get().getBody(Long.class, null);
		} catch (IOException e) {
			LOGGER.error(ConstantesErrores.PARSE_ERROR_LONG);
			LOGGER.error(e.getMessage());
			throw new RuntimeException(ConstantesErrores.PARSE_ERROR_LONG);
		}
		return result;
	}
	
	public static byte[] getBytesFromFileFromInputPart(List<InputPart> inputParts) {
		byte[] byteArray = null;
		try {
			InputStream streamFile = inputParts.stream().findFirst().get().getBody(InputStream.class, null);
			byteArray = IOUtils.toByteArray(streamFile);
		} catch (IOException e) {
			LOGGER.error(ConstantesErrores.PARSE_ERROR_FILE);
			LOGGER.error(e.getMessage());
			throw new RuntimeException(ConstantesErrores.PARSE_ERROR_FILE);
		}
		return byteArray;
	}
	
}
