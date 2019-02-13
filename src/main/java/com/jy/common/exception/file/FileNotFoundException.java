package com.jy.common.exception.file;

import com.jy.common.exception.GenericException;

/**
 * @author hiyouka
 * Date: 2019/2/12
 * @since JDK 1.8
 */
public class FileNotFoundException extends GenericException {

    private static final String message = "no file get from this path";

    public FileNotFoundException(){super(message);};

    public FileNotFoundException(String message) {
        super(message);
    }
}