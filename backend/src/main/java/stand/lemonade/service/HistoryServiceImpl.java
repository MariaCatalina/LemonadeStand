package stand.lemonade.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import stand.lemonade.dal.ShiftDal;
import stand.lemonade.entities.Shift;
import stand.lemonade.models.EndShiftModel;
import stand.lemonade.models.HistoryIntervalModel;
import stand.lemonade.models.HistoryModel;
import stand.lemonade.models.HistoryWalletModel;

public class HistoryServiceImpl implements HistoryService {

	@Inject
	private ShiftDal shiftDal;

	@Transactional
	@Override
	public List<HistoryModel> getHistoryData(HistoryIntervalModel dateModel) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		Date startDate = new Date();
		Date finishDate = new Date();

		try {

			startDate = formatter.parse(dateModel.getStartDate());
			finishDate = formatter.parse(dateModel.getFinishDate());

		} catch (Exception e) {
			e.printStackTrace();
		}

		List<Shift> entity = shiftDal.getHistoryData(startDate, finishDate);

		Iterator<Shift> it = entity.iterator();

		List<HistoryModel> historyData = new ArrayList<>();

		while (it.hasNext()) {
			Shift temp = it.next();

			HistoryWalletModel model = new HistoryWalletModel();
			
			Double incomesSum = EndShiftModel.getIncomesSum(temp);
			Double costsSum = EndShiftModel.getCostsSum(temp);
			
			model.setShiftId(temp.getId());
			model.setWaiterName(temp.getUser().getEmail());
			model.setStartSum(temp.getStartSum());
			model.setIncomes(incomesSum);
			model.setCosts(costsSum);
			model.setBankSum(temp.getBankSum());
			
			if(temp.getBankSum() == null || temp.getFinishSum() == null || incomesSum == null) {
				model.setDelta(null);
			} else {
				model.setDelta(EndShiftModel.getDelta(temp, incomesSum, costsSum));
			}
			
			model.setFinalSum(temp.getFinishSum());
			
			if(temp.getStartDate() == null) {
				model.setStartShiftTime(null);
			} else {
				model.setStartShiftTime(dateFormat(temp.getStartDate()));
			}
			
			if(temp.getFinishDate() == null) {
				model.setEndShiftTime(null);
			} else {
				model.setEndShiftTime(dateFormat(temp.getFinishDate()));
			}

			HistoryModel historyModel = new HistoryModel(temp.getWallet().getName());

			if (historyData.contains(historyModel)) {
				int idx = historyData.indexOf(historyModel);

				historyData.get(idx).getData().add(model);
			} else {
				historyModel.getData().add(model);
				historyData.add(historyModel);
			}

		}

		return historyData;
	}
	
	private String dateFormat(Timestamp date) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		try {
			return formatter.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return date.toString();
	}

}