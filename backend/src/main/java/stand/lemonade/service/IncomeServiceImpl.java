package stand.lemonade.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import stand.lemonade.dal.IncomeDal;
import stand.lemonade.dal.ShiftDal;
import stand.lemonade.entities.Income;
import stand.lemonade.entities.Shift;
import stand.lemonade.exceptions.ErrorCodes;
import stand.lemonade.exceptions.LemonadeException;
import stand.lemonade.models.IncomeModel;

public class IncomeServiceImpl implements IncomeService {
	@Inject
	private IncomeDal incomeDal;

	@Inject
	private ShiftDal shiftDal;

	@Override
	@Transactional
	public List<IncomeModel> getAllIncome() {
		List<Income> incomeEntity = incomeDal.getAllIncome();

		List<IncomeModel> models = new ArrayList<IncomeModel>();

		/* convert entity to model */
		for (Income i : incomeEntity) {
			IncomeModel incomeModel = new IncomeModel(i);
			models.add(incomeModel);
		}

		return models;
	}

	@Override
	@Transactional
	public List<IncomeModel> getAllIncomeByShiftId(long shitfId) {
		List<Income> incomeEntity = incomeDal.getIncomeByShiftId(shitfId);

		List<IncomeModel> models = new ArrayList<IncomeModel>();

		/* convert entity to model */
		for (Income i : incomeEntity) {
			IncomeModel incomeModel = new IncomeModel(i);
			models.add(incomeModel);
		}

		return models;
	}

	@Override
	@Transactional
	public IncomeModel createIncome(IncomeModel incomeModel) throws LemonadeException {
		Income incomeEntity = new Income();
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());

		/* set values with receive values */
		incomeEntity.setSum(incomeModel.getSum());
		incomeEntity.setDate(time);

		/* set shift */
		Shift shift = shiftDal.getShiftById(incomeModel.getShiftId());
		if (shift == null)
			throw new LemonadeException(ErrorCodes.SHIFT_NOT_FOUND);

		incomeEntity.setShift(shift);
		Income incomeCreated = incomeDal.createIncome(incomeEntity);

		/* return created user */
		IncomeModel incomeModelCreated = new IncomeModel(incomeCreated);
		return incomeModelCreated;
	}

}
