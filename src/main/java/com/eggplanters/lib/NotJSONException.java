package com.eggplanters.lib;
public class NotJSONException extends Exception {
    @Override
    public String getMessage() {
        return "File is not JSON.";
    }
}

