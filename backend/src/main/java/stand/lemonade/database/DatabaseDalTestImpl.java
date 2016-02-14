package stand.lemonade.database;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

public class DatabaseDalTestImpl implements DatabaseDalTest {

	@Inject
	private EntityManager em;

	@Override
	@Transactional
	public void deleteDbData() {

		deleteIncomeTableData();

		deleteCostsTableData();

		deleteShiftsTableData();

		deleteWalletsTableData();

		deleteUsersTableData();

	}

	@Override
	public void deleteCostsTableData() {
		em.createQuery("DELETE FROM Cost").executeUpdate();
	}

	@Override
	public void deleteIncomeTableData() {
		em.createQuery("DELETE FROM Income").executeUpdate();
	}

	@Override
	public void deleteShiftsTableData() {
		em.createQuery("DELETE FROM Shift").executeUpdate();
	}

	@Override
	public void deleteUsersTableData() {
		em.createQuery("DELETE FROM User").executeUpdate();
	}

	@Override
	public void deleteWalletsTableData() {
		em.createQuery("DELETE FROM Wallet").executeUpdate();
	}
}
