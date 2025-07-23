package com.cakmak.language.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LanguageException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private String code;
    private Object[] prmList;
    public LanguageException(String code, String ... prmList){
        super();
        this.code = code;
        this.prmList = prmList;
    }

}
