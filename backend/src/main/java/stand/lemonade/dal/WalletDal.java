package stand.lemonade.dal;

import java.util.List;

import stand.lemonade.entities.Wallet;
import stand.lemonade.exceptions.LemonadeException;

public interface WalletDal {

	/**
	 * @param deleteFlag
	 * @param availabilityFlag
	 * @return
	 * @throws LemonadeException
	 */
	List<Wallet> getWalletsByCriteria(boolean deleteFlag, boolean availabilityFlag);

	/**
	 * @param name
	 * @return
	 * @throws LemonadeException
	 */
	List<Wallet> getWalletByName(String name, boolean deleteFlag);

	/**
	 * @param id
	 * @return
	 * @throws LemonadeException
	 */
	Wallet getWalletById(long id);

	/**
	 * @param wallet
	 */
	void createWallet(Wallet wallet);

	/**
	 * @param wallet
	 */
	void updateWallet(Wallet wallet);

	/**
	 * @param wallet
	 */
	void deleteWallet(Wallet wallet);
}
