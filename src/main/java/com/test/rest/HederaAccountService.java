package com.test.rest;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@RegisterRestClient(configKey = "hedera-api")
public interface HederaAccountService {

    @GET
    @Path("/api/v1/accounts/{id}")
    Uni<GetAccountInfoOutput> getAccountInfo(@BeanParam GetAccountInfoInput input);

}
