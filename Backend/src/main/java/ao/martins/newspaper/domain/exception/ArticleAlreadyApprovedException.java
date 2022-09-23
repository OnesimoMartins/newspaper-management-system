package ao.martins.newspaper.domain.exception;

public class ArticleAlreadyApprovedException extends BusinessException {
	private static final long serialVersionUID = 1L;

	public ArticleAlreadyApprovedException(String msg) {
		super(msg);
	}
}
