package stand.lemonade.dal;

import java.util.List;

import stand.lemonade.entities.Roles;
import stand.lemonade.entities.User;

public interface UserDal {

	/**
	 * @param role
	 * @param deleteFlag
	 * @return
	 */
	List<User> getUsers(Roles role, boolean deleteFlag);

	/**
	 * @param deleteFlag
	 * @return
	 */
	List<User> getUsers(boolean deleteFlag);

	/**
	 * @param id
	 * @return
	 */
	User getUserById(Long id, boolean deleteFlag);

	/**
	 * @param user
	 * @return
	 */
	User createUser(User user);

	/**
	 * @param user
	 */
	void deleteUser(User user);

	/**
	 * @param user
	 * @return
	 */
	User updateUser(User user);

	/**
	 * @param email
	 * @param deleteFlag
	 * @return
	 */
	boolean checkEmail(String email, boolean deleteFlag);

	/**
	 * @param email
	 * @param password
	 * @return
	 */
	User checkUser(String email, String password, boolean deleteFlag);
}
