package FinalTest.zavrsniback.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Prevoznik {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String naziv = "";

	@Column
	private String adresa = "";

	@Column(unique = true, nullable = false)
	private String pib = "";

	@OneToMany(mappedBy = "prevoznik")
	private Set<Linija> linije = new HashSet<>();

	public Prevoznik() {
	}

	public Prevoznik(String naziv, String adresa, String pib, Set<Linija> linije) {
		this.naziv = naziv;
		this.adresa = adresa;
		this.pib = pib;
		this.linije = linije;
	}

	public Prevoznik(Long id, String naziv, String adresa, String pib, Set<Linija> linije) {
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.pib = pib;
		this.linije = linije;
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

	/**
	 * @return the linije
	 */
	public Set<Linija> getLinije() {
		return linije;
	}

	/**
	 * @param linije the linije to set
	 */
	public void setLinije(Set<Linija> linije) {
		this.linije = linije;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Prevoznik)) {
			return false;
		}
		Prevoznik other = (Prevoznik) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Prevoznik [id=" + id + ", naziv=" + naziv + ", adresa=" + adresa + ", pib=" + pib + ", linije=" + linije
				+ "]";
	}

}
