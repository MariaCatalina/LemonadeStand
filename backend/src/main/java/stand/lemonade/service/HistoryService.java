package stand.lemonade.service;

import java.util.List;

import stand.lemonade.models.HistoryIntervalModel;
import stand.lemonade.models.HistoryModel;

public interface HistoryService {

	/**
	 * @param dateModel
	 * @return
	 */
	public List<HistoryModel> getHistoryData(HistoryIntervalModel dateModel);
}
