package stand.lemonade.util;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import stand.lemonade.exceptions.ErrorCodes;
import stand.lemonade.exceptions.LemonadeException;

public class LemonadeValidator {

	private static Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

	@SuppressWarnings("rawtypes")
	public <T> void beanValidation(T obj) throws LemonadeException {
		Set<ConstraintViolation<T>> violations = VALIDATOR.validate(obj);

		if (!violations.isEmpty()) {

			Iterator<ConstraintViolation<T>> iterator = violations.iterator();
			while (iterator.hasNext()) {
				@SuppressWarnings("unused")
				ConstraintViolation violation = iterator.next();
				// TODO: logging
			}

			throw new LemonadeException(ErrorCodes.BEAN_VALIDATION_ERROR);
		}
	}

}
