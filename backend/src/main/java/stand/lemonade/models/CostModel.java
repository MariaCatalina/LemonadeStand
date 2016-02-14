package stand.lemonade.models;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.annotation.Nonnegative;
import javax.validation.constraints.NotNull;

import stand.lemonade.entities.Cost;

public class CostModel {

	private long id;
	private long shiftId;
	@Nonnegative
	private double sum;
	@NotNull
	private String description;
	private String date;

	public CostModel(Cost entity) {
		this.id = entity.getId();
		this.shiftId = entity.getShift().getId();
		this.sum = entity.getSum();
		this.date = dateFormat(entity.getDate());
		this.description = entity.getDescription();
	}

	public CostModel() {

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String desription) {
		this.description = desription;
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
		return "CostModel [id=" + id + ", shiftId=" + shiftId + ", sum=" + sum + ", description=" + description
				+ ", date=" + date + "]";
	}

}
