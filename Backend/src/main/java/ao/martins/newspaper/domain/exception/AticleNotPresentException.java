package ao.martins.newspaper.domain.exception;

public class AticleNotPresentException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public AticleNotPresentException(String msg) {
		super(msg);
	}

}
