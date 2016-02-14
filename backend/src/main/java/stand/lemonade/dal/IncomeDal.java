package stand.lemonade.dal;

import java.util.List;

import stand.lemonade.entities.Income;

public interface IncomeDal {

	/**
	 * @param id
	 * @return
	 */
	List<Income> getAllIncome();

	/**
	 * @param shiftId
	 * @return
	 */
	List<Income> getIncomeByShiftId(long shiftId);

	/**
	 * @param income
	 * @return
	 */
	Income createIncome(Income income);

}
