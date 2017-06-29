package co.com.techandsolve.lazyloading.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;

public class InputPartUtil {

	private final static Logger LOGGER = Logger.getLogger(InputPartUtil.class);
	
	public static String getStringFromInputPart(List<InputPart> inputParts){
		String string = null;
		try {
			string = inputParts.stream().findFirst().get().getBody(String.class, null);
		} catch (IOException e) {
			LOGGER.error("Error capturando el string desde el inputpart.");
			LOGGER.error(e.getMessage());
		}
		return string;
	}
	
	public static byte[] getBytesFromFileFromInputPart(List<InputPart> inputParts) {
		byte[] byteArray = null;
		try {
			InputStream streamFile = inputParts.stream().findFirst().get().getBody(InputStream.class, null);
			byteArray = IOUtils.toByteArray(streamFile);
		} catch (IOException e) {
			LOGGER.error("Error conviertiendo el archivo al arreglo de bytes.");
			LOGGER.error(e.getMessage());
		}
		return byteArray;
	}
	
}
