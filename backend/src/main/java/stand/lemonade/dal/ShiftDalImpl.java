package stand.lemonade.dal;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import com.google.inject.Inject;

import stand.lemonade.entities.Shift;

public class ShiftDalImpl implements ShiftDal {

	@Inject
	private EntityManager em;

	@Override
	public List<Shift> getShiftByUserId(long userId) {
		return em.createQuery("FROM Shift AS s WHERE s.user.id = :userId and s.bankSum IS NULL", Shift.class)
				.setParameter("userId", userId).getResultList();
	}

	@Override
	public void addShift(Shift shift) {
		em.persist(shift);
	}

	public void endShift(Shift shift) {
		em.persist(shift);
	}

	@Override
	public Shift getShiftById(long id) {
		return em.find(Shift.class, id);
	}

	@Override
	public List<Shift> getAllShifts() {
		return em.createQuery("FROM Shift AS s", Shift.class).getResultList();
	}

	@Override
	public List<Shift> getHistoryData(Date startDate, Date finishDate) {

		 finishDate.setHours(23);
		 finishDate.setMinutes(59);
		 finishDate.setSeconds(59);

		TypedQuery<Shift> query = em
				.createQuery("FROM Shift AS s WHERE s.startDate BETWEEN :startDate and :finishDate ORDER BY s.startDate ASC", Shift.class)
				.setParameter("startDate", startDate, TemporalType.TIMESTAMP)
				.setParameter("finishDate", finishDate, TemporalType.TIMESTAMP);

		return query.getResultList();
	}

}
