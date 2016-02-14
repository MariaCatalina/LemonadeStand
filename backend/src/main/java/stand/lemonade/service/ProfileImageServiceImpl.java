package stand.lemonade.service;

import java.nio.charset.Charset;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import stand.lemonade.dal.ProfileImageDal;
import stand.lemonade.dal.UserDal;
import stand.lemonade.entities.Profileimage;
import stand.lemonade.exceptions.ErrorCodes;
import stand.lemonade.exceptions.LemonadeException;
import stand.lemonade.models.ProfileImageModel;

public class ProfileImageServiceImpl implements ProfileImageService {

	@Inject
	private ProfileImageDal profileImageDal;
	
	@Inject
	private UserDal userDal;
	
	@Transactional
	@Override
	public ProfileImageModel getProfileImage(long userId) throws LemonadeException {
		List<Profileimage> imageList = profileImageDal.getProfileImage(userId);
		
		if(imageList.isEmpty()) {
			throw new LemonadeException(ErrorCodes.GENERIC_ENTITY_NOT_FOUND_ERROR);
		} 
		
		Profileimage image = imageList.get(0);
		
		if(image == null) {
			throw new LemonadeException(ErrorCodes.GENERIC_ENTITY_NOT_FOUND_ERROR);
		}
		
		ProfileImageModel model = new ProfileImageModel();
		
		model.setImage(new String(image.getImage()));
		
		return model;
	}
	
	@Transactional
	@Override
	public ProfileImageModel createProfileImage(long userId, ProfileImageModel model) throws LemonadeException {
		List<Profileimage> imageList = profileImageDal.getProfileImage(userId);
		
		Profileimage entity;
		
		if(imageList.isEmpty()) {
			entity = new Profileimage();
			entity.setUser(userDal.getUserById(model.getId().longValue(), false));
		} else {
			if(imageList.get(0) != null) {
				entity = imageList.get(0);
				entity.setUser(userDal.getUserById(model.getId().longValue(), false));
			}
			else
				entity = new Profileimage();
		}
		
		entity.setImage(model.getImage().getBytes());
		
		profileImageDal.createProfileImage(entity);
		
		return model;
	}
}
