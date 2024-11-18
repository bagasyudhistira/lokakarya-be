//package co.id.ogya.lokakarya.exceptions;
//
//import co.id.ogya.lokakarya.dto.ManagerDto;
//import co.id.ogya.lokakarya.utils.ServerResponseList;
//import jakarta.servlet.http.HttpServletRequest;
//import org.apache.commons.lang3.StringEscapeUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.MissingRequestHeaderException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.ServletWebRequest;
//import org.springframework.web.context.request.WebRequest;
//
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@ControllerAdvice
//public class GlobalExceptionHandler extends ServerResponseList {
//    final static Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ManagerDto> handleValidationExceptions(MethodArgumentNotValidException ex,
//                                                                 WebRequest request) {
//        HttpServletRequest servletRequest = ((ServletWebRequest) request).getRequest();
//
//        LOG.info("Validation error during request - URL:{}",
//                StringEscapeUtils.escapeJava(servletRequest.getRequestURI()));
//
//        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
//                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
//
//        return new ResponseEntity<>((ManagerDto) errors, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(MissingRequestHeaderException.class)
//    public ResponseEntity<ManagerDto> handleMissingHeaders(MissingRequestHeaderException ex, WebRequest request) {
//        HttpServletRequest servletRequest = ((ServletWebRequest) request).getRequest();
//
//        LOG.info("Missing header during request - URL: {}, Header: {}",
//                StringEscapeUtils.escapeJava(servletRequest.getRequestURI()),
//                StringEscapeUtils.escapeJava(ex.getHeaderName()));
//
//        ManagerDto managerDto = new ManagerDto();
//        if ("token".equals(ex.getHeaderName())) {
//            managerDto.setInfo(getInfoUnauthorized("Missing or invalid authorization"));
//            return new ResponseEntity<>(managerDto, HttpStatus.UNAUTHORIZED);
//        }
//        managerDto.setInfo(getInfoUnauthorized("Missing required header."));
//        return new ResponseEntity<>(managerDto, HttpStatus.BAD_REQUEST);
//    }
//
//    // Catch all other unhandled exceptions
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ManagerDto> handleAllExceptions(Exception ex, WebRequest request) {
//        ManagerDto managerDto = new ManagerDto();
//        managerDto.setInfo(getInfoUnauthorized("An unexpected error occurred."));
//        return new ResponseEntity<>(managerDto, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}
