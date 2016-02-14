package stand.lemonade.models;

import javax.annotation.Nonnegative;
import javax.validation.constraints.NotNull;

import stand.lemonade.entities.Shift;

public class StartShiftModel {

	private long shiftId;

	@Nonnegative
	@NotNull
	private Double startSum;

	private long walletId;

	private long userId;

	public StartShiftModel() {
	}

	public StartShiftModel(Shift shift) {
		this.startSum = shift.getStartSum();
	}

	public Double getStartSum() {
		return startSum;
	}

	public void setStartSum(Double startSum) {
		this.startSum = startSum;
	}

	public long getWalletId() {
		return walletId;
	}

	public void setWalletId(long walletId) {
		this.walletId = walletId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getShiftId() {
		return shiftId;
	}

	public void setShiftId(long shiftId) {
		this.shiftId = shiftId;
	}

	@Override
	public String toString() {
		return "StartShiftModel [shiftId=" + shiftId + ", startSum=" + startSum + ", walletId=" + walletId + ", userId="
				+ userId + "]";
	}

}
