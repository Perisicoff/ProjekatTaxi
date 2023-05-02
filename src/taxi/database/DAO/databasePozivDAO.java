package taxi.database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import taxi.DAO.PozivDAO;
import taxi.model.Poziv;

public class databasePozivDAO implements PozivDAO {

	private final Connection conn;

	public databasePozivDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public void add(Poziv poziv) throws Exception {

		String sql = "INSERT INTO pozivi (id, datumIVreme, ulica, broj, voziloId) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int param = 0;
			stmt.setLong(++param, poziv.getId());
			stmt.setTimestamp(++param, Timestamp.valueOf(poziv.getDatumIVreme()));
			stmt.setString(++param, poziv.getUlica());
			stmt.setInt(++param, poziv.getBroj());
			stmt.setLong(++param, poziv.getVozilo().getId());

			stmt.executeUpdate();
		}
	}

}
