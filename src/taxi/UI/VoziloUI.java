package taxi.UI;

import java.util.Collection;
import taxi.DAO.VoziloDAO;
import taxi.model.Poziv;
import taxi.model.Vozilo;
import taxi.util.Konzola;

public class VoziloUI {

	private static VoziloDAO voziloDAO;

	public static void setVoziloDAO(VoziloDAO voziloDAO) {
		VoziloUI.voziloDAO = voziloDAO;
	}

	public static void prikazSvihVozila() {

		try {
			Collection<Vozilo> vozila = voziloDAO.getAll();
			String Headher = String.format("%-10s %-15s %-20s", "ID", "Broj", "Vozac");
			System.out.println(Headher);
			System.out.println("========== =============== ====================");
			for (Vozilo vozilo : vozila) {
				String foother = String.format("%-10s %-15s %-20s", vozilo.getId(), vozilo.getBroj(),
						vozilo.getVozac());
				System.out.println(foother);
				System.out.println("---------- --------------- --------------------");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void prikazJednogVozila() {
		prikazSvihVozila();
		Vozilo vozilo = pretragaVozila();
		if (vozilo == null) {
			return;
		} else {
			try {
				String Headher1 = String.format("%-10s %-20s %-20s", "ID", "Broj vozila", "Vozac");
				System.out.println(Headher1);
				System.out.println("========== ==================== ====================");
				String foother1 = String.format("%-10s %-20s %-20s", vozilo.getId(), vozilo.getBroj(),
						vozilo.getVozac());
				System.out.println(foother1);
				System.out.println("========== ==================== ====================");
				System.out.println();
				System.out.println();
				String Headher = String.format("%-10s %-20s %-20s %-10s", "ID", "Vreme poziva", "Ulica", "Broj");
				System.out.println(Headher);
				System.out.println("---------- -------------------- -------------------- ----------");
				for (Poziv poziv : vozilo.getPozivi()) {
					String foother = String.format("%-10s %-20s %-20s %-10s", poziv.getId(),
							Konzola.formatiraj(poziv.getDatumIVreme()), poziv.getUlica(), poziv.getBroj());
					System.out.println(foother);
					System.out.println("---------- -------------------- -------------------- ----------");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static Vozilo pretragaVozila() {
		String broj = Konzola.ocitajString("Unesite broj vozila:");
		Vozilo vozilo = null;
		try {
			vozilo = voziloDAO.get(broj);
			if (vozilo == null) {
				System.out.println("Vozilo ne postoji!");
			}
		} catch (Exception e) {
			System.out.println("Dogodila se greska");
			e.printStackTrace();
		}
		return vozilo;
	}

}
