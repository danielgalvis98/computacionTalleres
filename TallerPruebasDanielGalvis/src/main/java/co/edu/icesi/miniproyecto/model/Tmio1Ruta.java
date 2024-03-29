package co.edu.icesi.miniproyecto.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.NonNull;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the tmio1_rutas database table.
 * 
 */
@Entity
@Table(name="tmio1_rutas")
@NamedQuery(name="Tmio1Ruta.findAll", query="SELECT t FROM Tmio1Ruta t")
public class Tmio1Ruta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TMIO1_RUTAS_ID_GENERATOR", sequenceName="TMIO1_RUTAS_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TMIO1_RUTAS_ID_GENERATOR")
	private Integer id;

	@NotBlank
	private String activa;

	@NotBlank
	private String descripcion;

	@Column(name="dia_fin")
	@Max(7)
	@Min(1)
	@NotNull
	private BigDecimal diaFin;

	@Column(name="dia_inicio")
	@Max(7)
	@Min(1)
	@NotNull
	private BigDecimal diaInicio;

	@Column(name="hora_fin")
	@Max(86400)
	@Min(1)
	@NotNull
	private BigDecimal horaFin;

	@Column(name="hora_inicio")
	@Max(86400)
	@Min(1)
	@NotNull
	private BigDecimal horaInicio;

	@NonNull
	@NotBlank
	private String numero;

	//bi-directional many-to-one association to Tmio1Servicio
	@OneToMany(mappedBy="tmio1Ruta")
	@JsonIgnore
	private List<Tmio1Servicio> tmio1Servicios;

	//bi-directional many-to-one association to Tmio1ServiciosSitio
	@OneToMany(mappedBy="tmio1Ruta")
	@JsonIgnore
	private List<Tmio1ServiciosSitio> tmio1ServiciosSitios;

	//bi-directional many-to-one association to Tmio1SitiosRuta
	@OneToMany(mappedBy="tmio1Ruta1")
	@JsonIgnore
	private List<Tmio1SitiosRuta> tmio1SitiosRutas1;

	public Tmio1Ruta() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActiva() {
		return this.activa;
	}

	public void setActiva(String activa) {
		this.activa = activa;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getDiaFin() {
		return this.diaFin;
	}

	public void setDiaFin(BigDecimal diaFin) {
		this.diaFin = diaFin;
	}

	public BigDecimal getDiaInicio() {
		return this.diaInicio;
	}

	public void setDiaInicio(BigDecimal diaInicio) {
		this.diaInicio = diaInicio;
	}

	public BigDecimal getHoraFin() {
		return this.horaFin;
	}

	public void setHoraFin(BigDecimal horaFin) {
		this.horaFin = horaFin;
	}

	public BigDecimal getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(BigDecimal horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public List<Tmio1Servicio> getTmio1Servicios() {
		return this.tmio1Servicios;
	}

	public void setTmio1Servicios(List<Tmio1Servicio> tmio1Servicios) {
		this.tmio1Servicios = tmio1Servicios;
	}

	public Tmio1Servicio addTmio1Servicio(Tmio1Servicio tmio1Servicio) {
		getTmio1Servicios().add(tmio1Servicio);
		tmio1Servicio.setTmio1Ruta(this);

		return tmio1Servicio;
	}

	public Tmio1Servicio removeTmio1Servicio(Tmio1Servicio tmio1Servicio) {
		getTmio1Servicios().remove(tmio1Servicio);
		tmio1Servicio.setTmio1Ruta(null);

		return tmio1Servicio;
	}

	public List<Tmio1ServiciosSitio> getTmio1ServiciosSitios() {
		return this.tmio1ServiciosSitios;
	}

	public void setTmio1ServiciosSitios(List<Tmio1ServiciosSitio> tmio1ServiciosSitios) {
		this.tmio1ServiciosSitios = tmio1ServiciosSitios;
	}

	public Tmio1ServiciosSitio addTmio1ServiciosSitio(Tmio1ServiciosSitio tmio1ServiciosSitio) {
		getTmio1ServiciosSitios().add(tmio1ServiciosSitio);
		tmio1ServiciosSitio.setTmio1Ruta(this);

		return tmio1ServiciosSitio;
	}

	public Tmio1ServiciosSitio removeTmio1ServiciosSitio(Tmio1ServiciosSitio tmio1ServiciosSitio) {
		getTmio1ServiciosSitios().remove(tmio1ServiciosSitio);
		tmio1ServiciosSitio.setTmio1Ruta(null);

		return tmio1ServiciosSitio;
	}

	public List<Tmio1SitiosRuta> getTmio1SitiosRutas1() {
		return this.tmio1SitiosRutas1;
	}

	public void setTmio1SitiosRutas1(List<Tmio1SitiosRuta> tmio1SitiosRutas1) {
		this.tmio1SitiosRutas1 = tmio1SitiosRutas1;
	}

	public Tmio1SitiosRuta addTmio1SitiosRutas1(Tmio1SitiosRuta tmio1SitiosRutas1) {
		getTmio1SitiosRutas1().add(tmio1SitiosRutas1);
		tmio1SitiosRutas1.setTmio1Ruta1(this);

		return tmio1SitiosRutas1;
	}

	public Tmio1SitiosRuta removeTmio1SitiosRutas1(Tmio1SitiosRuta tmio1SitiosRutas1) {
		getTmio1SitiosRutas1().remove(tmio1SitiosRutas1);
		tmio1SitiosRutas1.setTmio1Ruta1(null);

		return tmio1SitiosRutas1;
	}


}