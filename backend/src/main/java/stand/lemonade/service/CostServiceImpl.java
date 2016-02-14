package stand.lemonade.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import stand.lemonade.dal.CostDal;
import stand.lemonade.dal.ShiftDal;
import stand.lemonade.entities.Cost;
import stand.lemonade.entities.Shift;
import stand.lemonade.exceptions.ErrorCodes;
import stand.lemonade.exceptions.LemonadeException;
import stand.lemonade.models.CostModel;

public class CostServiceImpl implements CostService {

	@Inject
	private CostDal costDal;

	@Inject
	private ShiftDal shiftDal;

	@Override
	public List<CostModel> getAllCosts() {
		List<Cost> incomeEntity = costDal.getAllCosts();

		List<CostModel> models = new ArrayList<CostModel>();

		/* convert entity to model */
		for (Cost i : incomeEntity) {
			CostModel costModel = new CostModel(i);
			models.add(costModel);
		}

		return models;
	}

	@Override
	public List<CostModel> getAllCostsByShiftId(long shiftId) {
		List<Cost> incomeEntity = costDal.getAllCostsByShiftId(shiftId);

		List<CostModel> models = new ArrayList<CostModel>();

		/* convert entity to model */
		for (Cost i : incomeEntity) {
			CostModel costModel = new CostModel(i);
			models.add(costModel);
		}

		return models;
	}

	@Override
	@Transactional
	public CostModel createCosts(CostModel costModel) throws LemonadeException {

		Cost costEntity = new Cost();
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());

		/* set values with receive values */
		costEntity.setSum(costModel.getSum());
		costEntity.setDate(time);
		costEntity.setDescription(costModel.getDescription());

		/* set shift */
		Shift shift = shiftDal.getShiftById(costModel.getShiftId());
		if (shift == null)
			throw new LemonadeException(ErrorCodes.SHIFT_NOT_FOUND);

		costEntity.setShift(shift);
		Cost costCreated = costDal.createCost(costEntity);

		/* return created user */
		CostModel costModelCreated = new CostModel(costCreated);
		return costModelCreated;
	}

}
