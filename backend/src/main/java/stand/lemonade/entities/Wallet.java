package stand.lemonade.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * The persistent class for the wallets database table.
 * 
 */
@Entity
@Table(name = "wallets")
@NamedQuery(name = "Wallet.findAll", query = "SELECT w FROM Wallet w")
public class Wallet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "availability_flag")
	private Boolean availabilityFlag;

	@Column(name = "delete_flag")
	private Boolean deleteFlag;

	@NotNull
	private String name;

	// bi-directional many-to-one association to Shift
	@OneToMany(mappedBy = "wallet")
	private List<Shift> shifts;

	public Wallet() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAvailabilityFlag() {
		return this.availabilityFlag;
	}

	public void setAvailabilityFlag(Boolean availabilityFlag) {
		this.availabilityFlag = availabilityFlag;
	}

	public Boolean getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Shift> getShifts() {
		return this.shifts;
	}

	public void setShifts(List<Shift> shifts) {
		this.shifts = shifts;
	}

	public Shift addShift(Shift shift) {
		getShifts().add(shift);
		shift.setWallet(this);

		return shift;
	}

	public Shift removeShift(Shift shift) {
		getShifts().remove(shift);
		shift.setWallet(null);

		return shift;
	}

	@Override
	public String toString() {
		return "Wallet [id=" + id + ", availabilityFlag=" + availabilityFlag + ", deleteFlag=" + deleteFlag + ", name="
				+ name + ", shifts=" + shifts + "]";
	}

}