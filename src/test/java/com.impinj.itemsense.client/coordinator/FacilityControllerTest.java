package com.impinj.itemsense.client.coordinator;


import static com.github.tomakehurst.wiremock.client.WireMock.*;

import static org.hamcrest.CoreMatchers.instanceOf;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import com.google.gson.Gson;
import com.impinj.itemsense.client.coordinator.facility.Facility;
import com.impinj.itemsense.client.coordinator.facility.FacilityController;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.net.URI;
import java.util.ArrayList;


/**
 * Created by jcombopi on 1/27/16.
 */
public class FacilityControllerTest {

    private CoordinatorApiController coordinatorApiController;
    private FacilityController facilityController;
    private Gson gson;

    @ClassRule
    public static WireMockClassRule wireMockRule = new WireMockClassRule(8089);

    @Rule
    public WireMockClassRule instanceRule = wireMockRule;


    @Before
    public void setUp() throws Exception {

        Client client = ClientBuilder.newClient().register(HttpAuthenticationFeature.basic("testUser", "testPassword"));

                //http://localhost:8089 is where wiremock is running
        coordinatorApiController = new CoordinatorApiController(client, URI.create("http://localhost:8089"));
        facilityController = coordinatorApiController.getFacilityController();
        gson = new Gson();

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void GetAllFacilities(){
        Facility testFacility = new Facility("TestFacility");
        ArrayList<Facility> testFacilities = new ArrayList<>();
        testFacilities.add(testFacility);

        stubFor(get(urlEqualTo("/configuration/v1/facilities/show")).willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(gson.toJson(testFacilities))));

        ArrayList<Facility> facilities = facilityController.getAllFacilities();

        Assert.assertEquals(facilities.size(), 1);
        Assert.assertThat(facilities, instanceOf(ArrayList.class));
        Assert.assertThat(facilities.get(0), instanceOf(Facility.class));
        Assert.assertEquals(facilities.get(0),testFacility);
    }

    @Test
    public void GetFacility(){
        Facility testFacility = new Facility("TestFacility");

        stubFor(get(urlEqualTo("/configuration/v1/facilities/show/TestFacility")).willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(gson.toJson(testFacility))));

        Facility facilityResult =  facilityController.getFacility("TestFacility");
        Assert.assertEquals(facilityResult, testFacility);
        Assert.assertThat(facilityResult, instanceOf(Facility.class));

    }





}