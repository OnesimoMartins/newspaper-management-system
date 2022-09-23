package ao.martins.newspaper.domain.exception;

public class UserNotAllowedException extends ActionNotAllowedException {

	private static final long serialVersionUID = 1L;

	public UserNotAllowedException(String msg) {
		super(msg);
	}

}
