package ch.yoinc.client;

public class OmdbClientBuilder {

    private String apiKey;

    public OmdbClientBuilder apiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public OmdbClientImpl build() {
        return new OmdbClientImpl(apiKey);
    }
}