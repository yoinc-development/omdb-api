package ch.yoinc.client;

import ch.yoinc.exceptions.OmdbNotFoundException;
import ch.yoinc.models.*;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class OmdbClientImpl implements OmdbClient {

    private final String BASE_URL = "http://www.omdbapi.com/";
    private final String apiKey;
    private final HttpClient httpClient;

    protected OmdbClientImpl(String apiKey) {
        this.apiKey = apiKey;
        this.httpClient = HttpClient.newHttpClient();
    }

    @Override
    public OmdbResponse getForTitle(String title, Plot plot) throws IOException, InterruptedException, OmdbNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL)
                .append("?apikey=").append(apiKey)
                .append("&t=").append(cleanTitle(title));
        return get(title, plot, sb);
    }

    @Override
    public OmdbResponse getForTitle(String title, Plot plot, String year) throws IOException, InterruptedException, OmdbNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL)
                .append("?apikey=").append(apiKey)
                .append("&t=").append(cleanTitle(title))
                .append("&y=").append(year);
        return get(title, plot, sb);
    }

    @Override
    public OmdbResponse getForImdbId(String title, Plot plot) throws IOException, InterruptedException, OmdbNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL)
                .append("?apikey=").append(apiKey)
                .append("&i=").append(cleanTitle(title));
        return get(title, plot, sb);    }

    @Override
    public OmdbResponse getForImdbId(String title, Plot plot, String year) throws IOException, InterruptedException, OmdbNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL)
                .append("?apikey=").append(apiKey)
                .append("&i=").append(cleanTitle(title))
                .append("&y=").append(year);
        return get(title, plot, sb);
    }


    @Override
    public OmdbMovie getMovieByTitle(String title, Plot plot) throws IOException, InterruptedException, OmdbNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL)
                .append("?apikey=").append(apiKey)
                .append("&t=").append(cleanTitle(title))
                .append("&type=movie");
        return getMovie(title, plot, sb);
    }

    @Override
    public OmdbMovie getMovieByTitle(String title, Plot plot, String year) throws IOException, InterruptedException, OmdbNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL)
                .append("?apikey=").append(apiKey)
                .append("&t=").append(cleanTitle(title))
                .append("&type=movie")
                .append("&y=").append(year);
        return getMovie(title, plot, sb);
    }

    @Override
    public OmdbMovie getMovieByImdbId(String imdbId, Plot plot) throws IOException, InterruptedException, OmdbNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL)
                .append("?apikey=").append(apiKey)
                .append("&i=").append(imdbId)
                .append("&type=movie");
        return getMovie(imdbId, plot, sb);
    }

    @Override
    public OmdbMovie getMovieByImdbId(String imdbId, Plot plot, String year) throws IOException, InterruptedException, OmdbNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL)
                .append("?apikey=").append(apiKey)
                .append("&i=").append(imdbId)
                .append("&type=movie")
                .append("&y=").append(year);
        return getMovie(imdbId, plot, sb);
    }

    @Override
    public OmdbSeries getSeriesByTitle(String title, Plot plot) throws IOException, InterruptedException, OmdbNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL)
                .append("?apikey=").append(apiKey)
                .append("&t=").append(cleanTitle(title))
                .append("&type=series");
        return getSeries(title, plot, sb);
    }

    @Override
    public OmdbSeries getSeriesByTitle(String title, Plot plot, String year) throws IOException, InterruptedException, OmdbNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL)
                .append("?apikey=").append(apiKey)
                .append("&t=").append(cleanTitle(title))
                .append("&type=series")
                .append("&y=").append(year);
        return getSeries(title, plot, sb);
    }

    @Override
    public OmdbSeries getSeriesByImdbId(String imdbId, Plot plot) throws IOException, InterruptedException, OmdbNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL)
                .append("?apikey=").append(apiKey)
                .append("&i=").append(imdbId)
                .append("&type=series");
        return getSeries(imdbId, plot, sb);
    }

    @Override
    public OmdbSeries getSeriesByImdbId(String imdbId, Plot plot, String year) throws IOException, InterruptedException, OmdbNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL)
                .append("?apikey=").append(apiKey)
                .append("&i=").append(imdbId)
                .append("&type=series")
                .append("&y=").append(year);
        return getSeries(imdbId, plot, sb);
    }

    @Override
    public OmdbEpisode getEpisodeByTitle(String title, Plot plot) throws IOException, InterruptedException, OmdbNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL)
                .append("?apikey=").append(apiKey)
                .append("&t=").append(cleanTitle(title))
                .append("&type=episode");
        return getEpisode(title, plot, sb);
    }

    @Override
    public OmdbEpisode getEpisodeByTitle(String title, Plot plot, String year) throws IOException, InterruptedException, OmdbNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL)
                .append("?apikey=").append(apiKey)
                .append("&t=").append(cleanTitle(title))
                .append("&type=episode")
                .append("&y=").append(year);
        return getEpisode(title, plot, sb);
    }

    @Override
    public OmdbEpisode getEpisodeByImdbId(String imdbId, Plot plot) throws IOException, InterruptedException, OmdbNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL)
                .append("?apikey=").append(apiKey)
                .append("&i=").append(imdbId)
                .append("&type=episode");
        return getEpisode(imdbId, plot, sb);
    }

    @Override
    public OmdbEpisode getEpisodeByImdbId(String imdbId, Plot plot, String year) throws IOException, InterruptedException, OmdbNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL)
                .append("?apikey=").append(apiKey)
                .append("&i=").append(imdbId)
                .append("&type=episode")
                .append("&y=").append(year);
        return getEpisode(imdbId, plot, sb);
    }

    @Override
    public OmdbSearchResponse searchAll(String title) throws IOException, InterruptedException, OmdbNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL)
                .append("?apikey=").append(apiKey)
                .append("&s=").append(cleanTitle(title));
        return search(title, sb);
    }

    @Override
    public OmdbSearchResponse searchAll(String title, int page) throws IOException, InterruptedException, OmdbNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL)
                .append("?apikey=").append(apiKey)
                .append("&s=").append(cleanTitle(title))
                .append("&page=").append(page);
        return search(title, sb);
    }

    @Override
    public OmdbSearchResponse searchAll(String title, String year) throws IOException, InterruptedException, OmdbNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL)
                .append("?apikey=").append(apiKey)
                .append("&s=").append(cleanTitle(title))
                .append("&y=").append(year);
        return search(title, sb);
    }

    @Override
    public OmdbSearchResponse searchAll(String title, String year, int page) throws IOException, InterruptedException, OmdbNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL)
                .append("?apikey=").append(apiKey)
                .append("&s=").append(cleanTitle(title))
                .append("&y=").append(year)
                .append("&page=").append(page);
        return search(title, sb);
    }

    @Override
    public OmdbSearchResponse searchMovie(String title) throws IOException, InterruptedException, OmdbNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL)
                .append("?apikey=").append(apiKey)
                .append("&s=").append(cleanTitle(title))
                .append("&type=movie");
        return search(title, sb);
    }

    @Override
    public OmdbSearchResponse searchMovie(String title, int page) throws IOException, InterruptedException, OmdbNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL)
                .append("?apikey=").append(apiKey)
                .append("&s=").append(cleanTitle(title))
                .append("&type=movie")
                .append("&page=").append(page);
        return search(title, sb);
    }

    @Override
    public OmdbSearchResponse searchMovie(String title, String year) throws IOException, InterruptedException, OmdbNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL)
                .append("?apikey=").append(apiKey)
                .append("&s=").append(cleanTitle(title))
                .append("&type=movie")
                .append("&y=").append(year);
        return search(title, sb);
    }

    @Override
    public OmdbSearchResponse searchMovie(String title, String year, int page) throws IOException, InterruptedException, OmdbNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL)
                .append("?apikey=").append(apiKey)
                .append("&s=").append(cleanTitle(title))
                .append("&type=movie")
                .append("&y=").append(year)
                .append("&page=").append(page);
        return search(title, sb);
    }

    @Override
    public OmdbSearchResponse searchSeries(String title) throws IOException, InterruptedException, OmdbNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL)
                .append("?apikey=").append(apiKey)
                .append("&s=").append(cleanTitle(title))
                .append("&type=series");
        return search(title, sb);
    }

    @Override
    public OmdbSearchResponse searchSeries(String title, int page) throws IOException, InterruptedException, OmdbNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL)
                .append("?apikey=").append(apiKey)
                .append("&s=").append(cleanTitle(title))
                .append("&type=series")
                .append("&page=").append(page);
        return search(title, sb);
    }

    @Override
    public OmdbSearchResponse searchSeries(String title, String year) throws IOException, InterruptedException, OmdbNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL)
                .append("?apikey=").append(apiKey)
                .append("&s=").append(cleanTitle(title))
                .append("&type=series")
                .append("&y=").append(year);
        return search(title, sb);
    }

    @Override
    public OmdbSearchResponse searchSeries(String title, String year, int page) throws IOException, InterruptedException, OmdbNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL)
                .append("?apikey=").append(apiKey)
                .append("&s=").append(cleanTitle(title))
                .append("&type=series")
                .append("&y=").append(year)
                .append("&page=").append(page);
        return search(title, sb);
    }

    protected OmdbResponse get(String query, Plot plot, StringBuilder sb) throws IOException, InterruptedException, OmdbNotFoundException {
        if (plot == Plot.FULL) {
            sb.append("&plot=full");
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(sb.toString()))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        OmdbResponse omdbResponse = new Gson().fromJson(response.body(), OmdbResponse.class);
        if ("True".equals(omdbResponse.getResponse())) {
            if ("movie".equals(omdbResponse.getType())) {
                return new Gson().fromJson(response.body(), OmdbMovie.class);
            } else if ("series".equals(omdbResponse.getType())) {
                return new Gson().fromJson(response.body(), OmdbSeries.class);
            } else if ("episode".equals(omdbResponse.getType())) {
                return new Gson().fromJson(response.body(), OmdbEpisode.class);
            }
        }
        throw new OmdbNotFoundException("No item was found for this query: " + query);
    }
    protected OmdbMovie getMovie(String query, Plot plot, StringBuilder sb) throws IOException, InterruptedException, OmdbNotFoundException {
        if (plot == Plot.FULL) {
            sb.append("&plot=full");
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(sb.toString()))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        OmdbMovie omdbMovie = new Gson().fromJson(response.body(), OmdbMovie.class);
        if ("True".equals(omdbMovie.getResponse()) && "movie".equals(omdbMovie.getType())) {
            return omdbMovie;
        }
        throw new OmdbNotFoundException("No movie was found for this query: " + query);
    }

    protected OmdbSeries getSeries(String query, Plot plot, StringBuilder sb) throws IOException, InterruptedException, OmdbNotFoundException {
        if (plot == Plot.FULL) {
            sb.append("&plot=full");
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(sb.toString()))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        OmdbSeries omdbSeries = new Gson().fromJson(response.body(), OmdbSeries.class);
        if ("True".equals(omdbSeries.getResponse()) && "series".equals(omdbSeries.getType())) {
            return omdbSeries;
        }
        throw new OmdbNotFoundException("No series was found for this query: " + query);
    }

    private OmdbEpisode getEpisode(String title, Plot plot, StringBuilder sb) throws IOException, InterruptedException, OmdbNotFoundException {
        if (plot == Plot.FULL) {
            sb.append("&plot=full");
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(sb.toString()))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        OmdbEpisode omdbEpisode = new Gson().fromJson(response.body(), OmdbEpisode.class);
        if ("episode".equals(omdbEpisode.getType())) {
            return omdbEpisode;
        }
        throw new OmdbNotFoundException("No episode was found for this query: " + title);
    }

    private OmdbSearchResponse search(String title, StringBuilder sb) throws IOException, InterruptedException, OmdbNotFoundException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(sb.toString()))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        OmdbSearchResponse omdbSearchResponse = new Gson().fromJson(response.body(), OmdbSearchResponse.class);
        if ("True".equals(omdbSearchResponse.getResponse())) {
            return omdbSearchResponse;
        }
        throw new OmdbNotFoundException("No search results were found for this query: " + title);
    }

    protected String cleanTitle(String title) {
        return URLEncoder.encode(title.trim().toLowerCase().replaceAll(" +", "+"), StandardCharsets.UTF_8);
    }
}
