package stand.lemonade.service;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import stand.lemonade.dal.UserDal;
import stand.lemonade.entities.Roles;
import stand.lemonade.entities.User;
import stand.lemonade.exceptions.ErrorCodes;
import stand.lemonade.exceptions.LemonadeException;
import stand.lemonade.models.LoginModel;
import stand.lemonade.models.UserModel;

public class UserServiceImpl implements UserService {

	@Inject
	private UserDal userDal;

	@Override
	@Transactional
	public List<UserModel> getUsers(Roles role) {
		/* deleteFlag = false */
		List<User> usersEntity = userDal.getUsers(role, false);

		/* map entity to model */
		List<UserModel> models = new ArrayList<UserModel>();

		for (User u : usersEntity) {
			UserModel userModel = new UserModel(u);
			models.add(userModel);
		}

		return models;
	}

	@Override
	@Transactional
	public List<UserModel> getUsers() {
		List<User> usersEntity = userDal.getUsers(false);

		/* map entity to model */
		List<UserModel> models = new ArrayList<UserModel>();

		for (User u : usersEntity) {
			UserModel userModel = new UserModel(u);
			models.add(userModel);
		}

		return models;
	}

	@Override
	@Transactional
	public UserModel getUserById(Long id) throws LemonadeException {
		User userEntity = userDal.getUserById(id, false);
		if (userEntity == null)
			throw new LemonadeException(ErrorCodes.USER_NOT_FOUND);

		UserModel userModel = new UserModel(userEntity);
		return userModel;
	}

	@Override
	@Transactional
	public UserModel createUser(UserModel userModel) throws LemonadeException {
		User userEntity = new User();

		/* check if email is in the database */
		if (!userDal.checkEmail(userModel.getEmail(), false))
			throw new LemonadeException(ErrorCodes.INVALID_EMAIL);

		/* set values with receive values */
		userEntity.setFirstName(userModel.getFirstName());
		userEntity.setLastName(userModel.getLastName());
		userEntity.setPassword(userModel.getPassword());
		userEntity.setEmail(userModel.getEmail());
		userEntity.setRole(Roles.getRoleFromString(userModel.getRole()));
		userEntity.setDeleteFlag(false);

		User userCreated = userDal.createUser(userEntity);

		/* return created user */
		UserModel userModelCreated = new UserModel(userCreated);
		return userModelCreated;
	}

	@Override
	@Transactional
	public void deleteUser(Long id) {
		User userEntity = userDal.getUserById(id, false);

		if (userEntity != null) {
			/* change delete flag on true */
			userEntity.setDeleteFlag(true);
			userDal.deleteUser(userEntity);
		}
	}

	   	
	@Override
	@Transactional
	public UserModel updateUser(UserModel userModel) throws LemonadeException {
		User userEntity = userDal.getUserById(userModel.getId(), false);
		if (userEntity == null)
			throw new LemonadeException(ErrorCodes.USER_NOT_FOUND);

		userEntity.setFirstName(userModel.getFirstName());
		userEntity.setLastName(userModel.getLastName());
		userEntity.setEmail(userModel.getEmail());

		User userUpdated = userDal.updateUser(userEntity);

		/* return updated entity */
		UserModel userModelCreated = new UserModel(userUpdated);
		return userModelCreated;
	}

	@Override
	@Transactional
	public UserModel updateUserProfile(UserModel userModel) throws LemonadeException {
		/* find the entity from the database */
		User userEntity = userDal.getUserById(userModel.getId(), false);
		if (userEntity == null)
			throw new LemonadeException(ErrorCodes.USER_NOT_FOUND);

		/*
		 * this data is already verified by validator and if is not change it
		 * has the same values
		 */
		userEntity.setFirstName(userModel.getFirstName());
		userEntity.setLastName(userModel.getLastName());
		userEntity.setEmail(userModel.getEmail());

		/* if the password is not change it remain the same */
		if (userModel.getPassword() != null)
			userEntity.setPassword(userModel.getPassword());

		User userUpdated = userDal.updateUser(userEntity);

		UserModel userModelCreated = new UserModel(userUpdated);
		return userModelCreated;
	}

	@Override
	@Transactional
	public LoginModel checkUser(LoginModel loginModel) throws LemonadeException {
		User userEntity = userDal.checkUser(loginModel.getEmail(), loginModel.getPassword(), false);

		if (userEntity == null)
			throw new LemonadeException(ErrorCodes.WRONG_CREDENTIALS);

		LoginModel loginModelCheck = new LoginModel(userEntity);

		return loginModelCheck;
	}

}
