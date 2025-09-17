package ch.yoinc.client;

import ch.yoinc.models.OmdbEpisode;
import ch.yoinc.models.OmdbMovie;
import ch.yoinc.models.OmdbResponse;
import ch.yoinc.models.OmdbSeries;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OmdbClientImplTest {

    private final String MOVIE_RESPONSE = "{\"Title\":\"Double Trouble\",\"Year\":\"1984\",\"Rated\":\"TV-PG\",\"Released\":\"16 Nov 1984\",\"Runtime\":\"99 min\",\"Genre\":\"Action, Comedy\",\"Director\":\"Enzo Barboni\",\"Writer\":\"Marco Barboni\",\"Actors\":\"Terence Hill, Bud Spencer, April Clough\",\"Plot\":\"A jazz musician and a stunt man are the spitting image of two millionaire brothers. They take the job to replace them in Rio de Janeiro when someone plans to assassinate them.\",\"Language\":\"Italian, English, Portuguese\",\"Country\":\"Italy, Brazil\",\"Awards\":\"N/A\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BZjU0NGFkYzgtZDA3Ny00ZTk3LWExMjgtNTI5ZTMzZTkwNWQ4XkEyXkFqcGc@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"7.1/10\"}],\"Metascore\":\"N/A\",\"imdbRating\":\"7.1\",\"imdbVotes\":\"11,710\",\"imdbID\":\"tt0087481\",\"Type\":\"movie\",\"DVD\":\"N/A\",\"BoxOffice\":\"N/A\",\"Production\":\"N/A\",\"Website\":\"N/A\",\"Response\":\"True\"}";
    private final String SERIES_RESPONSE = "{\"Title\":\"Arrested Development\",\"Year\":\"2003–2019\",\"Rated\":\"TV-14\",\"Released\":\"02 Nov 2003\",\"Runtime\":\"22 min\",\"Genre\":\"Comedy\",\"Director\":\"N/A\",\"Writer\":\"Mitchell Hurwitz\",\"Actors\":\"Jason Bateman, Michael Cera, Portia de Rossi\",\"Plot\":\"Level-headed son Michael Bluth takes over family affairs after his father is imprisoned. But the rest of his spoiled, dysfunctional family are making his job unbearable.\",\"Language\":\"English\",\"Country\":\"United States\",\"Awards\":\"Won 6 Primetime Emmys. 61 wins & 123 nominations total\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BOTk3N2M0MjktMDhkNi00ZGVhLTk4YTUtYjI5MDJmMWUzNTgwXkEyXkFqcGc@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"8.6/10\"}],\"Metascore\":\"N/A\",\"imdbRating\":\"8.6\",\"imdbVotes\":\"337,488\",\"imdbID\":\"tt0367279\",\"Type\":\"series\",\"totalSeasons\":\"5\",\"Response\":\"True\"}";
    private final String EPISODE_RESPONSE = "{\"Title\":\"Made in America\",\"Year\":\"2007\",\"Rated\":\"TV-MA\",\"Released\":\"10 Jun 2007\",\"Season\":\"6\",\"Episode\":\"21\",\"Runtime\":\"62 min\",\"Genre\":\"Crime, Drama\",\"Director\":\"David Chase\",\"Writer\":\"David Chase\",\"Actors\":\"James Gandolfini, Edie Falco, Michael Imperioli\",\"Plot\":\"Tony and his family have to stay in hiding until Phil Leotardo is dealt with. His family doesn't like the lifestyle they have been forced to adopt. AJ and Meadow move onto the next step in their lives. Tony confronts Junior one la...\",\"Language\":\"English\",\"Country\":\"United States\",\"Awards\":\"N/A\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BNDU3MDAwZDAtM2YxNy00MjllLWE2ZWQtZGI0MTgxYTYwOGFlXkEyXkFqcGc@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"9.4/10\"}],\"Metascore\":\"N/A\",\"imdbRating\":\"9.4\",\"imdbVotes\":\"14470\",\"imdbID\":\"tt0995839\",\"seriesID\":\"tt0141842\",\"Type\":\"episode\",\"Response\":\"True\"}";
    HttpClient mockClient = mock(HttpClient.class);
    private OmdbClientImpl omdbClient;

    @BeforeEach
    void setUp() {
        omdbClient = new OmdbClientBuilder()
                .apiKey("test-api-key")
                .build();
    }

    @Test
    void testGet() throws Exception {
        HttpResponse mockResponse = mock(HttpResponse.class);

        when(mockResponse.body()).thenReturn(MOVIE_RESPONSE);
        when(mockClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        Field clientField = OmdbClientImpl.class.getDeclaredField("httpClient");
        clientField.setAccessible(true);
        clientField.set(omdbClient, mockClient);

        OmdbResponse result = omdbClient.getForTitle("Double Trouble", Plot.SHORT);

        assertAll(
                "All correct cases",
                () -> assertNotNull(result),
                () -> assertTrue(result instanceof OmdbMovie),
                () -> assertEquals("N/A", ((OmdbMovie) result).getDVD()),
                () -> assertEquals("N/A", ((OmdbMovie) result).getBoxOffice()),
                () -> assertEquals("N/A", ((OmdbMovie) result).getProduction()),
                () -> assertEquals("N/A", ((OmdbMovie) result).getWebsite())
        );
    }

    @Test
    void testGetMovieByTitle() throws Exception {
        HttpResponse mockResponse = mock(HttpResponse.class);

        when(mockResponse.body()).thenReturn(MOVIE_RESPONSE);
        when(mockClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        Field clientField = OmdbClientImpl.class.getDeclaredField("httpClient");
        clientField.setAccessible(true);
        clientField.set(omdbClient, mockClient);

        OmdbMovie result = omdbClient.getMovieByTitle("Double Trouble", Plot.SHORT);

        assertAll(
                "All correct cases",
                () -> assertNotNull(result),
                () -> assertEquals("Double Trouble", result.getTitle()),
                () -> assertEquals("1984", result.getYear()),
                () -> assertEquals("TV-PG", result.getRated()),
                () -> assertEquals("16 Nov 1984", result.getReleased()),
                () -> assertEquals("99 min", result.getRuntime()),
                () -> assertEquals("Action, Comedy", result.getGenre()),
                () -> assertEquals("Enzo Barboni", result.getDirector()),
                () -> assertEquals("Marco Barboni", result.getWriter()),
                () -> assertEquals("Terence Hill, Bud Spencer, April Clough", result.getActors()),
                () -> assertEquals("A jazz musician and a stunt man are the spitting image of two millionaire brothers. They take the job to replace them in Rio de Janeiro when someone plans to assassinate them.", result.getPlot()),
                () -> assertEquals("Italian, English, Portuguese", result.getLanguage()),
                () -> assertEquals("Italy, Brazil", result.getCountry()),
                () -> assertEquals("N/A", result.getAwards()),
                () -> assertEquals("https://m.media-amazon.com/images/M/MV5BZjU0NGFkYzgtZDA3Ny00ZTk3LWExMjgtNTI5ZTMzZTkwNWQ4XkEyXkFqcGc@._V1_SX300.jpg", result.getPoster()),
                () -> assertEquals(1, result.getRatings().size()),
                () -> assertEquals("Internet Movie Database", result.getRatings().getFirst().getSource()),
                () -> assertEquals("N/A", result.getMetascore()),
                () -> assertEquals("7.1", result.getImdbRating()),
                () -> assertEquals("11,710", result.getImdbVotes()),
                () -> assertEquals("tt0087481", result.getImdbID()),
                () -> assertEquals("movie", result.getType()),
                () -> assertEquals("N/A", result.getDVD()),
                () -> assertEquals("N/A", result.getBoxOffice()),
                () -> assertEquals("N/A", result.getProduction()),
                () -> assertEquals("N/A", result.getWebsite()),
                () -> assertEquals("True", result.getResponse())
        );
    }

    @Test
    void testGetMovieByTitleAndYear() throws Exception {
        HttpResponse mockResponse = mock(HttpResponse.class);

        when(mockResponse.body()).thenReturn(MOVIE_RESPONSE);
        when(mockClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        Field clientField = OmdbClientImpl.class.getDeclaredField("httpClient");
        clientField.setAccessible(true);
        clientField.set(omdbClient, mockClient);

        OmdbMovie result = omdbClient.getMovieByTitle("Double Trouble", Plot.SHORT, "1984");

        assertAll(
                "All correct cases",
                () -> assertNotNull(result),
                () -> assertEquals("Double Trouble", result.getTitle()),
                () -> assertEquals("1984", result.getYear()),
                () -> assertEquals("TV-PG", result.getRated()),
                () -> assertEquals("16 Nov 1984", result.getReleased()),
                () -> assertEquals("99 min", result.getRuntime()),
                () -> assertEquals("Action, Comedy", result.getGenre()),
                () -> assertEquals("Enzo Barboni", result.getDirector()),
                () -> assertEquals("Marco Barboni", result.getWriter()),
                () -> assertEquals("Terence Hill, Bud Spencer, April Clough", result.getActors()),
                () -> assertEquals("A jazz musician and a stunt man are the spitting image of two millionaire brothers. They take the job to replace them in Rio de Janeiro when someone plans to assassinate them.", result.getPlot()),
                () -> assertEquals("Italian, English, Portuguese", result.getLanguage()),
                () -> assertEquals("Italy, Brazil", result.getCountry()),
                () -> assertEquals("N/A", result.getAwards()),
                () -> assertEquals("https://m.media-amazon.com/images/M/MV5BZjU0NGFkYzgtZDA3Ny00ZTk3LWExMjgtNTI5ZTMzZTkwNWQ4XkEyXkFqcGc@._V1_SX300.jpg", result.getPoster()),
                () -> assertEquals(1, result.getRatings().size()),
                () -> assertEquals("Internet Movie Database", result.getRatings().getFirst().getSource()),
                () -> assertEquals("N/A", result.getMetascore()),
                () -> assertEquals("7.1", result.getImdbRating()),
                () -> assertEquals("11,710", result.getImdbVotes()),
                () -> assertEquals("tt0087481", result.getImdbID()),
                () -> assertEquals("movie", result.getType()),
                () -> assertEquals("N/A", result.getDVD()),
                () -> assertEquals("N/A", result.getBoxOffice()),
                () -> assertEquals("N/A", result.getProduction()),
                () -> assertEquals("N/A", result.getWebsite()),
                () -> assertEquals("True", result.getResponse())
        );
    }

    @Test
    void testGetMovieByImdbId() throws Exception {
        HttpResponse mockResponse = mock(HttpResponse.class);

        when(mockResponse.body()).thenReturn(MOVIE_RESPONSE);
        when(mockClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        Field clientField = OmdbClientImpl.class.getDeclaredField("httpClient");
        clientField.setAccessible(true);
        clientField.set(omdbClient, mockClient);

        OmdbMovie result = omdbClient.getMovieByImdbId("tt0087481", Plot.SHORT);

        assertAll(
                "All correct cases",
                () -> assertNotNull(result),
                () -> assertEquals("Double Trouble", result.getTitle()),
                () -> assertEquals("1984", result.getYear()),
                () -> assertEquals("tt0087481", result.getImdbID()),
                () -> assertEquals("movie", result.getType()),
                () -> assertEquals("True", result.getResponse())
        );
    }

    @Test
    void testGetMovieByImdbIdWithYear() throws Exception {
        HttpResponse mockResponse = mock(HttpResponse.class);

        when(mockResponse.body()).thenReturn(MOVIE_RESPONSE);
        when(mockClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        Field clientField = OmdbClientImpl.class.getDeclaredField("httpClient");
        clientField.setAccessible(true);
        clientField.set(omdbClient, mockClient);

        OmdbMovie result = omdbClient.getMovieByImdbId("tt0087481", Plot.SHORT, "1984");

        assertAll(
                "All correct cases",
                () -> assertNotNull(result),
                () -> assertEquals("Double Trouble", result.getTitle()),
                () -> assertEquals("1984", result.getYear()),
                () -> assertEquals("tt0087481", result.getImdbID()),
                () -> assertEquals("movie", result.getType()),
                () -> assertEquals("True", result.getResponse())
        );
    }

    @Test
    void testGetSeriesByTitle() throws Exception {
        HttpResponse mockResponse = mock(HttpResponse.class);

        when(mockResponse.body()).thenReturn(SERIES_RESPONSE);
        when(mockClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        Field clientField = OmdbClientImpl.class.getDeclaredField("httpClient");
        clientField.setAccessible(true);
        clientField.set(omdbClient, mockClient);

        OmdbSeries result = omdbClient.getSeriesByTitle("Arrested Development", Plot.SHORT);

        assertAll(
                "All correct cases",
                () -> assertNotNull(result),
                () -> assertEquals("Arrested Development", result.getTitle()),
                () -> assertEquals("2003–2019", result.getYear()),
                () -> assertEquals("TV-14", result.getRated()),
                () -> assertEquals("02 Nov 2003", result.getReleased()),
                () -> assertEquals("22 min", result.getRuntime()),
                () -> assertEquals("Comedy", result.getGenre()),
                () -> assertEquals("Mitchell Hurwitz", result.getWriter()),
                () -> assertEquals("Jason Bateman, Michael Cera, Portia de Rossi", result.getActors()),
                () -> assertEquals("Level-headed son Michael Bluth takes over family affairs after his father is imprisoned. But the rest of his spoiled, dysfunctional family are making his job unbearable.", result.getPlot()),
                () -> assertEquals("English", result.getLanguage()),
                () -> assertEquals("United States", result.getCountry()),
                () -> assertEquals("Won 6 Primetime Emmys. 61 wins & 123 nominations total", result.getAwards()),
                () -> assertEquals("https://m.media-amazon.com/images/M/MV5BOTk3N2M0MjktMDhkNi00ZGVhLTk4YTUtYjI5MDJmMWUzNTgwXkEyXkFqcGc@._V1_SX300.jpg", result.getPoster()),
                () -> assertEquals(1, result.getRatings().size()),
                () -> assertEquals("Internet Movie Database", result.getRatings().getFirst().getSource()),
                () -> assertEquals("8.6/10", result.getRatings().getFirst().getValue()),
                () -> assertEquals("N/A", result.getMetascore()),
                () -> assertEquals("8.6", result.getImdbRating()),
                () -> assertEquals("337,488", result.getImdbVotes()),
                () -> assertEquals("tt0367279", result.getImdbID()),
                () -> assertEquals("series", result.getType()),
                () -> assertEquals("5", result.getTotalSeasons()),
                () -> assertEquals("True", result.getResponse())
        );
    }

    @Test
    void testGetSeriesByTitleWithYear() throws Exception {
        HttpResponse mockResponse = mock(HttpResponse.class);

        when(mockResponse.body()).thenReturn(SERIES_RESPONSE);
        when(mockClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        Field clientField = OmdbClientImpl.class.getDeclaredField("httpClient");
        clientField.setAccessible(true);
        clientField.set(omdbClient, mockClient);

        OmdbSeries result = omdbClient.getSeriesByTitle("Arrested Development", Plot.SHORT, "2003");

        assertAll(
                "All correct cases",
                () -> assertNotNull(result),
                () -> assertEquals("Arrested Development", result.getTitle()),
                () -> assertEquals("2003–2019", result.getYear()),
                () -> assertEquals("tt0367279", result.getImdbID()),
                () -> assertEquals("series", result.getType()),
                () -> assertEquals("5", result.getTotalSeasons()),
                () -> assertEquals("True", result.getResponse())
        );
    }

    @Test
    void testGetSeriesByImdbId() throws Exception {
        HttpResponse mockResponse = mock(HttpResponse.class);

        when(mockResponse.body()).thenReturn(SERIES_RESPONSE);
        when(mockClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        Field clientField = OmdbClientImpl.class.getDeclaredField("httpClient");
        clientField.setAccessible(true);
        clientField.set(omdbClient, mockClient);

        OmdbSeries result = omdbClient.getSeriesByImdbId("tt0367279", Plot.SHORT);

        assertAll(
                "All correct cases",
                () -> assertNotNull(result),
                () -> assertEquals("Arrested Development", result.getTitle()),
                () -> assertEquals("2003–2019", result.getYear()),
                () -> assertEquals("tt0367279", result.getImdbID()),
                () -> assertEquals("series", result.getType()),
                () -> assertEquals("5", result.getTotalSeasons()),
                () -> assertEquals("True", result.getResponse())
        );
    }

    @Test
    void testGetSeriesByImdbIdWithYear() throws Exception {
        HttpResponse mockResponse = mock(HttpResponse.class);

        when(mockResponse.body()).thenReturn(SERIES_RESPONSE);
        when(mockClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        Field clientField = OmdbClientImpl.class.getDeclaredField("httpClient");
        clientField.setAccessible(true);
        clientField.set(omdbClient, mockClient);

        OmdbSeries result = omdbClient.getSeriesByImdbId("tt0367279", Plot.SHORT, "2003");

        assertAll(
                "All correct cases",
                () -> assertNotNull(result),
                () -> assertEquals("Arrested Development", result.getTitle()),
                () -> assertEquals("2003–2019", result.getYear()),
                () -> assertEquals("tt0367279", result.getImdbID()),
                () -> assertEquals("series", result.getType()),
                () -> assertEquals("5", result.getTotalSeasons()),
                () -> assertEquals("True", result.getResponse())
        );
    }

    @Test
    void testGetEpisodeByTitle() throws Exception {
        HttpResponse mockResponse = mock(HttpResponse.class);

        when(mockResponse.body()).thenReturn(EPISODE_RESPONSE);
        when(mockClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        Field clientField = OmdbClientImpl.class.getDeclaredField("httpClient");
        clientField.setAccessible(true);
        clientField.set(omdbClient, mockClient);

        OmdbEpisode result = omdbClient.getEpisodeByTitle("Made in America", Plot.SHORT);

        assertAll(
                "All correct cases",
                () -> assertNotNull(result),
                () -> assertEquals("Made in America", result.getTitle()),
                () -> assertEquals("2007", result.getYear()),
                () -> assertEquals("TV-MA", result.getRated()),
                () -> assertEquals("10 Jun 2007", result.getReleased()),
                () -> assertEquals("6", result.getSeason()),
                () -> assertEquals("21", result.getEpisode()),
                () -> assertEquals("62 min", result.getRuntime()),
                () -> assertEquals("Crime, Drama", result.getGenre()),
                () -> assertEquals("David Chase", result.getDirector()),
                () -> assertEquals("David Chase", result.getWriter()),
                () -> assertEquals("James Gandolfini, Edie Falco, Michael Imperioli", result.getActors()),
                () -> assertEquals("Tony and his family have to stay in hiding until Phil Leotardo is dealt with. His family doesn't like the lifestyle they have been forced to adopt. AJ and Meadow move onto the next step in their lives. Tony confronts Junior one la...", result.getPlot()),
                () -> assertEquals("English", result.getLanguage()),
                () -> assertEquals("United States", result.getCountry()),
                () -> assertEquals("N/A", result.getAwards()),
                () -> assertEquals("https://m.media-amazon.com/images/M/MV5BNDU3MDAwZDAtM2YxNy00MjllLWE2ZWQtZGI0MTgxYTYwOGFlXkEyXkFqcGc@._V1_SX300.jpg", result.getPoster()),
                () -> assertEquals(1, result.getRatings().size()),
                () -> assertEquals("Internet Movie Database", result.getRatings().getFirst().getSource()),
                () -> assertEquals("9.4/10", result.getRatings().getFirst().getValue()),
                () -> assertEquals("N/A", result.getMetascore()),
                () -> assertEquals("9.4", result.getImdbRating()),
                () -> assertEquals("14470", result.getImdbVotes()),
                () -> assertEquals("tt0995839", result.getImdbID()),
                () -> assertEquals("tt0141842", result.getSeriesID()),
                () -> assertEquals("episode", result.getType()),
                () -> assertEquals("True", result.getResponse())
        );
    }

    @Test
    void testGetEpisodeByTitleWithYear() throws Exception {
        HttpResponse mockResponse = mock(HttpResponse.class);

        when(mockResponse.body()).thenReturn(EPISODE_RESPONSE);
        when(mockClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        Field clientField = OmdbClientImpl.class.getDeclaredField("httpClient");
        clientField.setAccessible(true);
        clientField.set(omdbClient, mockClient);

        OmdbEpisode result = omdbClient.getEpisodeByTitle("Made in America", Plot.SHORT, "2007");

        assertAll(
                "All correct cases",
                () -> assertNotNull(result),
                () -> assertEquals("Made in America", result.getTitle()),
                () -> assertEquals("2007", result.getYear()),
                () -> assertEquals("6", result.getSeason()),
                () -> assertEquals("21", result.getEpisode()),
                () -> assertEquals("tt0995839", result.getImdbID()),
                () -> assertEquals("tt0141842", result.getSeriesID()),
                () -> assertEquals("episode", result.getType()),
                () -> assertEquals("True", result.getResponse())
        );
    }

    @Test
    void testGetEpisodeByImdbId() throws Exception {
        HttpResponse mockResponse = mock(HttpResponse.class);

        when(mockResponse.body()).thenReturn(EPISODE_RESPONSE);
        when(mockClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        Field clientField = OmdbClientImpl.class.getDeclaredField("httpClient");
        clientField.setAccessible(true);
        clientField.set(omdbClient, mockClient);

        OmdbEpisode result = omdbClient.getEpisodeByImdbId("tt0995839", Plot.SHORT);

        assertAll(
                "All correct cases",
                () -> assertNotNull(result),
                () -> assertEquals("Made in America", result.getTitle()),
                () -> assertEquals("2007", result.getYear()),
                () -> assertEquals("6", result.getSeason()),
                () -> assertEquals("21", result.getEpisode()),
                () -> assertEquals("tt0995839", result.getImdbID()),
                () -> assertEquals("tt0141842", result.getSeriesID()),
                () -> assertEquals("episode", result.getType()),
                () -> assertEquals("True", result.getResponse())
        );
    }

    @Test
    void testGetEpisodeByImdbIdWithYear() throws Exception {
        HttpResponse mockResponse = mock(HttpResponse.class);

        when(mockResponse.body()).thenReturn(EPISODE_RESPONSE);
        when(mockClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        Field clientField = OmdbClientImpl.class.getDeclaredField("httpClient");
        clientField.setAccessible(true);
        clientField.set(omdbClient, mockClient);

        OmdbEpisode result = omdbClient.getEpisodeByImdbId("tt0995839", Plot.SHORT, "2007");

        assertAll(
                "All correct cases",
                () -> assertNotNull(result),
                () -> assertEquals("Made in America", result.getTitle()),
                () -> assertEquals("2007", result.getYear()),
                () -> assertEquals("6", result.getSeason()),
                () -> assertEquals("21", result.getEpisode()),
                () -> assertEquals("tt0995839", result.getImdbID()),
                () -> assertEquals("tt0141842", result.getSeriesID()),
                () -> assertEquals("episode", result.getType()),
                () -> assertEquals("True", result.getResponse())
        );
    }
}
