package ws.client.jersey;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class App 
{
    public static void main( String[] args ) {
    	Client client = ClientBuilder.newClient();
    	getSample(client);
    	postSample(client);
    }

    public static void getSample(Client client) {
    	WebTarget target = client.target("http://localhost:9080/ws-provider-jersey/webapi")
    		    .path("/users") 
    		    .queryParam("id", "001");
    		 
		try {
			User user = target.request().get(User.class);
	    	System.out.println("client");
        	System.out.println(user.getId());
        	System.out.println(user.getName());    		
    	} catch (BadRequestException e) {
    		System.out.println(e.getResponse().getStatus());
		    throw e;
		}
    }

    public static void postSample(Client client) {
    	User user = new User("001", "うえがき");
    	Entity<User> entity = Entity.entity(user, MediaType.APPLICATION_JSON);

    	WebTarget target = client.target("http://localhost:9080/ws-provider-jersey/webapi")
    		    .path("/users"); 
    	
		try {
			target.request().post(entity, User.class);
    	} catch (BadRequestException e) {
    		System.out.println(e.getResponse().getStatus());
		    throw e;
		}
    }
}
