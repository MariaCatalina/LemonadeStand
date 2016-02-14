package stand.lemonade.models;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class HistoryModel {

	@NotNull
	@NotEmpty
	private String walletName;

	@NotNull
	private List<HistoryWalletModel> data;

	public HistoryModel(String walletName) {
		this.walletName = walletName;
		data = new ArrayList<>();
	}

	public HistoryModel() {
		data = new ArrayList<>();
	}

	public String getWalletName() {
		return walletName;
	}

	public void setWalletName(String walletName) {
		this.walletName = walletName;
	}

	public List<HistoryWalletModel> getData() {
		return data;
	}

	public void setData(List<HistoryWalletModel> data) {
		this.data = data;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HistoryModel other = (HistoryModel) obj;
		if (walletName == null) {
			if (other.walletName != null)
				return false;
		} else if (!walletName.equals(other.walletName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HistoryModel [walletName=" + walletName + ", data=" + data + "]";
	}

}
