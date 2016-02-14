package stand.lemonade.models;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import stand.lemonade.entities.Wallet;

public class WalletModel {

	private Long id;

	@NotNull
	private String name;

	private String availabilityFlag;

	public WalletModel() {
	}

	public WalletModel(Wallet entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.availabilityFlag = convertAvailabilityFlagToString(entity.getAvailabilityFlag());
	}

	public WalletModel(Long id, String name, Boolean availabilityFlag) {
		this.id = id;
		this.name = name;
		this.availabilityFlag = convertAvailabilityFlagToString(availabilityFlag);
	}

	@JsonProperty
	public String getAvailabilityFlag() {
		return availabilityFlag;
	}

	@JsonProperty
	public void setAvailabilityFlag(String availabilityFlag) {
		this.availabilityFlag = availabilityFlag;
	}

	@JsonProperty
	public Long getId() {
		return id;
	}

	@JsonProperty
	public void setId(Long id) {
		this.id = id;
	}

	@JsonProperty
	public String getName() {
		return name;
	}

	@JsonProperty
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "WalletModel [id=" + id + ", name=" + name + ", availabilityFlag=" + availabilityFlag + "]";
	}
	
	public String convertAvailabilityFlagToString(Boolean availabilityFlag) {
		if(availabilityFlag != null && availabilityFlag.booleanValue()){
			return "Yes";
		} else {
			return "No";
		}
	}

}