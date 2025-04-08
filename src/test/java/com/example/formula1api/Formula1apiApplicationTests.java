package com.example.formula1api;

import com.example.formula1api.driver.Driver;
import com.example.formula1api.race.Race;
import com.example.formula1api.team.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

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
    void shouldCreateProperlyANewDriver() {
        var driver = new Driver("Lando NoWins", new Team("McLaren", "McLaren Formula 1 Team"));

        var postResponse = restTemplate
                .withBasicAuth("admin", "adminpass")
                .postForEntity("/api/f1/drivers", driver, Void.class);
        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        var newDriverLocation = postResponse.getHeaders().getLocation();
        assertThat(newDriverLocation).isNotNull();

        var getResponse = restTemplate.getForEntity(newDriverLocation, String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldReturnABadRequestWhenCreatingANewDriverWithInvalidData() {
        Driver driver = new Driver("   ", new Team("   ", "   "));
        var postResponse = restTemplate.postForEntity("/api/f1/drivers", driver, Void.class);
        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @DirtiesContext
    void shouldUpdateProperlyAnExistingDriver() {
        var newDriver = new Driver("Lewis Hamilton", new Team("Ferrari", "Scuderia Ferrari HP"));
        var request = new HttpEntity<>(newDriver);

        var putResponse = restTemplate.exchange("/api/f1/drivers/2", HttpMethod.PUT, request, Void.class);
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void shouldNotUpdateADriverWhenItDoesNotExist() {
        var newDriver = new Driver(
                "George Russell",
                new Team("Mercedes", "Mercedes-AMG PETRONAS Formula One Team"));
        var request = new HttpEntity<>(newDriver);

        var putResponse = restTemplate.exchange("/api/f1/drivers/1000", HttpMethod.PUT, request, Void.class);
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DirtiesContext
    void shouldDeleteProperlyAnExistingDriver() {
        var deleteResponse = restTemplate.exchange(
                "/api/f1/drivers/1",
                HttpMethod.DELETE,
                null,
                Void.class);

        assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        var getResponse = restTemplate.getForEntity("/api/f1/drivers/1", String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DirtiesContext
    void shouldCreateProperlyANewTeam() {
        var team = new Team("Mercedes", "Mercedes-AMG PETRONAS Formula One Team");
        var postResponse = restTemplate.postForEntity("/api/f1/teams", team, Void.class);
        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void shouldReturnABadRequestWhenCreatingANewTeamWithInvalidData() {
        Team team = new Team("   ", "   ");
        var postResponse = restTemplate.postForEntity("/api/f1/teams", team, Void.class);
        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @DirtiesContext
    void shouldUpdateProperlyAnExistingTeam() {
        var newTeam = new Team("Ferrari", "Scuderia Ferrari");
        var request = new HttpEntity<>(newTeam);

        var putResponse = restTemplate.exchange("/api/f1/teams/1", HttpMethod.PUT, request, Void.class);
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        var getResponse = restTemplate.getForEntity("/api/f1/teams/1", Team.class);
        assertThat(Objects.requireNonNull(getResponse.getBody()).getFullName())
                .isEqualTo("Scuderia Ferrari");
    }

    @Test
    void shouldNotUpdateATeamWhenItDoesNotExist() {
        var newTeam = new Team("Ferrari", "Scuderia Ferrari 2");
        var request = new HttpEntity<>(newTeam);

        var putResponse = restTemplate.exchange("/api/f1/teams/1000", HttpMethod.PUT, request, Void.class);
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DirtiesContext
    void shouldDeleteAnExistingTeam() {
        var deleteResponse = restTemplate.exchange(
                "/api/f1/teams/1",
                HttpMethod.DELETE,
                null,
                Void.class);
        assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        var getResponse = restTemplate.getForEntity("/api/f1/teams/1", String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldNotDeleteATeamWhenItDoesNotExist() {
        var deleteResponse = restTemplate.exchange(
                "/api/f1/teams/1000",
                HttpMethod.DELETE,
                null,
                Void.class);
        assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DirtiesContext
    void shouldCreateANewRaceProperly() {
        var newRace = new Race(
                "FORMULA 1 LOUIS VUITTON AUSTRALIAN GRAND PRIX 2025",
                LocalDate.of(2025, Month.MARCH, 16));

        var postResponse = restTemplate.postForEntity("/api/f1/races", newRace, Void.class);
        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        var newRaceLocation = postResponse.getHeaders().getLocation();
        assertThat(newRaceLocation).isNotNull();

        var getResponse = restTemplate.getForEntity(newRaceLocation, String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldNotCreateANewRaceWithInvalidData() {
        var invalidRace = new Race("   ", LocalDate.of(2025, Month.MARCH, 16));
        var postResponse = restTemplate.postForEntity("/api/f1/races", invalidRace, Void.class);
        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @DirtiesContext
    void shouldUpdateProperlyAnExistingRace() {
        // Create a valid race
        var newRace = new Race(
                "FORMULA 1 LOUIS VUITTON AUSTRALIAN GRAND PRIX 2025",
                LocalDate.of(2025, Month.MARCH, 16));
        var postResponse = restTemplate.postForEntity("/api/f1/races", newRace, Void.class);
        var raceLocation = postResponse.getHeaders().getLocation();
        assertThat(raceLocation).isNotNull();

        // Prepare updated race data
        var updatedRace = new Race(
                "FORMULA 1 LOUIS VUITTON AUSTRALIAN GRAND PRIX UPDATED",
                LocalDate.of(2025, Month.APRIL, 16));
        // Send PUT request with the updated race
        var request = new HttpEntity<>(updatedRace);
        var putResponse = restTemplate.exchange(raceLocation, HttpMethod.PUT, request, Void.class);
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        // Verify the update with a GET call
        var getResponse = restTemplate.getForEntity(raceLocation, Race.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(getResponse.getBody()).getName())
                .isEqualTo("FORMULA 1 LOUIS VUITTON AUSTRALIAN GRAND PRIX UPDATED");
    }

    @Test
    void shouldNotUpdateARaceWhenItDoesNotExist() {
        // Prepare data for a race that does not exist
        var updatedRace = new Race(
                "NON-EXISTENT RACE",
                LocalDate.of(2025, Month.MAY, 16)
        );
        var request = new HttpEntity<>(updatedRace);

        // Use an ID that does not exist (e.g., 1000)
        var putResponse = restTemplate.exchange("/api/f1/races/1000", HttpMethod.PUT, request, Void.class);

        // Assert that the response status is NOT_FOUND
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
