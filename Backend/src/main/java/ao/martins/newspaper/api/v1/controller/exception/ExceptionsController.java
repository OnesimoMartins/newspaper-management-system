//package ao.martins.newspaper.api.v1.controller.exception;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.web.HttpRequestMethodNotSupportedException;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import ao.martins.newspaper.api.dto.response.error.ErrorModelResponse;
//
//
//@RestControllerAdvice
//public class ExceptionsController extends ResponseEntityExceptionHandler{	
//
//
//	@Override
//	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		
//		System.out.println("xxxx");
//		return ResponseEntity.ok( ErrorModelResponse.builder()
//				.message("Custommmmmmmm")
//				.build());
//	}
//	
//	@Override
//	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		// TODO Auto-generated method stub
//		return  ResponseEntity.ok( ErrorModelResponse.builder()
//				.message("Custommmmmmmm")
//				.build());
//	}
//	
//	
//	
//}
