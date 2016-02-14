package stand.lemonade.models;

public class ShiftModel {

	private long id;

	public ShiftModel() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ShiftModel [id=" + id + "]";
	}

}
