package stand.lemonade.models;

public class HistoryWalletModel {

	private long shiftId;

	private String waiterName;

	private Double startSum;

	private Double incomes;

	private Double costs;

	private Double finalSum;

	private Double bankSum;

	private Double delta;
	
	private String startShiftTime;
	
	public String getStartShiftTime() {
		return startShiftTime;
	}

	public void setStartShiftTime(String startShiftTime) {
		this.startShiftTime = startShiftTime;
	}

	public String getEndShiftTime() {
		return endShiftTime;
	}

	public void setEndShiftTime(String endShiftTime) {
		this.endShiftTime = endShiftTime;
	}

	private String endShiftTime;

	public HistoryWalletModel() {

	}

	public long getShiftId() {
		return shiftId;
	}

	public void setShiftId(long shiftId) {
		this.shiftId = shiftId;
	}

	public String getWaiterName() {
		return waiterName;
	}

	public void setWaiterName(String waiterName) {
		this.waiterName = waiterName;
	}

	public Double getStartSum() {
		return startSum;
	}

	public void setStartSum(Double startSum) {
		this.startSum = startSum;
	}

	public Double getIncomes() {
		return incomes;
	}

	public void setIncomes(Double incomes) {
		this.incomes = incomes;
	}

	public Double getCosts() {
		return costs;
	}

	public void setCosts(Double costs) {
		this.costs = costs;
	}

	public Double getFinalSum() {
		return finalSum;
	}

	public void setFinalSum(Double finalSum) {
		this.finalSum = finalSum;
	}

	public Double getBankSum() {
		return bankSum;
	}

	public void setBankSum(Double bankSum) {
		this.bankSum = bankSum;
	}

	public Double getDelta() {
		return delta;
	}

	public void setDelta(Double delta) {
		this.delta = delta;
	}

	@Override
	public String toString() {
		return "HistoryWalletModel [shiftId=" + shiftId + ", waiterName=" + waiterName + ", startSum=" + startSum
				+ ", incomes=" + incomes + ", costs=" + costs + ", finalSum=" + finalSum + ", bankSum=" + bankSum
				+ ", delta=" + delta + "]";
	}

}
