package com.wallet.walletapplication.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String username) {
        super("Username '" + username + "' is already taken.");
    }
}
