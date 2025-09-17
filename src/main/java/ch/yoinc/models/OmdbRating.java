package ch.yoinc.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class OmdbRating {
    private String Source;
    private String Value;

    public OmdbRating(String Source, String Value) {
        this.Source = Source;
        this.Value = Value;
    }
}
