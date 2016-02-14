package stand.lemonade.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the costs database table.
 * 
 */
@Entity
@Table(name = "costs")
@NamedQuery(name = "Cost.findAll", query = "SELECT c FROM Cost c")
public class Cost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private Timestamp date;

	private String description;

	private double sum;

	// bi-directional many-to-one association to Shift
	@ManyToOne
	private Shift shift;

	public Cost() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getSum() {
		return this.sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public Shift getShift() {
		return this.shift;
	}

	public void setShift(Shift shift) {
		this.shift = shift;
	}

	@Override
	public String toString() {
		return "Cost [id=" + id + ", date=" + date + ", description=" + description + ", sum=" + sum + ", shift="
				+ shift + "]";
	}

}