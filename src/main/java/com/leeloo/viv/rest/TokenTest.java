package com.leeloo.viv.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.HeaderParam;
import java.util.List;

import com.google.gson.Gson;

import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Tokeninfo;
import com.google.api.client.auth.oauth2.Credential;

import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.Reader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Path("token")
public class TokenTest {

    private static final JacksonFactory JSON_FACTORY = new JacksonFactory();
    private static final Gson GSON = new Gson();

    private static final String CLIENT_ID = "17792696780-egbqbeqkdbamg2aojs0e7otgvq1i2p06.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "GLWIOHbb8dQS7I0qPNe3DSL8";

    @GET
    @Path("/all")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getAllWorks() {
        Gson gson = new Gson();
        List<Work> works = new WorkRepository().getAllWorks();
        return Response.ok().entity(gson.toJson(works)).build();
    }

    @GET
    @Path("/test")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response test(@HeaderParam("auth-result") String authResult)  {
        
        //Reader reader = new FileReader("client_secrets.json");
        //GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(new JacksonFactory(), reader);
        try{
            GoogleCredential credential = new GoogleCredential.Builder()
                .setJsonFactory(new JacksonFactory())
                .setTransport(new NetHttpTransport())
                .setClientSecrets(CLIENT_ID, CLIENT_SECRET).build()
                .setFromTokenResponse(new JacksonFactory().fromString(
                    authResult, GoogleTokenResponse.class));
        } catch (IOException e) {
            
            System.out.print("Failed to read data from Google. " +e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR ).build();
        }




        Gson gson = new Gson();
        //return gson.toJson("Well, hello there!");
        //System.out.print(gonHeader);
        return Response
            .ok(gson.toJson("Well, hello there!"), MediaType.APPLICATION_JSON)
            .cookie(new NewCookie("name", "I am the cookie!"))
            .build();
    }
}
