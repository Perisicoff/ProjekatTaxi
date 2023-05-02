package taxi.database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import taxi.DAO.VoziloDAO;
import taxi.model.Poziv;
import taxi.model.Vozilo;

public class databaseVoziloDAO implements VoziloDAO {

	private final Connection conn;

	public databaseVoziloDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public Vozilo get(String broj) throws Exception {
		Vozilo vozilo = null;

		String sql = "SELECT v.id, v.vozac, p.id, p.datumIVreme, p.ulica, p.broj FROM taxi.vozila v LEFT JOIN taxi.pozivi p ON v.id = p.voziloId WHERE v.broj = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int param = 0;
			stmt.setString(++param, broj);
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int kolona = 0;
					long vId = rset.getLong(++kolona);
					String vVozac = rset.getString(++kolona);

					if (vozilo == null) {
						vozilo = new Vozilo(vId, broj, vVozac);
					}

					long pId = rset.getLong(++kolona);
					if (pId != 0) {
						LocalDateTime datum = rset.getTimestamp(++kolona).toLocalDateTime();
						String ulica = rset.getString(++kolona);
						int pBroj = rset.getInt(++kolona);
						Poziv poziv = new Poziv(pId, datum, ulica, pBroj, vozilo);
						vozilo.addPoziv(poziv);
					}
				}
			}
		}

		return vozilo;
	}

	@Override
	public Collection<Vozilo> getAll() throws Exception {
		Map<Long, Vozilo> vozila = new LinkedHashMap<>();

		String sql = "SELECT v.id, v.broj, v.vozac, p.id, p.datumIVreme, p.ulica, p.broj FROM taxi.vozila v LEFT JOIN taxi.pozivi p ON v.id = p.voziloId";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int kolona = 0;
					long vId = rset.getLong(++kolona);
					String vBroj = rset.getString(++kolona);
					String vVozac = rset.getString(++kolona);

					Vozilo vozilo = vozila.get(vId);
					if (vozilo == null) {
						vozilo = new Vozilo(vId, vBroj, vVozac);
					}
					long pId = rset.getLong(++kolona);
					if (pId != 0) {
						LocalDateTime datum = rset.getTimestamp(++kolona).toLocalDateTime();
						String ulica = rset.getString(++kolona);
						int broj = rset.getInt(++kolona);
						Poziv poziv = new Poziv(pId, datum, ulica, broj, vozilo);
						vozilo.addPoziv(poziv);
						vozila.put(vId, vozilo);
					}
				}
			}
		}

		return vozila.values();
	}

}
