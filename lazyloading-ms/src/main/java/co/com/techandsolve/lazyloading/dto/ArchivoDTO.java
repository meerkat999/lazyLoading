package co.com.techandsolve.lazyloading.dto;

import java.io.Serializable;

public class ArchivoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private byte[] archivo_data;
	
	private Long cliente_id;
	
	private Integer archivo_id;

	public byte[] getArchivo_data() {
		return archivo_data;
	}

	public void setArchivo_data(byte[] archivo_data) {
		this.archivo_data = archivo_data;
	}

	public Long getCliente_id() {
		return cliente_id;
	}

	public void setCliente_id(Long cliente_id) {
		this.cliente_id = cliente_id;
	}

	public Integer getArchivo_id() {
		return archivo_id;
	}

	public void setArchivo_id(Integer archivo_id) {
		this.archivo_id = archivo_id;
	}

}
