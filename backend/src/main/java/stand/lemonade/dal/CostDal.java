package stand.lemonade.dal;

import java.util.List;

import stand.lemonade.entities.Cost;

public interface CostDal {

	/**
	 * @return
	 */
	List<Cost> getAllCosts();

	/**
	 * @param shiftId
	 * @return
	 */
	List<Cost> getAllCostsByShiftId(long shiftId);

	/**
	 * @param cost
	 * @return
	 */
	Cost createCost(Cost cost);
}
