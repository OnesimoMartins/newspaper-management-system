package ao.martins.newspaper.domain.exception;

public class ActionNotAllowedException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public ActionNotAllowedException(String msg) {
		super(msg);
	}

}
