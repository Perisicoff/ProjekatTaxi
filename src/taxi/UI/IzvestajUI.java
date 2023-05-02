package taxi.UI;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import taxi.DAO.VoziloDAO;
import taxi.model.Poziv;
import taxi.model.StavkaIzvestaja;
import taxi.model.Vozilo;
import taxi.util.Konzola;

public class IzvestajUI {

	private static VoziloDAO voziloDAO;

	public static void setVoziloDAO(VoziloDAO voziloDAO) {
		IzvestajUI.voziloDAO = voziloDAO;
	}

	public static void izvestaj() {
		
		LocalDateTime pocetniDatum = Konzola.ocitajDateTime("Unesite pocetni datum pretrage: ");
		LocalDateTime krajnjiDatum = Konzola.ocitajDateTime("Unesite krajnji datum pretrage: ");

		try {
			Set<String> ulice = new LinkedHashSet<>();

			Collection<Vozilo> vozila = voziloDAO.getAll();
			for (Vozilo vozilo : vozila) {
				for (Poziv poziv : vozilo.getPozivi()) {
					ulice.add(poziv.getUlica());
				}
			}
			List<StavkaIzvestaja> stavke = new ArrayList<>();
			for (String ulica : ulice) {
				Vozilo voziloSaNajvisePoziva = null;
				int maxBrojPoziva = Integer.MIN_VALUE;
				for (Vozilo vozilo : vozila) {
					Collection<Poziv> poziviUulici = new ArrayList<>();
					for (Poziv poziv : vozilo.getPozivi()) {
						if (poziv.getUlica().equals(ulica) && poziv.isDatumUOpsegu(pocetniDatum, krajnjiDatum)) {
							poziviUulici.add(poziv);
							int brojPoziva = poziviUulici.size();
							if (brojPoziva > maxBrojPoziva) {
								maxBrojPoziva = brojPoziva;
								voziloSaNajvisePoziva = poziv.getVozilo();
							}
						}
					}
				}
				StavkaIzvestaja stavka = new StavkaIzvestaja(ulica, voziloSaNajvisePoziva, maxBrojPoziva);
				stavke.add(stavka);
			}
			stavke.sort(StavkaIzvestaja::compareBrojPoziva);
			try {
				String Headher = String.format("%-20s %-15s %-20s", "Ulica", "Broj vozila", "Broj poziva");
				System.out.println(Headher);
				System.out.println("==================== =============== ====================");
				for (StavkaIzvestaja stavkaIzvestaja : stavke) {
				String foother = String.format("%-20s %-15s %-20s", stavkaIzvestaja.getNazivUlice(), (stavkaIzvestaja.getVoziloSaNajvisePoziva() == null ? "/" : stavkaIzvestaja.getVoziloSaNajvisePoziva().getBroj()), (stavkaIzvestaja.getBrojPoziva() == Integer.MIN_VALUE ? "0" : stavkaIzvestaja.getBrojPoziva()));
				System.out.println(foother);
				System.out.println("-------------------- --------------- --------------------");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("dogodila se greska!");
			e.printStackTrace();
		}
	}
}
