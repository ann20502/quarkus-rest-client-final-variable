package com.test;

import com.test.rest.GetAccountInfoInput;
import com.test.rest.GetAccountInfoOutput;
import com.test.rest.HederaAccountService;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/profile")
public class MyController {

    HederaAccountService accountService;

    public MyController(@RestClient HederaAccountService accountService) {
        this.accountService = accountService;
    }

    @GET
    public Uni<GetAccountInfoOutput> execute() {
        GetAccountInfoInput input = new GetAccountInfoInput("0.0.100000");
        return accountService.getAccountInfo(input);
    }

}
