package stand.lemonade.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.google.inject.Inject;

import stand.lemonade.entities.Cost;

public class CostDalImpl implements CostDal {
	@Inject
	private EntityManager em;

	@Override
	public List<Cost> getAllCosts() {
		TypedQuery<Cost> query = em.createQuery("FROM Cost AS c", Cost.class);
		return query.getResultList();
	}

	@Override
	public List<Cost> getAllCostsByShiftId(long shiftId) {
		TypedQuery<Cost> query = em.createQuery("FROM Cost c WHERE c.shift.id = :shiftId ", Cost.class);
		query.setParameter("shiftId", shiftId);
		return query.getResultList();
	}

	@Override
	public Cost createCost(Cost cost) {
		em.persist(cost);
		return cost;
	}
}
