//package ao.martins.newspaper.api.v1.controller.exception;
//
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import ao.martins.newspaper.api.dto.response.error.ErrorModelResponse;
//import ao.martins.newspaper.domain.exception.ActionNotAllowedException;
//import ao.martins.newspaper.domain.exception.ArticleAlreadyMarkedException;
//
//@RestControllerAdvice
//public class BusinessExceptionsController extends ResponseEntityExceptionHandler {
//
//	
//	@ExceptionHandler(ActionNotAllowedException.class)
//	public ErrorModelResponse 
//	     ActionNotAllowedExceptionHandler(ActionNotAllowedException e) {
//		
//		return ErrorModelResponse.builder()
//				.status(400)
//				.message(e.getMessage())
//				.build();
//	}
//	
//	@ExceptionHandler(ArticleAlreadyMarkedException.class)
//	public ResponseEntity<Object> 
//	     ActionNotAllowedExceptionHandler(ArticleAlreadyMarkedException e,
//	    		 WebRequest req) {
//		
//	var	body= ErrorModelResponse.builder()
//				.status(HttpStatus.ALREADY_REPORTED.value())
//				.message(e.getMessage())
//				.build();
//	
//	return  handleExceptionInternal(e, body, new HttpHeaders() ,HttpStatus.ALREADY_REPORTED , req);
//	
//	}
//
//}
