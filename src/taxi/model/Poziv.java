package taxi.model;

import java.time.LocalDateTime;
import java.util.Objects;

import taxi.util.Konzola;

public class Poziv {

	private long id;
	private LocalDateTime datumIVreme = LocalDateTime.now();
	private String ulica = "";
	private int broj;

	Vozilo vozilo;

	public Poziv() {
		super();
	}

	public Poziv(LocalDateTime datumIVreme, String ulica, int broj, Vozilo vozilo) {
		super();
		this.datumIVreme = datumIVreme;
		this.ulica = ulica;
		this.broj = broj;
		this.vozilo = vozilo;
	}

	public Poziv(long id, LocalDateTime datumIVreme, String ulica, int broj, Vozilo vozilo) {
		super();
		this.id = id;
		this.datumIVreme = datumIVreme;
		this.ulica = ulica;
		this.broj = broj;
		this.vozilo = vozilo;
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
		Poziv other = (Poziv) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Poziv ID: " + id + "   Datum i vreme: " + (datumIVreme == LocalDateTime.MIN ? "/" : Konzola.formatiraj(datumIVreme)) + "   Ulica: " + ulica + "   Broj: " + broj + "   Broj vozila: "
				+ vozilo.getBroj();
	}

	public boolean isDatumUOpsegu(LocalDateTime pocetni, LocalDateTime krajnji) {
		return datumIVreme.compareTo(pocetni) >= 0 && datumIVreme.compareTo(krajnji) <= 0;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getDatumIVreme() {
		return datumIVreme;
	}

	public void setDatumIVreme(LocalDateTime datumIVreme) {
		this.datumIVreme = datumIVreme;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public int getBroj() {
		return broj;
	}

	public void setBroj(int broj) {
		this.broj = broj;
	}

	public Vozilo getVozilo() {
		return vozilo;
	}

	public void setVozilo(Vozilo vozilo) {
		this.vozilo = vozilo;
		vozilo.pozivi.add(this);
	}

}
