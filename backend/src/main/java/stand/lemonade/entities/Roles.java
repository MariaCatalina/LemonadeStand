package stand.lemonade.entities;

public enum Roles {

	ADMIN, WAITER;

	public static boolean isValidRole(String roleName) {

		try {
			Roles.valueOf(roleName);
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}

	public static Roles getRoleFromString(String roleName) {

		if (roleName != null) {
			try {
				return Roles.valueOf(roleName);
			} catch (IllegalArgumentException e) {
				// do nothing
			}
		}
		return null;
	}
}
