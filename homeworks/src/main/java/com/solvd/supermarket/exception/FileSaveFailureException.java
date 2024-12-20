package com.solvd.supermarket.exception;

import java.io.IOException;

public class FileSaveFailureException extends IOException {
    public FileSaveFailureException() {
        super("Receipt save failure");
    }

}
