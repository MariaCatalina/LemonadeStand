package stand.lemonade.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import stand.lemonade.dal.ShiftDal;
import stand.lemonade.dal.UserDal;
import stand.lemonade.dal.WalletDal;
import stand.lemonade.entities.Shift;
import stand.lemonade.entities.User;
import stand.lemonade.entities.Wallet;
import stand.lemonade.exceptions.ErrorCodes;
import stand.lemonade.exceptions.LemonadeException;
import stand.lemonade.models.EndShiftModel;
import stand.lemonade.models.ShiftModel;
import stand.lemonade.models.StartShiftModel;

public class ShiftServiceImpl implements ShiftService {

	@Inject
	private WalletDal walletDal;

	@Inject
	private ShiftDal shiftDal;

	@Inject
	private UserDal userDal;

	@Transactional
	@Override
	public ShiftModel getShiftByUserId(long userId) throws LemonadeException {
		List<Shift> list = shiftDal.getShiftByUserId(userId);

		if (list.isEmpty()) {
			throw new LemonadeException(ErrorCodes.GENERIC_ENTITY_NOT_FOUND_ERROR);
		}

		Shift entity = list.get(0);

		if (entity == null) {
			throw new LemonadeException(ErrorCodes.SHIFT_NOT_FOUND);
		}

		ShiftModel model = new ShiftModel();
		model.setId(entity.getId());

		return model;
	}

	@Transactional
	@Override
	public StartShiftModel addShift(StartShiftModel shiftModel) throws LemonadeException {
		User user = userDal.getUserById(shiftModel.getUserId(), false);

		if (user == null) {
			throw new LemonadeException(ErrorCodes.USER_NOT_FOUND);
		}

		Wallet wallet = walletDal.getWalletById(shiftModel.getWalletId());

		if (wallet == null) {
			throw new LemonadeException(ErrorCodes.WALLET_NOT_FOUND);
		}

		if (!wallet.getAvailabilityFlag()) {
			throw new LemonadeException(ErrorCodes.NOT_AVAILABLE);
		}

		if (wallet.getDeleteFlag()) {
			throw new LemonadeException(ErrorCodes.WALLET_NOT_FOUND);
		}

		Shift shift = new Shift();
		Date date = new Date();

		shift.setStartDate(new Timestamp(date.getTime()));
		shift.setStartSum(shiftModel.getStartSum());
		shift.setUser(user);
		shift.setWallet(wallet);

		wallet.setAvailabilityFlag(false);

		walletDal.updateWallet(wallet);
		shiftDal.addShift(shift);

		shiftModel.setShiftId(this.getShiftByUserId(shiftModel.getUserId()).getId());

		return shiftModel;
	}

	@Transactional
	@Override
	public EndShiftModel getShiftData(long id) throws LemonadeException {
		Shift shift = shiftDal.getShiftById(id);

		if (shift == null) {
			throw new LemonadeException(ErrorCodes.SHIFT_NOT_FOUND);
		}

		EndShiftModel model = new EndShiftModel(shift);

		return model;
	}

	@Transactional
	@Override
	public EndShiftModel endShift(EndShiftModel shiftModel) throws LemonadeException {
		Shift shift = shiftDal.getShiftById(shiftModel.getShiftId());

		if (shift == null) {
			throw new LemonadeException(ErrorCodes.SHIFT_NOT_FOUND);
		}

		Wallet wallet = walletDal.getWalletById(shift.getWallet().getId());

		if (wallet == null) {
			throw new LemonadeException(ErrorCodes.WALLET_NOT_FOUND);
		}
			

		Date date = new Date();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		Date startDate = shift.getStartDate();
		Date finishDate = new Date();
		
		try {
			startDate = formatter.parse(shift.getStartDate().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			finishDate = formatter.parse(new Timestamp(date.getTime()).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(!startDate.equals(finishDate)) {
			shift.setFinishDate(new Timestamp(startDate.getTime()));
		} else {
			shift.setFinishDate(new Timestamp(date.getTime()));
		}
		
		shift.setBankSum(shiftModel.getBankSum());
		shift.setFinishSum(shiftModel.getFinishSum());

		wallet.setAvailabilityFlag(true);

		shiftDal.endShift(shift);
		walletDal.updateWallet(wallet);

		return shiftModel;
	}

}
