package es.discerial.backend.health;

import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HealthControllerTest {
    @Test
    void returnsOkWhenPostgresResponds() {
        var jdbc = mock(JdbcTemplate.class);
        when(jdbc.queryForObject("SELECT 1", Integer.class)).thenReturn(1);
        var response = new HealthController(jdbc).health();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("connected", response.getBody().database());
    }

    @Test
    void returnsUnavailableWhenDatabaseFails() {
        var jdbc = mock(JdbcTemplate.class);
        when(jdbc.queryForObject("SELECT 1", Integer.class))
                .thenThrow(new DataAccessResourceFailureException("connection unavailable"));
        var response = new HealthController(jdbc).health();
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
        assertEquals("unavailable", response.getBody().database());
    }
}
