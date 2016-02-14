package stand.lemonade.config;

import org.joda.time.DateTime;

import com.github.toastshaman.dropwizard.auth.jwt.JsonWebTokenParser;
import com.github.toastshaman.dropwizard.auth.jwt.JsonWebTokenValidator;
import com.github.toastshaman.dropwizard.auth.jwt.hmac.HmacSHA512Signer;
import com.github.toastshaman.dropwizard.auth.jwt.hmac.HmacSHA512Verifier;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebToken;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebTokenClaim;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebTokenHeader;
import com.github.toastshaman.dropwizard.auth.jwt.parser.DefaultJsonWebTokenParser;
import com.google.common.base.Optional;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import stand.lemonade.entities.Roles;
import stand.lemonade.entities.User;

public class LemonadeAuthenticator implements Authenticator<JsonWebToken, User> {

	public static final byte[] SECRET = "secret".getBytes();

	private static final HmacSHA512Signer SIGNER = new HmacSHA512Signer(SECRET);

	public static final HmacSHA512Verifier VERIFIER = new HmacSHA512Verifier(SECRET);

	public static final JsonWebTokenParser TOKEN_PARSER = new DefaultJsonWebTokenParser();

	@Override
	public Optional<User> authenticate(JsonWebToken token) throws AuthenticationException {

		// Provide your own implementation to lookup users based on the
		// principal attribute in the
		// JWT Token. E.g.: lookup users from a database etc.
		// This method will be called once the token's signature has been
		// verified

		// In case you want to verify different parts of the token you can do
		// that here.
		// E.g.: Verifying that the provided token has not expired.

		// All JsonWebTokenExceptions will result in a 401 Unauthorized
		// response.

		final User user = new User();
		final JsonWebTokenValidator jwtValidator = new JsonWebTokenValidator() {

			@Override
			public void validate(JsonWebToken token) {
				Long expirationTime = token.claim().expiration();

				Long now = new DateTime().getMillis() / 1000;

				if (expirationTime > now) {
					// token is valid, so extract user info from it
					JsonWebTokenClaim claims = token.claim();

					String userEmail = claims.subject();
					String role = (String) claims.getParameter("role");
					Long userId = Long.parseLong(String.valueOf(claims.getParameter("userId")));

					user.setEmail(userEmail);
					user.setId(userId);
					user.setRole(Roles.getRoleFromString(role));
				}
			}
		};

		jwtValidator.validate(token);

		if (user.getId() != null) {
			return Optional.of(user);
		}

		return Optional.absent();

	}

	public static String generateWebToken(User user) {

		final JsonWebToken token = JsonWebToken.builder().header(JsonWebTokenHeader.HS512())
				.claim(JsonWebTokenClaim.builder().subject(user.getEmail()).issuedAt(new DateTime())
						.expiration(new DateTime().plusHours(1)).param("role", user.getRole())
						.param("userId", user.getId()).build())
				.build();

		final String signedToken = SIGNER.sign(token);

		return signedToken;

	}
}
