package ao.martins.newspaper.domain.exception;

public class MagazineEditionAlreadyPublishedException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MagazineEditionAlreadyPublishedException(String msg) {
		super(msg);
	}

}
