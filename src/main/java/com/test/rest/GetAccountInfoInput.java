package com.test.rest;

import javax.ws.rs.PathParam;

public class GetAccountInfoInput {

    @PathParam("id")
    public final String accountId;

    public GetAccountInfoInput(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "GetAccountInfoInput{" +
                "accountId='" + accountId + '\'' +
                '}';
    }
}
