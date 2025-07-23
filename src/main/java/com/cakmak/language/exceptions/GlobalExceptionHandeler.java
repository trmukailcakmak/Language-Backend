package com.cakmak.language.exceptions;

import com.cakmak.language.constant.MessageKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class GlobalExceptionHandeler {

    private final MessageSource messageSource;
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandeler.class);

    public GlobalExceptionHandeler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @ExceptionHandler(value = LanguageException.class)
    public ResponseEntity<Object> handleLanguageException(LanguageException exception) {
        String code = exception.getCode();
        Object[] args = exception.getPrmList();
        String msg = this.messageSource.getMessage(code,args.length==0 ? null:args, Locale.ENGLISH);
        logger.error("Language exception alindi. msg:: " + msg,exception);
        return new ResponseEntity<>(this.messageSource.getMessage(MessageKey.GENERAL_ERR,new Object[]{code,msg},Locale.ENGLISH), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(
            Exception exception
    ) {
        logger.error("Beklenmeyen bir hata alindi...",exception);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}
