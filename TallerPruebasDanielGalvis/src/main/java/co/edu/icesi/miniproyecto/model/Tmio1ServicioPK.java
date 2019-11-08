package co.edu.icesi.miniproyecto.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import co.edu.icesi.miniproyecto.validation.ValidateDate;

/**
 * The primary key class for the tmio1_servicios database table.
 * 
 */
@Embeddable
public class Tmio1ServicioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name="id_ruta", insertable=false, updatable=false)
	private Integer idRuta;

	@NotNull
	@Column(name="cedula_conductor", insertable=false, updatable=false)
	private String cedulaConductor;

	@NotNull
	@Column(name="id_bus", insertable=false, updatable=false)
	private Integer idBus;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(groups = ValidateDate.class)
	@NotNull
	@Column(name="fecha_inicio")
	private LocalDate fechaInicio;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	@Column(name="fecha_fin")
	private LocalDate fechaFin;

	public Tmio1ServicioPK() {
	}
	public Integer getIdRuta() {
		return this.idRuta;
	}
	public void setIdRuta(Integer idRuta) {
		this.idRuta = idRuta;
	}
	public String getCedulaConductor() {
		return this.cedulaConductor;
	}
	public void setCedulaConductor(String cedulaConductor) {
		this.cedulaConductor = cedulaConductor;
	}
	public Integer getIdBus() {
		return this.idBus;
	}
	public void setIdBus(Integer idBus) {
		this.idBus = idBus;
	}
	public LocalDate getFechaInicio() {
		return this.fechaInicio;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDate getFechaFin() {
		return this.fechaFin;
	}
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Tmio1ServicioPK)) {
			return false;
		}
		Tmio1ServicioPK castOther = (Tmio1ServicioPK)other;
		return 
			this.idRuta.equals(castOther.idRuta)
			&& this.cedulaConductor.equals(castOther.cedulaConductor)
			&& this.idBus.equals(castOther.idBus)
			&& this.fechaInicio.equals(castOther.fechaInicio)
			&& this.fechaFin.equals(castOther.fechaFin);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idRuta.hashCode();
		hash = hash * prime + this.cedulaConductor.hashCode();
		hash = hash * prime + this.idBus.hashCode();
		hash = hash * prime + this.fechaInicio.hashCode();
		hash = hash * prime + this.fechaFin.hashCode();
		
		return hash;
	}
}