package stand.lemonade.service;

import java.util.List;

import stand.lemonade.exceptions.LemonadeException;
import stand.lemonade.models.WalletModel;

public interface WalletService {

	/**
	 * @return
	 */
	List<WalletModel> getAllWallets();

	/**
	 * @return
	 */
	List<WalletModel> getAvailableWallets();

	/**
	 * @param id
	 * @return
	 * @throws LemonadeException
	 */
	WalletModel getWalletById(long id) throws LemonadeException;

	/**
	 * @param model
	 * @return
	 * @throws LemonadeException
	 */
	WalletModel createWallet(WalletModel model) throws LemonadeException;

	/**
	 * @param model
	 * @return
	 * @throws LemonadeException
	 */
	WalletModel updateWallet(WalletModel model) throws LemonadeException;

	/**
	 * @param walletId
	 * @throws LemonadeException
	 */
	void deleteWallet(long walletId) throws LemonadeException;

}
