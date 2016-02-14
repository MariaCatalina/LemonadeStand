package stand.lemonade.service;

import java.util.List;

import stand.lemonade.exceptions.LemonadeException;
import stand.lemonade.models.CostModel;

public interface CostService {

	/**
	 * @param costModel
	 * @return
	 * @throws LemonadeException
	 */
	CostModel createCosts(CostModel costModel) throws LemonadeException;

	/**
	 * @param id
	 * @return
	 */
	/**
	 * @return
	 */
	List<CostModel> getAllCosts();

	/**
	 * @param shiftId
	 * @return
	 */
	List<CostModel> getAllCostsByShiftId(long shiftId);
}
