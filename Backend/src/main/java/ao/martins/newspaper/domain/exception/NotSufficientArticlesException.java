package ao.martins.newspaper.domain.exception;

public class NotSufficientArticlesException extends  BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotSufficientArticlesException(String msg) {
		super(msg);
	}

}
