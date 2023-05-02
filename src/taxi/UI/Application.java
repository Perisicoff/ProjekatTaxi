package taxi.UI;

import java.sql.Connection;
import java.sql.DriverManager;
import taxi.DAO.PozivDAO;
import taxi.DAO.VoziloDAO;
import taxi.database.DAO.databasePozivDAO;
import taxi.database.DAO.databaseVoziloDAO;
import taxi.util.Meni;
import taxi.util.Meni.FunkcionalnaStavkaMenija;
import taxi.util.Meni.IzlaznaStavkaMenija;
import taxi.util.Meni.StavkaMenija;

public class Application {

	private static void initDatabase() throws Exception {
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/taxi?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Europe/Belgrade",
				"root", "root");

		PozivDAO pozivDAO = new databasePozivDAO(conn);
		VoziloDAO voziloDAO = new databaseVoziloDAO(conn);

		PozivUI.setPozivDAO(pozivDAO);
		VoziloUI.setVoziloDAO(voziloDAO);
		IzvestajUI.setVoziloDAO(voziloDAO);

	}

	static {
		try {
			initDatabase();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Greška pri povezivanju sa izvorom podataka!");

			System.exit(1);
		}
	}

		public static void main(String[] args) throws Exception {
			Meni.pokreni("Taxi", new StavkaMenija[] {
				new IzlaznaStavkaMenija("Izlaz"),
				new FunkcionalnaStavkaMenija("Prikaz svih vozila") {

					@Override
					public void izvrsi() { VoziloUI.prikazSvihVozila(); }
					
				}, 
				new FunkcionalnaStavkaMenija("Prikaz vozila sa svim pozivima") {

					@Override
					public void izvrsi() { VoziloUI.prikazJednogVozila(); }
					
				}, 
				new FunkcionalnaStavkaMenija("Dodavanje poziva") {

					@Override
					public void izvrsi() { PozivUI.noviPoziv(); }
					
				},
				new FunkcionalnaStavkaMenija("Izvestaj") {

					@Override
					public void izvrsi() { IzvestajUI.izvestaj(); }
					
				}
			});
		}


}
