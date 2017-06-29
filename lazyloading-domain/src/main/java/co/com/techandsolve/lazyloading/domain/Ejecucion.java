package co.com.techandsolve.lazyloading.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ejecucion")
public class Ejecucion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ejecucion_id")
	private Integer id;
	
	@Column(name = "ejecucion_json_mov")
	private String movimientoJson;
	
	@ManyToOne
	@JoinColumn(name = "archivo_id")
	private Archivo archivo;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMovimientoJson() {
		return movimientoJson;
	}

	public void setMovimientoJson(String movimientoJson) {
		this.movimientoJson = movimientoJson;
	}

	public Archivo getArchivo() {
		return archivo;
	}

	public void setArchivo(Archivo archivo) {
		this.archivo = archivo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
