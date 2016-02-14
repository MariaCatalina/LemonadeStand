package stand.lemonade.dal;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Injector;

import stand.lemonade.config.LemonadeApplicationTest;
import stand.lemonade.database.DatabaseDalTest;
import stand.lemonade.entities.User;
import stand.lemonade.exceptions.LemonadeException;
import stand.lemonade.models.UserModel;
import stand.lemonade.service.UserService;

public class UserDalImplTest {
	private static Injector injector;
	private static UserDal userDal;
	private static DatabaseDalTest databaseDal;
	private static UserService userService;

//	@BeforeClass
//	public static void init() {
//		injector = LemonadeApplicationTest.createInjector();
//
//		userDal = injector.getInstance(UserDal.class);
//
//		userService = injector.getInstance(UserService.class);
//
//		databaseDal = injector.getInstance(DatabaseDalTest.class);
//
//		// databaseDal.deleteDbData();
//	}
//
//	/* Email is already in the data base */
//	@Test(expected = LemonadeException.class)
//	public void testCreate() throws LemonadeException {
//		UserModel user = new UserModel();
//		user.setEmail("popa.catalina01@gmail.com");
//		user.setFirstName("ANA");
//		user.setLastName("Maria");
//		user.setPassword("blabla");
//		user.setRole("ADMIN");
//
//		UserModel created = new UserModel();
//		created = userService.createUser(user);
//		Assert.assertNotNull(created.getId());
//
//	}
//
////	@Test
////	public void testCreate1() throws LemonadeException {
////		UserModel user = new UserModel();
////		user.setEmail("carmenRusu02@gmail.com");
////		user.setFirstName("Carmen");
////		user.setLastName("Rusu");
////		user.setPassword("pass");
////		user.setRole("WAITER");
////
////		UserModel created = new UserModel();
////		created = userService.createUser(user);
////
////		Assert.assertNotNull(created.getId());
////	}
//
//	@Test
//	public void testUpdate() throws LemonadeException {
//		/* create a new user */
//		UserModel user = new UserModel();
//		user.setEmail("waiter01@gmail.com");
//		user.setFirstName("Carmen");
//		user.setLastName("Rusu");
//		user.setPassword("pass");
//		user.setRole("WAITER");
//
//		UserModel created = new UserModel();
//
//		/* update user */
//		UserModel userUpdated = new UserModel();
//
//		userUpdated.setId(created.getId());
//		user.setLastName("Mihnea");
//		user.setFirstName("Carmen");
//		user.setEmail("waiter01@gmail.com");
//
//		UserModel updated = userService.updateUser(user);
//		Assert.assertEquals(updated.getLastName(), "Mihnea");
//	}
//
//	@Test
//	public void testDelete() {
//		/* create a new user */
//		UserModel user = new UserModel();
//		user.setEmail("waiterDeleted@gmail.com");
//		user.setFirstName("Carmen");
//		user.setLastName("Rusu");
//		user.setPassword("pass");
//		user.setRole("WAITER");
//		UserModel created = new UserModel();
//
//		/* delete the user */
//		User userDelete = new User();
//		userService.deleteUser(created.getId());
//		userDelete = userDal.getUserById(20L, false);
//		Assert.assertNull(userDelete);
//	}

}
