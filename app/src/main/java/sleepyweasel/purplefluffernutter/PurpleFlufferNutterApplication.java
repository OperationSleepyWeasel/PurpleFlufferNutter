package sleepyweasel.purplefluffernutter;

import android.app.Application;

import javax.inject.Inject;

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
    }

    public TmdbComponent getTmdbComponent() {
        return tmdbComponent;
    }

    public Configuration getConfiguration() {
        if (configuration == null) {
            configuration = tmdb.getConfiguration();
        }
        return configuration;
    }
}
