package stand.lemonade.dal;

import java.util.Date;
import java.util.List;

import stand.lemonade.entities.Shift;

public interface ShiftDal {

	/**
	 * @param id
	 * @return
	 */
	List<Shift> getShiftByUserId(long userId);

	/**
	 * @param shift
	 */
	void addShift(Shift shift);

	/**
	 * @param shift
	 */
	void endShift(Shift shift);

	/**
	 * @param id
	 * @return
	 */
	Shift getShiftById(long id);

	/**
	 * @return
	 */
	public List<Shift> getAllShifts();

	/**
	 * @param startDate
	 * @param finishDate
	 * @return
	 */
	List<Shift> getHistoryData(Date startDate, Date finishDate);

}
