package stand.lemonade.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the shifts database table.
 * 
 */
@Entity
@Table(name = "shifts")
@NamedQuery(name = "Shift.findAll", query = "SELECT s FROM Shift s")
public class Shift implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "bank_sum")
	private Double bankSum;

	@Column(name = "finish_date")
	private Timestamp finishDate;

	@Column(name = "finish_sum")
	private Double finishSum;

	@Column(name = "start_date")
	private Timestamp startDate;

	@Column(name = "start_sum")
	private Double startSum;

	// bi-directional many-to-one association to Cost
	@OneToMany(mappedBy = "shift")
	private List<Cost> costs;

	// bi-directional many-to-one association to Income
	@OneToMany(mappedBy = "shift")
	private List<Income> incomes;

	// bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	// bi-directional many-to-one association to Wallet
	@ManyToOne
	private Wallet wallet;

	public Shift() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getBankSum() {
		return this.bankSum;
	}

	public void setBankSum(Double bankSum) {
		this.bankSum = bankSum;
	}

	public Timestamp getFinishDate() {
		return this.finishDate;
	}

	public void setFinishDate(Timestamp finishDate) {
		this.finishDate = finishDate;
	}

	public Double getFinishSum() {
		return this.finishSum;
	}

	public void setFinishSum(Double finishSum) {
		this.finishSum = finishSum;
	}

	public Timestamp getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Double getStartSum() {
		return this.startSum;
	}

	public void setStartSum(Double startSum) {
		this.startSum = startSum;
	}

	public List<Cost> getCosts() {
		return this.costs;
	}

	public void setCosts(List<Cost> costs) {
		this.costs = costs;
	}

	public Cost addCost(Cost cost) {
		getCosts().add(cost);
		cost.setShift(this);

		return cost;
	}

	public Cost removeCost(Cost cost) {
		getCosts().remove(cost);
		cost.setShift(null);

		return cost;
	}

	public List<Income> getIncomes() {
		return this.incomes;
	}

	public void setIncomes(List<Income> incomes) {
		this.incomes = incomes;
	}

	public Income addIncome(Income income) {
		getIncomes().add(income);
		income.setShift(this);

		return income;
	}

	public Income removeIncome(Income income) {
		getIncomes().remove(income);
		income.setShift(null);

		return income;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Wallet getWallet() {
		return this.wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	@Override
	public String toString() {
		return "Shift [id=" + id + ", bankSum=" + bankSum + ", finishDate=" + finishDate + ", finishSum=" + finishSum
				+ ", startDate=" + startDate + ", startSum=" + startSum + ", costs=" + costs + ", incomes=" + incomes
				+ ", user=" + user + ", wallet=" + wallet + "]";
	}

}