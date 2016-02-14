package stand.lemonade.models;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class HistoryIntervalModel {

	@NotNull
	@NotEmpty
	private String startDate;

	@NotNull
	@NotEmpty
	private String finishDate;

	public HistoryIntervalModel() {

	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	@Override
	public String toString() {
		return "HistoryIntervalModel [startDate=" + startDate + ", finishDate=" + finishDate + "]";
	}

}
