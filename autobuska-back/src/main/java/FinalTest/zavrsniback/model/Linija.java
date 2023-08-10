package FinalTest.zavrsniback.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Linija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private int brojMesta;

	@Column
	private double cenaKarte;

	@Column
	private String vremePolaska = "";

	@Column(nullable = false)
	private String destinacija = "";

	@ManyToOne
	private Prevoznik prevoznik;

	@OneToMany(mappedBy = "linija", cascade = CascadeType.ALL)
	private Set<Rezervacija> rezervacije = new HashSet<>();

	public Linija() {
	}

	public Linija(int brojMesta, double cenaKarte, String vremePolaska, String destinacija, Prevoznik prevoznik,
			Set<Rezervacija> rezervacije) {
		this.brojMesta = brojMesta;
		this.cenaKarte = cenaKarte;
		this.vremePolaska = vremePolaska;
		this.destinacija = destinacija;
		this.prevoznik = prevoznik;
		this.rezervacije = rezervacije;
	}

	public Linija(Long id, int brojMesta, double cenaKarte, String vremePolaska, String destinacija,
			Prevoznik prevoznik, Set<Rezervacija> rezervacije) {
		this.id = id;
		this.brojMesta = brojMesta;
		this.cenaKarte = cenaKarte;
		this.vremePolaska = vremePolaska;
		this.destinacija = destinacija;
		this.prevoznik = prevoznik;
		this.rezervacije = rezervacije;
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
	 * @return the prevoznik
	 */
	public Prevoznik getPrevoznik() {
		return prevoznik;
	}

	/**
	 * @param prevoznik the prevoznik to set
	 */
	public void setPrevoznik(Prevoznik prevoznik) {
		this.prevoznik = prevoznik;
	}

	/**
	 * @return the rezervacije
	 */
	public Set<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	/**
	 * @param rezervacije the rezervacije to set
	 */
	public void setRezervacije(Set<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
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
		if (!(obj instanceof Linija)) {
			return false;
		}
		Linija other = (Linija) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Linija [id=" + id + ", brojMesta=" + brojMesta + ", cenaKarte=" + cenaKarte + ", vremePolaska="
				+ vremePolaska + ", destinacija=" + destinacija + ", prevoznik=" + prevoznik + ", rezervacije="
				+ rezervacije + "]";
	}

}