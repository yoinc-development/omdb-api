package ch.yoinc.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OmdbSeries extends OmdbResponse {

    private String totalSeasons;

    public OmdbSeries(String Title, String Year, String Rated, String Released, String Runtime, String Genre, String Director, String Writer, String Actors, String Plot, String Language, String Country, String Awards, String Poster, List<OmdbRating> Ratings, String Metascore, String imdbRating, String imdbVotes, String imdbID, String Type, String Response, String totalSeasons) {
        super(Title, Year, Rated, Released, Runtime, Genre, Director, Writer, Actors, Plot, Language, Country, Awards, Poster, Ratings, Metascore, imdbRating, imdbVotes, imdbID, Type, Response);
        this.totalSeasons = totalSeasons;
    }

}
