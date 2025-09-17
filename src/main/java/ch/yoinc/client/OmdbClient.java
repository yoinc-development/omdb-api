package ch.yoinc.client;

import ch.yoinc.exceptions.OmdbNotFoundException;
import ch.yoinc.models.*;

import java.io.IOException;

public interface OmdbClient {

    OmdbResponse getForTitle(String title, Plot plot) throws IOException, InterruptedException, OmdbNotFoundException;
    OmdbResponse getForTitle(String title, Plot plot, String year) throws IOException, InterruptedException, OmdbNotFoundException;
    OmdbResponse getForImdbId(String title, Plot plot) throws IOException, InterruptedException, OmdbNotFoundException;
    OmdbResponse getForImdbId(String title, Plot plot, String year) throws IOException, InterruptedException, OmdbNotFoundException;
    OmdbMovie getMovieByTitle(String title, Plot plot) throws IOException, InterruptedException, OmdbNotFoundException;
    OmdbMovie getMovieByTitle(String title, Plot plot, String year) throws IOException, InterruptedException, OmdbNotFoundException;
    OmdbMovie getMovieByImdbId(String imdbId, Plot plot) throws IOException, InterruptedException, OmdbNotFoundException;
    OmdbMovie getMovieByImdbId(String imdbId, Plot plot, String year) throws IOException, InterruptedException, OmdbNotFoundException;
    OmdbSeries getSeriesByTitle(String title, Plot plot) throws IOException, InterruptedException, OmdbNotFoundException;
    OmdbSeries getSeriesByTitle(String title, Plot plot, String year) throws IOException, InterruptedException, OmdbNotFoundException;
    OmdbSeries getSeriesByImdbId(String imdbId, Plot plot) throws IOException, InterruptedException, OmdbNotFoundException;
    OmdbSeries getSeriesByImdbId(String imdbId, Plot plot, String year) throws IOException, InterruptedException, OmdbNotFoundException;
    OmdbEpisode getEpisodeByTitle(String title, Plot plot) throws IOException, InterruptedException, OmdbNotFoundException;
    OmdbEpisode getEpisodeByTitle(String title, Plot plot, String year) throws IOException, InterruptedException, OmdbNotFoundException;
    OmdbEpisode getEpisodeByImdbId(String imdbId, Plot plot) throws IOException, InterruptedException, OmdbNotFoundException;
    OmdbEpisode getEpisodeByImdbId(String imdbId, Plot plot, String year) throws IOException, InterruptedException, OmdbNotFoundException;
    OmdbSearchResponse searchAll(String title) throws IOException, InterruptedException, OmdbNotFoundException;
    OmdbSearchResponse searchAll(String title, int page) throws IOException, InterruptedException, OmdbNotFoundException;
    OmdbSearchResponse searchAll(String title, String year) throws IOException, InterruptedException, OmdbNotFoundException;
    OmdbSearchResponse searchAll(String title, String year, int page) throws IOException, InterruptedException, OmdbNotFoundException;
    OmdbSearchResponse searchMovie(String title) throws IOException, InterruptedException, OmdbNotFoundException;
    OmdbSearchResponse searchMovie(String title, int page) throws IOException, InterruptedException, OmdbNotFoundException;
    OmdbSearchResponse searchMovie(String title, String year) throws IOException, InterruptedException, OmdbNotFoundException;
    OmdbSearchResponse searchMovie(String title, String year, int page) throws IOException, InterruptedException, OmdbNotFoundException;
    OmdbSearchResponse searchSeries(String title) throws IOException, InterruptedException, OmdbNotFoundException;
    OmdbSearchResponse searchSeries(String title, int page) throws IOException, InterruptedException, OmdbNotFoundException;
    OmdbSearchResponse searchSeries(String title, String year) throws IOException, InterruptedException, OmdbNotFoundException;
    OmdbSearchResponse searchSeries(String title, String year, int page) throws IOException, InterruptedException, OmdbNotFoundException;
}
