package sleepyweasel.purplefluffernutter.rest.interceptors;

import org.junit.Test;

import retrofit.RequestInterceptor;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TmdbApiKeyRequestInterceptorTest {

    private static final String API_KEY_VALUE = "apiKeyValue";

    TmdbApiKeyRequestInterceptor interceptor = new TmdbApiKeyRequestInterceptor(API_KEY_VALUE);

    @Test
    public void shouldAddApiKeyToRequest() {
        RequestInterceptor.RequestFacade requestFacade = mock(RequestInterceptor.RequestFacade.class);

        interceptor.intercept(requestFacade);

        verify(requestFacade).addQueryParam(TmdbApiKeyRequestInterceptor.API_KEY_KEY, API_KEY_VALUE);
    }
}