package ch.yoinc.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OmdbResponse {

    private String Title;
    private String Year;
    private String Rated;
    private String Released;
    private String Runtime;
    private String Genre;
    private String Director;
    private String Writer;
    private String Actors;
    private String Plot;
    private String Language;
    private String Country;
    private String Awards;
    private String Poster;
    private List<OmdbRating> Ratings;
    private String Metascore;
    private String imdbRating;
    private String imdbVotes;
    private String imdbID;
    private String Type;
    private String Response;

    public OmdbResponse(String Title, String Year, String Rated, String Released, String Runtime, String Genre, String Director, String Writer, String Actors, String Plot, String Language, String Country, String Awards, String Poster, List<OmdbRating> Ratings, String Metascore, String imdbRating, String imdbVotes, String imdbID, String Type, String Response) {
        this.Title = Title;
        this.Year = Year;
        this.Rated = Rated;
        this.Released = Released;
        this.Runtime = Runtime;
        this.Genre = Genre;
        this.Director = Director;
        this.Writer = Writer;
        this.Actors = Actors;
        this.Plot = Plot;
        this.Language = Language;
        this.Country = Country;
        this.Awards = Awards;
        this.Poster = Poster;
        this.Ratings = Ratings;
        this.Metascore = Metascore;
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
        this.imdbID = imdbID;
        this.Type = Type;
        this.Response = Response;
    }
}
