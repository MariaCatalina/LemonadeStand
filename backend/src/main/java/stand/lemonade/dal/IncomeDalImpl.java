package stand.lemonade.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.google.inject.Inject;

import stand.lemonade.entities.Income;

public class IncomeDalImpl implements IncomeDal {
	@Inject
	private EntityManager em;

	@Override
	public List<Income> getAllIncome() {
		TypedQuery<Income> query = em.createQuery("FROM Income AS i", Income.class);
		return query.getResultList();
		// return null;
	}

	@Override
	public List<Income> getIncomeByShiftId(long shiftId) {
		TypedQuery<Income> query = em.createQuery("FROM Income i WHERE i.shift.id = :shiftId ", Income.class);
		query.setParameter("shiftId", shiftId);
		return query.getResultList();
	}

	@Override
	public Income createIncome(Income income) {
		em.persist(income);
		return income;
	}

}
