package com.test.rest;

public class GetAccountInfoOutput {

    private String account;
    private String created_timestamp;
    private String expiry_timestamp;

    public String getAccount() {
        return account;
    }

    public String getCreated_timestamp() {
        return created_timestamp;
    }

    public String getExpiry_timestamp() {
        return expiry_timestamp;
    }

    @Override
    public String toString() {
        return "GetAccountInfoOutput{" +
                "account='" + account + '\'' +
                ", created_timestamp='" + created_timestamp + '\'' +
                ", expiry_timestamp='" + expiry_timestamp + '\'' +
                '}';
    }
}
