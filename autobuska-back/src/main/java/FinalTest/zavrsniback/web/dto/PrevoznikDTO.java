package FinalTest.zavrsniback.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class PrevoznikDTO {

	@Positive(message = "Id mora biti pozitivan broj.")
	private Long id;

	@NotBlank(message = "Naziv prevoznika nije zadat.")
	private String naziv = "";

	@NotBlank(message = "Adresa prevoznika nije zadat.")
	private String adresa = "";

	@NotBlank(message = "Pib prevoznika nije zadat.")
	private String pib = "";

	public PrevoznikDTO() {
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
	 * @return the naziv
	 */
	public String getNaziv() {
		return naziv;
	}

	/**
	 * @param naziv the naziv to set
	 */
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	/**
	 * @return the adresa
	 */
	public String getAdresa() {
		return adresa;
	}

	/**
	 * @param adresa the adresa to set
	 */
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	/**
	 * @return the pib
	 */
	public String getPib() {
		return pib;
	}

	/**
	 * @param pib the pib to set
	 */
	public void setPib(String pib) {
		this.pib = pib;
	}

}
