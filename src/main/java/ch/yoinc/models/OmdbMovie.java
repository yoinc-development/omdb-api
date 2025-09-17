package ch.yoinc.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OmdbMovie extends OmdbResponse {

    private String DVD;
    private String BoxOffice;
    private String Production;
    private String Website;

    public OmdbMovie(String Title, String Year, String Rated, String Released, String Genre, String Actors, String Plot, String Language, String Country, String Awards, String Poster, List<OmdbRating> Ratings, String Metascore, String imdbRating, String imdbVotes, String imdbID, String Type, String Response, String Runtime, String Director, String Writer, String DVD, String BoxOffice, String Production, String Website) {
        super(Title, Year, Rated, Released, Runtime, Genre, Director, Writer, Actors, Plot, Language, Country, Awards, Poster, Ratings, Metascore, imdbRating, imdbVotes, imdbID, Type, Response);
        this.DVD = DVD;
        this.BoxOffice = BoxOffice;
        this.Production = Production;
        this.Website = Website;
    }
}
