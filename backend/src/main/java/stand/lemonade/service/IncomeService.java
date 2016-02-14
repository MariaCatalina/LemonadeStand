package stand.lemonade.service;

import java.util.List;

import stand.lemonade.exceptions.LemonadeException;
import stand.lemonade.models.IncomeModel;

public interface IncomeService {

	/**
	 * @param incomeModel
	 * @return
	 * @throws LemonadeException
	 */
	IncomeModel createIncome(IncomeModel incomeModel) throws LemonadeException;

	/**
	 * @return
	 * @throws LemonadeException
	 */
	List<IncomeModel> getAllIncome();

	/**
	 * @param id
	 * @return
	 */
	List<IncomeModel> getAllIncomeByShiftId(long id);

}
