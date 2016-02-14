package stand.lemonade.models;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.annotation.Nonnegative;

import stand.lemonade.entities.Income;

public class IncomeModel {

	private long id;
	private long shiftId;
	@Nonnegative
	private double sum;
	private String date;

	public IncomeModel() {

	}

	public IncomeModel(Income entity) {
		this.shiftId = entity.getShift().getId();
		this.id = entity.getId();
		this.sum = entity.getSum();
		this.date = dateFormat(entity.getDate());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getShiftId() {
		return shiftId;
	}

	public void setShiftId(long shiftId) {
		this.shiftId = shiftId;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public String dateFormat(Timestamp date) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		try {
			return formatter.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return date.toString();
	}

	@Override
	public String toString() {
		return "IncomeModel [id=" + id + ", shiftId=" + shiftId + ", sum=" + sum + ", date=" + date + "]";
	}

}
