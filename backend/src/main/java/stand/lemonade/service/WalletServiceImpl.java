package stand.lemonade.service;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import stand.lemonade.dal.WalletDal;
import stand.lemonade.entities.Wallet;
import stand.lemonade.exceptions.ErrorCodes;
import stand.lemonade.exceptions.LemonadeException;
import stand.lemonade.models.WalletModel;

public class WalletServiceImpl implements WalletService {

	@Inject
	private WalletDal walletDal;

	public WalletServiceImpl() {
	}

	@Transactional
	@Override
	public List<WalletModel> getAllWallets() {

		List<Wallet> wallets = walletDal.getWalletsByCriteria(false, false);

		List<WalletModel> models = new ArrayList<WalletModel>();

		for (Wallet w : wallets) {
			models.add(new WalletModel(w));
		}

		return models;
	}

	@Transactional
	@Override
	public List<WalletModel> getAvailableWallets() {
		List<Wallet> wallets = walletDal.getWalletsByCriteria(false, true);

		List<WalletModel> models = new ArrayList<WalletModel>();

		for (Wallet w : wallets) {
			models.add(new WalletModel(w));
		}

		return models;
	}

	@Transactional
	@Override
	public WalletModel createWallet(WalletModel model) throws LemonadeException {
		Wallet wallet = new Wallet();

		wallet.setName(model.getName());
		wallet.setAvailabilityFlag(true);
		wallet.setDeleteFlag(false);

		if (walletDal.getWalletByName(model.getName(), false).isEmpty()) {
			walletDal.createWallet(wallet);
		} else {
			throw new LemonadeException(ErrorCodes.GENERIC_ENTITY_DUPLICATE_FOUND_ERROR);
		}

		return new WalletModel(walletDal.getWalletByName(model.getName(), false).get(0));
	}

	@Transactional
	@Override
	public WalletModel getWalletById(long id) throws LemonadeException {
		Wallet entity = walletDal.getWalletById(id);

		if (entity == null) {
			throw new LemonadeException(ErrorCodes.WALLET_NOT_FOUND);
		}

		WalletModel model = new WalletModel(entity);

		return model;
	}

	@Transactional
	@Override
	public WalletModel updateWallet(WalletModel model) throws LemonadeException {

		Wallet wallet = walletDal.getWalletById(model.getId());

		if (wallet == null) {
			throw new LemonadeException(ErrorCodes.WALLET_NOT_FOUND);
		}

		if (walletDal.getWalletByName(model.getName(), false).isEmpty()) {
			wallet.setName(model.getName());

			walletDal.updateWallet(wallet);
		} else {
			throw new LemonadeException(ErrorCodes.WALLET_NOT_FOUND);
		}

		return new WalletModel(wallet);
	}

	@Transactional
	@Override
	public void deleteWallet(long id) throws LemonadeException {

		Wallet wallet = walletDal.getWalletById(id);

		if (wallet == null) {
			throw new LemonadeException(ErrorCodes.WALLET_NOT_FOUND);
		}
		
		if(!wallet.getAvailabilityFlag())
			throw new LemonadeException(ErrorCodes.WALLET_TAKEN);

		wallet.setDeleteFlag(true);

		walletDal.deleteWallet(wallet);
	}
}
