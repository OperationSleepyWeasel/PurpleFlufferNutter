package sleepyweasel.purplefluffernutter.modules;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import dagger.Module;
import dagger.Provides;
import lombok.AllArgsConstructor;
import lombok.Getter;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import sleepyweasel.purplefluffernutter.dummy.retrofit.Tmdb;
import sleepyweasel.purplefluffernutter.dummy.retrofit.deserializers.DateDeserializer;

@Module
@AllArgsConstructor(suppressConstructorProperties = true)
public class TmdbModule {
    public static final String TMDB_APIARY_URL = "http://private-anon-975b18d78-themoviedb.apiary-mock.com/3";

    @Getter
    private String url;

    @Provides
    Tmdb provideTmdb(RestAdapter restAdapter) {
        return restAdapter.create(Tmdb.class);
    }

    @Provides
    RestAdapter provideRestAdapter(GsonConverter gsonConverter) {
        return new RestAdapter.Builder()
            .setEndpoint(url)
            .setConverter(gsonConverter)
            .build();
    }

    @Provides
    GsonConverter provideGsonConverter(Gson gson) {
        return new GsonConverter(gson);
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
