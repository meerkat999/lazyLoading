package co.com.techandsolve.lazyloading.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import co.com.techandsolve.lazyloading.service.Impl.EjecucionServiceImpl;

public class ParseUtil {

	private final static Logger LOGGER = Logger.getLogger(EjecucionServiceImpl.class);
	
	public static String convertFileToString(byte[] bytes) {
		String file = null;
		try {
			file = new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Ha ocurrido un error leyendo el archivo desde bd.");
		}
		return file;
	}
	
	public static String listToJson(List<?> list){
		return new Gson().toJson(list);
	}
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    return true;
	}
	
	public static List<Integer> stringToListInteger(String string){
		List<Integer> lineasNumericas = new ArrayList<>();
		try {
			String[] lineas = string.split("\\r\\n|\\n|\\r");
			List<String> lineasAlfanumericas = Arrays.asList(lineas);
			lineasAlfanumericas.stream().forEach(l -> {
				lineasNumericas.add(Integer.parseInt(l));
			});
		} catch (Exception e) {
			throw new RuntimeException("Error parseando el archivo. Archivo incorrecto.");
		}
		return lineasNumericas;
	}
	
	public static List<Integer> bytesToListInteger(byte[] bytes){
		return stringToListInteger(convertFileToString(bytes));
	}
	
}
