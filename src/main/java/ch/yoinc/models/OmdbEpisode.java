package ch.yoinc.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OmdbEpisode extends OmdbResponse {

    private String Season;
    private String Episode;
    private String seriesID;

    public OmdbEpisode(String Title, String Year, String Rated, String Released, String Genre, String Director, String Writer, String Actors, String Plot, String Language, String Country, String Awards, String Poster, List<OmdbRating> Ratings, String Metascore, String imdbRating, String imdbVotes, String imdbID, String Type, String Response, String Season, String Episode, String Runtime, String seriesID) {
        super(Title, Year, Rated, Released, Runtime, Genre, Director, Writer, Actors, Plot, Language, Country, Awards, Poster, Ratings, Metascore, imdbRating, imdbVotes, imdbID, Type, Response);
        this.Season = Season;
        this.Episode = Episode;
        this.seriesID = seriesID;
    }
}
