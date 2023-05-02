package taxi.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Vozilo {

	private long id;
	private String broj = "";
	private String vozac = "";

	final Set<Poziv> pozivi = new HashSet<>();

	public Vozilo() {
		super();
	}

	public Vozilo(String broj, String vozac) {
		super();
		this.broj = broj;
		this.vozac = vozac;
	}

	public Vozilo(long id, String broj, String vozac) {
		super();
		this.id = id;
		this.broj = broj;
		this.vozac = vozac;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vozilo other = (Vozilo) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Vozilo ID: " + id + "   Broj vozila: " + broj + "   Vozac: " + vozac;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}

	public String getVozac() {
		return vozac;
	}

	public void setVozac(String vozac) {
		this.vozac = vozac;
	}

	public Collection<Poziv> getPozivi() {
		return Collections.unmodifiableCollection(pozivi);
	}

	public void addPoziv(Poziv poziv) {
		pozivi.add(poziv);
		poziv.vozilo = this;
	}

	public void addSvePozive(Collection<Poziv> pozivi) {
		pozivi.addAll(pozivi);
		for (Poziv poziv : pozivi) {
			poziv.vozilo = this;
		}
	}

	public void removePoziv(Poziv poziv) {
		poziv.vozilo = null;
		pozivi.remove(poziv);
	}

	public void removeSvePozive() {
		for (Poziv poziv : pozivi) {
			poziv.vozilo = null;
		}
		pozivi.clear();
	}

}
