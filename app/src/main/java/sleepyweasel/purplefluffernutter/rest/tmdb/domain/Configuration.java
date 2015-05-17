package sleepyweasel.purplefluffernutter.rest.tmdb.domain;

import java.util.List;

public class Configuration {
    private ImagesConfiguration images;

    public ImagesConfiguration getImages() {
        return images;
    }

    public void setImages(ImagesConfiguration images) {
        this.images = images;
    }

    public static class ImagesConfiguration {
        private String baseUrl;
        private List<String> posterSizes;

        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public List<String> getPosterSizes() {
            return posterSizes;
        }

        public void setPosterSizes(List<String> posterSizes) {
            this.posterSizes = posterSizes;
        }
    }
}
