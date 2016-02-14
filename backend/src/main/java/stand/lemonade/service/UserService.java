package stand.lemonade.service;

import java.util.List;

import stand.lemonade.entities.Roles;
import stand.lemonade.exceptions.LemonadeException;
import stand.lemonade.models.LoginModel;
import stand.lemonade.models.UserModel;

public interface UserService {

	/**
	 * @param role
	 * @return
	 */
	List<UserModel> getUsers(Roles role);

	/**
	 * @return
	 */
	List<UserModel> getUsers();

	/**
	 * @param id
	 * @return
	 * @throws LemonadeException
	 */
	UserModel getUserById(Long id) throws LemonadeException;

	/**
	 * @param userModel
	 * @return
	 * @throws LemonadeException
	 */
	UserModel createUser(UserModel userModel) throws LemonadeException;

	/**
	 * @param id
	 * @throws LemonadeException
	 */
	void deleteUser(Long id);

	/**
	 * @param userModel
	 * @return
	 * @throws LemonadeException
	 */
	UserModel updateUser(UserModel userModel) throws LemonadeException;

	/**
	 * @param userModel
	 * @return
	 * @throws LemonadeException
	 */
	UserModel updateUserProfile(UserModel userModel) throws LemonadeException;

	/**
	 * @param loginModel
	 * @return
	 * @throws LemonadeException
	 */
	LoginModel checkUser(LoginModel loginModel) throws LemonadeException;
}
