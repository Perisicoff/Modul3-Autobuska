package FinalTest.zavrsniback.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class LinijaDTO {

	@Positive(message = "Id mora biti pozitivan broj.")
	private Long id;

	@Positive(message = "Broj mesta mora biti pozitivan broj.")
	private int brojMesta;

	private double cenaKarte;

	private String vremePolaska = "";

	@NotBlank(message = "Destinacija nije zadat.")
	@NotNull
	private String destinacija = "";

	private Long prevoznikId;

	private String nazivPrevoznik = "";

	public LinijaDTO() {
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the brojMesta
	 */
	public int getBrojMesta() {
		return brojMesta;
	}

	/**
	 * @param brojMesta the brojMesta to set
	 */
	public void setBrojMesta(int brojMesta) {
		this.brojMesta = brojMesta;
	}

	/**
	 * @return the cenaKarte
	 */
	public double getCenaKarte() {
		return cenaKarte;
	}

	/**
	 * @param cenaKarte the cenaKarte to set
	 */
	public void setCenaKarte(double cenaKarte) {
		this.cenaKarte = cenaKarte;
	}

	/**
	 * @return the vremePolaska
	 */
	public String getVremePolaska() {
		return vremePolaska;
	}

	/**
	 * @param vremePolaska the vremePolaska to set
	 */
	public void setVremePolaska(String vremePolaska) {
		this.vremePolaska = vremePolaska;
	}

	/**
	 * @return the destinacija
	 */
	public String getDestinacija() {
		return destinacija;
	}

	/**
	 * @param destinacija the destinacija to set
	 */
	public void setDestinacija(String destinacija) {
		this.destinacija = destinacija;
	}

	/**
	 * @return the prevoznikId
	 */
	public Long getPrevoznikId() {
		return prevoznikId;
	}

	/**
	 * @param prevoznikId the prevoznikId to set
	 */
	public void setPrevoznikId(Long prevoznikId) {
		this.prevoznikId = prevoznikId;
	}

	/**
	 * @return the nazivPrevoznik
	 */
	public String getNazivPrevoznik() {
		return nazivPrevoznik;
	}

	/**
	 * @param nazivPrevoznik the nazivPrevoznik to set
	 */
	public void setNazivPrevoznik(String nazivPrevoznik) {
		this.nazivPrevoznik = nazivPrevoznik;
	}

}
