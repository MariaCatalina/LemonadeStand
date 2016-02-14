package stand.lemonade.dal;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.google.inject.Injector;

import stand.lemonade.config.LemonadeApplicationTest;
import stand.lemonade.database.DatabaseDalTest;
import stand.lemonade.entities.Wallet;

public class WalletDalImplTest {

	private static Injector injector;
	private static WalletDal walletDal;
	private static DatabaseDalTest databaseDal;

//	@BeforeClass
//	public static void init() {
//		injector = LemonadeApplicationTest.createInjector();
//
//		walletDal = injector.getInstance(WalletDal.class);
//
//		databaseDal = injector.getInstance(DatabaseDalTest.class);
//
//		// databaseDal.deleteDbData();
//	}
//
//	@Test
//	@Ignore
//	public void testCreate() {
//		Wallet wallet = new Wallet();
//		walletDal.createWallet(wallet);
//		System.out.println(wallet);
//		Assert.assertNotNull(wallet.getId());
//	}
//
//	@Test
//	@Ignore
//	public void testUpdate() {
//		// TODO
//	}

}
