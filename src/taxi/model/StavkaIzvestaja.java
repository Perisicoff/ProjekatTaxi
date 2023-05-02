package taxi.model;

public class StavkaIzvestaja {

	private String nazivUlice = "";
	private Vozilo voziloSaNajvisePoziva;
	private int brojPoziva;

	public StavkaIzvestaja(String nazivVozila, Vozilo voziloSaNajvisePoziva, int brojPoziva) {
		super();
		this.nazivUlice = nazivVozila;
		this.voziloSaNajvisePoziva = voziloSaNajvisePoziva;
		this.brojPoziva = brojPoziva;
	}

	@Override
	public String toString() {
		return "Ulica: " + nazivUlice + "   Vozilo sa najvise poziva: "
				+ (voziloSaNajvisePoziva == null ? "/" : voziloSaNajvisePoziva.getBroj()) + "   Broj poziva: " + (brojPoziva == Integer.MIN_VALUE ? "0" : brojPoziva);
	}

	public static int compareBrojPoziva(StavkaIzvestaja stavka1, StavkaIzvestaja stavka2) {
		return -Integer.compare(stavka1.brojPoziva, stavka2.brojPoziva);

	}

	public String getNazivUlice() {
		return nazivUlice;
	}

	public Vozilo getVoziloSaNajvisePoziva() {
		return voziloSaNajvisePoziva;
	}

	public int getBrojPoziva() {
		return brojPoziva;
	}

}
