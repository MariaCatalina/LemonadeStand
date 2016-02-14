package stand.lemonade.dal;

import java.util.List;

import javax.persistence.EntityManager;

import com.google.inject.Inject;

import stand.lemonade.entities.Profileimage;

public class ProfileImageDalImpl implements ProfileImageDal {

	@Inject
	private EntityManager em;
	
	@Override
	public List<Profileimage> getProfileImage(long userId) {
		return em.createQuery("FROM Profileimage AS p WHERE p.user.id = :userId", Profileimage.class)
				.setParameter("userId", userId).getResultList();
	}
	
	@Override
	public Profileimage createProfileImage(Profileimage image) {
		em.persist(image);
		return image;
	}
}
