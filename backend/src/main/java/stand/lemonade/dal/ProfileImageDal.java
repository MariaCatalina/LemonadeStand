package stand.lemonade.dal;

import java.util.List;

import stand.lemonade.entities.Profileimage;

public interface ProfileImageDal {

	/**
	 * @param image
	 * @return
	 */
	Profileimage createProfileImage(Profileimage image);

	/**
	 * @param userId
	 * @return
	 */
	List<Profileimage> getProfileImage(long userId);

	
}
