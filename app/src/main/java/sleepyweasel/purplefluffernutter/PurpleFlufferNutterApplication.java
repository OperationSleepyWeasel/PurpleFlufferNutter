package sleepyweasel.purplefluffernutter;

import android.app.Application;

import sleepyweasel.purplefluffernutter.components.DaggerTmdbComponent;
import sleepyweasel.purplefluffernutter.components.TmdbComponent;
import sleepyweasel.purplefluffernutter.modules.TmdbModule;

public class PurpleFlufferNutterApplication extends Application {
    private TmdbComponent tmdbComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        tmdbComponent = DaggerTmdbComponent.builder()
            .tmdbModule(new TmdbModule(TmdbModule.TMDB_APIARY_URL))
            .build();
    }

    public TmdbComponent getTmdbComponent() {
        return tmdbComponent;
    }
}
