package stand.lemonade.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import stand.lemonade.entities.Roles;
import stand.lemonade.entities.User;

public class UserDalImpl implements UserDal {
	@Inject
	private EntityManager em;

	@Override
	public List<User> getUsers(Roles role, boolean deleteFlag) {
		TypedQuery<User> query = em.createQuery("FROM User AS u WHERE u.role = :role AND u.deleteFlag = :deleteFlag ",
				User.class);
		query.setParameter("role", role);
		query.setParameter("deleteFlag", deleteFlag);
		return query.getResultList();
	}

	@Override
	public List<User> getUsers(boolean deleteFlag) {
		TypedQuery<User> query = em.createQuery("FROM User AS u WHERE u.deleteFlag = :deleteFlag ", User.class);
		query.setParameter("deleteFlag", deleteFlag);
		return query.getResultList();
	}

	@Override
	public User getUserById(Long id, boolean deleteFlag) {
		TypedQuery<User> query = em.createQuery("FROM User AS u WHERE u.id = :id AND u.deleteFlag = :deleteFlag ",
				User.class);
		query.setParameter("id", id);
		query.setParameter("deleteFlag", deleteFlag);

		List<User> userList = query.getResultList();
		if (userList.isEmpty())
			return null;
		else
			return userList.get(0);

	}

	@Override
	@Transactional
	public User createUser(User user) {
		em.persist(user);
		return user;
	}

	@Override
	public void deleteUser(User user) {
		em.merge(user);
	}

	@Override
	@Transactional
	public User updateUser(User user) {
		em.merge(user);
		return user;
	}

	@Override
	public boolean checkEmail(String email, boolean deleteFlag) {
		TypedQuery<User> query = em.createQuery("FROM User AS u WHERE u.email = :email AND u.deleteFlag = :deleteFlag",
				User.class);
		query.setParameter("email", email);
		query.setParameter("deleteFlag", deleteFlag);

		List<User> userList = query.getResultList();
		if (userList.isEmpty())
			return true;

		return false;
	}

	@Override
	public User checkUser(String email, String password, boolean deleteFlag) {
		TypedQuery<User> query = em.createQuery(
				"FROM User AS u WHERE u.email = :email AND u.password = :password AND u.deleteFlag = :deleteFlag",
				User.class);
		query.setParameter("email", email);
		query.setParameter("password", password);
		query.setParameter("deleteFlag", deleteFlag);

		List<User> userList = query.getResultList();
		if (userList.isEmpty())
			return null;
		else
			return userList.get(0);
	}

}
