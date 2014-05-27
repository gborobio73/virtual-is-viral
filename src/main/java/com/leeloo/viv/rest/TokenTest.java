package com.leeloo.viv.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.gson.Gson;

@Path("token")
public class TokenTest {

    private static final GsonFactory JSON_FACTORY = new GsonFactory();
    private static final Gson GSON = new Gson();
    private static final HttpTransport TRANSPORT = new NetHttpTransport();

    private static final String CLIENT_ID = "17792696780-egbqbeqkdbamg2aojs0e7otgvq1i2p06.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "GLWIOHbb8dQS7I0qPNe3DSL8";

    @Context private HttpServletRequest httpRequest;
    
    @GET
    @Path("/all")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getAllWorks() {
        Gson gson = new Gson();
        List<Work> works = new WorkRepository().getAllWorks();
        return Response.ok().entity(gson.toJson(works)).build();
    }

    @POST
	@Path("/post")
	@Consumes("application/json")
    @Produces({ MediaType.APPLICATION_JSON })
	public Response createTrackInJSON(Track track) {
 
		String result = "Track saved : " + track;
		return Response.status(201).entity(result).build();
 
	}
    
    @POST
	@Path("/test")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_JSON })
    public Response test(Code code) 
    		throws IOException {
        
    	System.out.println("ConnectServlet authCode.code:" +code.code);
    	String tokenData = (String) httpRequest.getSession().getAttribute("token");
        System.out.println("ConnectServlet Token data:" +tokenData);
        if (tokenData != null) {
        	return Response
                    .ok(GSON.toJson("Current user is already connected."), MediaType.APPLICATION_JSON)
                    .build();
        }
        
        //try {
            // Upgrade the authorization code into an access and refresh token.
            GoogleTokenResponse tokenResponse = new GoogleTokenResponse();
            tokenResponse= new GoogleAuthorizationCodeTokenRequest(TRANSPORT, JSON_FACTORY,
                    CLIENT_ID, CLIENT_SECRET, code.code, "postmessage").execute();

            // You can read the Google user ID in the ID token.
            // This sample does not use the user ID.
            GoogleIdToken idToken = tokenResponse.parseIdToken();
            String gplusId = idToken.getPayload().getSubject();

            // Store the token in the session for later use.
            httpRequest.getSession().setAttribute("token", tokenResponse.toString());
            return Response
                    .ok(GSON.toJson("Successfully connected user."), MediaType.APPLICATION_JSON)
                    .build();
            
          /*} catch (TokenResponseException e) {
        	  return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to upgrade the authorization code.").build();
          } catch (IOException e) {
        	  return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to read token data from Google. " +
                      e.getMessage()).build();
          }*/
    }
}
