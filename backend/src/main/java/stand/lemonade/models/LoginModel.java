package stand.lemonade.models;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import stand.lemonade.entities.User;

public class LoginModel {

	private long userId;
	@NotNull
	@NotEmpty
	private String email;
	@NotEmpty
	@NotNull
	private String password;
	private String firstName;
	private String lastName;
	private String role;
	private String token;

	public LoginModel() {
	}

	public LoginModel(User entity) {
		this.userId = entity.getId();
		this.email = entity.getEmail();
		this.firstName = entity.getFirstName();
		this.lastName = entity.getLastName();
		this.role = entity.getRole().toString();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "LoginModel [userId=" + userId + ", email=" + email + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", role=" + role + ", token=" + token + "]";
	}

}
