package ch.bbw.m323;

import ch.bbw.m323.model.WeatherEntry;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    @Test
    void testParseWeatherEntry() {
        String line = "2024-01-01,Zurich,2.5,85,0.0,12,Cloudy";
        WeatherEntry entry = App.parseWeatherEntry(line);

        assertEquals(LocalDate.of(2024, 1, 1), entry.getDate());
        assertEquals("Zurich", entry.getCity());
        assertEquals(2.5, entry.getTemperature());
        assertEquals(85, entry.getHumidity());
        assertEquals(0.0, entry.getPrecipitation());
        assertEquals(12, entry.getWindSpeed());
        assertEquals("Cloudy", entry.getWeatherCondition());
    }
}
