package ch.yoinc.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OmdbSearchItem {
    private String Title;
    private String Year;
    private String imdbID;
    private String Type;
    private String Poster;

    public OmdbSearchItem(String title, String year, String imdbID, String type, String poster) {
        this.Title = title;
        this.Year = year;
        this.imdbID = imdbID;
        this.Type = type;
        this.Poster = poster;
    }
}
