package sleepyweasel.purplefluffernutter;

import android.app.Application;
import android.util.Log;

import javax.inject.Inject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import sleepyweasel.purplefluffernutter.components.DaggerTmdbComponent;
import sleepyweasel.purplefluffernutter.components.TmdbComponent;
import sleepyweasel.purplefluffernutter.modules.TmdbModule;
import sleepyweasel.purplefluffernutter.rest.tmdb.Tmdb;
import sleepyweasel.purplefluffernutter.rest.tmdb.domain.Configuration;

public class PurpleFlufferNutterApplication extends Application {
    private TmdbComponent tmdbComponent;
    private Configuration configuration;

    @Inject
    Tmdb tmdb;

    @Override
    public void onCreate() {
        super.onCreate();

        tmdbComponent = DaggerTmdbComponent.builder()
            .tmdbModule(new TmdbModule(TmdbModule.TMDB_URL))
            .build();

        getTmdbComponent().inject(this);

        fetchConfiguration();
    }

    public TmdbComponent getTmdbComponent() {
        return tmdbComponent;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    private void fetchConfiguration() {
        tmdb.getConfiguration(new Callback<Configuration>() {
            @Override
            public void success(Configuration fetchedConfiguration, Response response) {
                configuration = fetchedConfiguration;
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Error : ", "Failed to fetch configuration.");
            }
        });
    }
}
