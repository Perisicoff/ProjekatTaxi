package taxi.UI;

import java.time.LocalDateTime;
import taxi.DAO.PozivDAO;
import taxi.model.Poziv;
import taxi.model.Vozilo;
import taxi.util.Konzola;

public class PozivUI {

	private static PozivDAO pozivDAO;

	public static void setPozivDAO(PozivDAO pozivDAO) {
		PozivUI.pozivDAO = pozivDAO;
	}

	public static void noviPoziv() {
		VoziloUI.prikazSvihVozila();
		Vozilo vozilo = VoziloUI.pretragaVozila();
		if (vozilo == null) {
			return;
		}
		String ulica = "";
		while (ulica.equals("")) {
			ulica = Konzola.ocitajString("Unesite naziv ulice: ");
		}
		int broj = Konzola.ocitajInt("Unesite broj: ");
		LocalDateTime datum = LocalDateTime.now();
		Poziv poziv = new Poziv(datum, ulica, broj, vozilo);
		try {
			pozivDAO.add(poziv);
			System.out.println("Uspesno ste dodali novi poziv!");
		} catch (Exception e) {
			System.out.println("Dogodila se greska!");
			e.printStackTrace();
		}

	}
}
