package co.com.techandsolve.lazyloading.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "archivo")
public class Archivo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "archivo_id")
	private Integer archivo_id;
	
	@Column(name = "archivo_data")
	private byte[] archivo_data;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	public Integer getArchivo_id() {
		return archivo_id;
	}

	public void setArchivo_id(Integer archivo_id) {
		this.archivo_id = archivo_id;
	}

	public byte[] getArchivo_data() {
		return archivo_data;
	}

	public void setArchivo_data(byte[] archivo_data) {
		this.archivo_data = archivo_data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
