package stand.lemonade.service;

import stand.lemonade.exceptions.LemonadeException;
import stand.lemonade.models.EndShiftModel;
import stand.lemonade.models.ShiftModel;
import stand.lemonade.models.StartShiftModel;

public interface ShiftService {

	/**
	 * @param userId
	 * @return
	 * @throws LemonadeException
	 */
	ShiftModel getShiftByUserId(long userId) throws LemonadeException;

	/**
	 * @param shiftModel
	 * @return
	 * @throws LemonadeException
	 */
	StartShiftModel addShift(StartShiftModel shiftModel) throws LemonadeException;

	/**
	 * @param shiftModel
	 * @return
	 * @throws LemonadeException
	 */
	EndShiftModel endShift(EndShiftModel shiftModel) throws LemonadeException;

	/**
	 * @param shiftId
	 * @return
	 * @throws LemonadeException
	 */
	EndShiftModel getShiftData(long shiftId) throws LemonadeException;
}
