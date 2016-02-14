package stand.lemonade.service;

import stand.lemonade.exceptions.LemonadeException;
import stand.lemonade.models.ProfileImageModel;

public interface ProfileImageService {

	/**
	 * @param userId
	 * @return
	 * @throws LemonadeException 
	 */
	ProfileImageModel getProfileImage(long userId) throws LemonadeException;

	/**
	 * @param userId
	 * @param model
	 * @return
	 * @throws LemonadeException 
	 */
	ProfileImageModel createProfileImage(long userId, ProfileImageModel model) throws LemonadeException;

	
	
	
}
