package ao.martins.newspaper.domain.exception;

public class ArticleAlreadyMarkedException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public ArticleAlreadyMarkedException(String msg) {
	 super(msg);
	}
}
