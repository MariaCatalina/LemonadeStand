package stand.lemonade.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.google.inject.Inject;

import stand.lemonade.entities.Wallet;

public class WalletDalImpl implements WalletDal {
	@Inject
	private EntityManager em;

	public List<Wallet> getWalletsByCriteria(boolean deleteFlag, boolean availabilityFlag) {
		if (availabilityFlag == false) {
			return em.createQuery("FROM Wallet as w WHERE w.deleteFlag= :deleteFlag", Wallet.class)
					.setParameter("deleteFlag", deleteFlag).getResultList();
		} else if (availabilityFlag == true) {
			return em
					.createQuery(
							"FROM Wallet as w WHERE w.deleteFlag= :deleteFlag and w.availabilityFlag= :availabilityFlag",
							Wallet.class)
					.setParameter("deleteFlag", deleteFlag).setParameter("availabilityFlag", availabilityFlag)
					.getResultList();
		} else {
			return em.createQuery("FROM Wallet as w", Wallet.class).getResultList();
		}
	}

	@Override
	public List<Wallet> getWalletByName(String name, boolean deleteFlag) {
		TypedQuery<Wallet> query = em
				.createQuery("FROM Wallet as w WHERE w.name= :name and w.deleteFlag= :notDeleted", Wallet.class)
				.setParameter("name", name).setParameter("notDeleted", deleteFlag);

		return query.getResultList();
	}

	@Override
	public Wallet getWalletById(long id) {
		Wallet entity = em.find(Wallet.class, id);

		return entity;
	}

	@Override
	public void createWallet(Wallet wallet) {
		em.persist(wallet);
	}

	@Override
	public void updateWallet(Wallet wallet) {
		em.persist(wallet);

	}

	@Override
	public void deleteWallet(Wallet wallet) {
		em.persist(wallet);

	}
}
