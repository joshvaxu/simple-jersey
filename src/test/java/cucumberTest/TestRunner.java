package cucumberTest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.example.Main;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "Feature"
		,glue={"stepDefinition"}
		)

public class TestRunner {

    private static HttpServer server;
    public static WebTarget target;

    /**
     * Prepared for JUnit.
     * Start the API Server and Create a Client target.
     * 
     * @throws Exception
     */
    @BeforeClass
    public static void setUp() throws Exception {
    	
    	System.out.println("############setup()#####################");
    	
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI);
    }

    /**
     * Shutdown the API Server.
     * 
     * @throws Exception
     */
    @AfterClass
    public static void tearDown() throws Exception {
    	System.out.println("############tearDown()#####################");
    	
        server.shutdownNow();
    }

    
}