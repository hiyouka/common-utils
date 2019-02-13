package com.jy.common.exception.file;

import com.jy.common.exception.GenericException;

/**
 * @author hiyouka
 * Date: 2019/2/12
 * @since JDK 1.8
 */
public class FileReadException extends GenericException {

    private final static String message = "read file error !! ";

    public FileReadException(){
        super(message);
    }

    public FileReadException(String message) {
        super(message);
    }
}