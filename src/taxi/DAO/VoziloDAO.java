package taxi.DAO;

import java.util.Collection;

import taxi.model.Vozilo;

public interface VoziloDAO {

	public Vozilo get(String broj) throws Exception;
	public Collection<Vozilo> getAll() throws Exception;
}
