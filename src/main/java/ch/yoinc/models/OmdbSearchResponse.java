package ch.yoinc.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OmdbSearchResponse {

    private List<OmdbSearchItem> Search;
    private int totalResults;
    private String Response;

    public OmdbSearchResponse(List<OmdbSearchItem> search, int totalResults, String response) {
        this.Search = search;
        this.totalResults = totalResults;
        this.Response = response;
    }
}
