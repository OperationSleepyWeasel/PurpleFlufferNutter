package sleepyweasel.purplefluffernutter.rest.interceptors;

import retrofit.RequestInterceptor;

public class TmdbApiKeyRequestInterceptor implements RequestInterceptor {

    public static final String API_KEY_KEY = "api_key";
    private String apiKeyValue;

    public TmdbApiKeyRequestInterceptor(String apiKeyValue) {
        super();
        this.apiKeyValue = apiKeyValue;
    }

    @Override
    public void intercept(RequestFacade request) {
        request.addQueryParam(API_KEY_KEY, apiKeyValue);

    }
}
