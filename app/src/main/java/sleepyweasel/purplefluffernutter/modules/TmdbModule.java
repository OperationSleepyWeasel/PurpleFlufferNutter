package sleepyweasel.purplefluffernutter.modules;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import sleepyweasel.purplefluffernutter.rest.interceptors.TmdbApiKeyRequestInterceptor;
import sleepyweasel.purplefluffernutter.rest.tmdb.Tmdb;
import sleepyweasel.purplefluffernutter.rest.deserializers.DateDeserializer;

@Module
public class TmdbModule {
    //i am not sure how long does it last
    public static final String TMDB_APIARY_URL = "http://private-anon-975b18d78-themoviedb.apiary-mock.com/3";
    public static final String TMDB_URL = "http://api.themoviedb.org/3";

    public static final String API_KEY = "8ec7476e88d48365fd343370b9947b76";

    private String url;

    public TmdbModule(String url) {
        this.url = url;
    }

    @Provides
    Tmdb provideTmdb(RestAdapter restAdapter) {
        return restAdapter.create(Tmdb.class);
    }

    @Provides
    RestAdapter provideRestAdapter(GsonConverter gsonConverter, TmdbApiKeyRequestInterceptor interceptor) {
        return new RestAdapter.Builder()
            .setEndpoint(url)
            .setConverter(gsonConverter)
            .setRequestInterceptor(interceptor)
            .build();
    }

    @Provides
    GsonConverter provideGsonConverter(Gson gson) {
        return new GsonConverter(gson);
    }

    @Provides
    TmdbApiKeyRequestInterceptor provideTmdbApiKeyRequestInterceptor() {
        return new TmdbApiKeyRequestInterceptor(API_KEY);
    }

    @Provides
    Gson provideGson(DateDeserializer dateDeserializer) {
        return new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(Date.class, dateDeserializer)
            .create();
    }

    @Provides
    DateDeserializer provideDateDeserializer() {
        return new DateDeserializer();
    }
}
