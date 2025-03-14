package com.example.formula1api;

import com.example.formula1api.driver.Driver;
import com.example.formula1api.team.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Formula1apiApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnAListOfDrivers() {
        var response = restTemplate.getForEntity("/api/f1/drivers", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldReturnADriverById() {
        var response = restTemplate.getForEntity("/api/f1/drivers/1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DirtiesContext
    void shouldCreateProperlyANewTeamAndANewDriver() {
        // First, save the team
        Team team = new Team("McLaren");

        var teamPostResponse = restTemplate.postForEntity("/api/f1/teams", team, Void.class);
        assertThat(teamPostResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI newTeamLocation = teamPostResponse.getHeaders().getLocation();
        assertThat(newTeamLocation).isNotNull();

        var teamGetResponse = restTemplate.getForEntity(newTeamLocation, Team.class);
        assertThat(teamGetResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        Team savedTeam = teamGetResponse.getBody();
        assertThat(savedTeam).isNotNull();

        // Now, create and save the driver with the saved team
        Driver driver = new Driver("Lando NoWins", savedTeam);

        var postResponse = restTemplate.postForEntity("/api/f1/drivers", driver, Void.class);
        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI newDriverLocation = postResponse.getHeaders().getLocation();
        assertThat(newDriverLocation).isNotNull();

        var getResponse = restTemplate.getForEntity(newDriverLocation, String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
