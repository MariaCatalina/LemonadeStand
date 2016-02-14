package stand.lemonade.models;

import javax.annotation.Nonnegative;
import javax.validation.constraints.NotNull;

import stand.lemonade.entities.Cost;
import stand.lemonade.entities.Income;
import stand.lemonade.entities.Shift;

public class EndShiftModel {

	@NotNull
	private long shiftId;

	@NotNull
	@Nonnegative
	private Double costsSum;

	@NotNull
	@Nonnegative
	private Double incomesSum;

	@NotNull
	@Nonnegative
	private Double finishSum;

	@NotNull
	@Nonnegative
	private Double bankSum;

	public EndShiftModel() {
	}

	public EndShiftModel(Shift shift) {
		this.setCostsSum(EndShiftModel.getCostsSum(shift));
		this.setIncomesSum(EndShiftModel.getIncomesSum(shift));
	}

	public Double getCostsSum() {
		return costsSum;
	}

	public void setCostsSum(Double costsSum) {
		this.costsSum = costsSum;
	}

	public Double getIncomesSum() {
		return incomesSum;
	}

	public void setIncomesSum(Double incomesSum) {
		this.incomesSum = incomesSum;
	}

	public Double getBankSum() {
		return bankSum;
	}

	public void setBankSum(Double bankSum) {
		this.bankSum = bankSum;
	}

	public Double getFinishSum() {
		return finishSum;
	}

	public void setFinishSum(Double finishSum) {
		this.finishSum = finishSum;
	}

	public long getShiftId() {
		return shiftId;
	}

	public void setShiftId(long shiftId) {
		this.shiftId = shiftId;
	}

	public static Double getCostsSum(Shift shift) {
		double sum = 0;

		if (shift.getCosts() == null) {
			return new Double(0);
		}

		for (Cost cost : shift.getCosts()) {
			sum += cost.getSum();
		}

		return new Double(sum);
	}

	public static Double getIncomesSum(Shift shift) {
		double sum = 0;

		if (shift.getIncomes() == null) {
			return new Double(0);
		}

		for (Income income : shift.getIncomes()) {
			sum += income.getSum();
		}

		return new Double(sum);
	}
	
	public static Double getDelta(Shift shift, Double incomesSum, Double costsSum) {
		return new Double(shift.getStartSum() + incomesSum.doubleValue() - costsSum.doubleValue() - shift.getFinishSum() - shift.getBankSum());
	}

	@Override
	public String toString() {
		return "EndShiftModel [shiftId=" + shiftId + ", costsSum=" + costsSum + ", incomesSum=" + incomesSum
				+ ", finishSum=" + finishSum + ", bankSum=" + bankSum + "]";
	}

}
