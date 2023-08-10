package FinalTest.zavrsniback.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Rezervacija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Linija linija;

	public Rezervacija() {
	}

	public Rezervacija(Linija linija) {
		this.linija = linija;
	}

	public Rezervacija(Long id, Linija linija) {
		this.id = id;
		this.linija = linija;
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
	 * @return the linija
	 */
	public Linija getLinija() {
		return linija;
	}

	/**
	 * @param linija the linija to set
	 */
	public void setLinija(Linija linija) {
		this.linija = linija;
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
		if (!(obj instanceof Rezervacija)) {
			return false;
		}
		Rezervacija other = (Rezervacija) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Rezervacija [id=" + id + ", linija=" + linija + "]";
	}

}
