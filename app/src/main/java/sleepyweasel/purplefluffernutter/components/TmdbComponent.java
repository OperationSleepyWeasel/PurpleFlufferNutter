package sleepyweasel.purplefluffernutter.components;

import dagger.Component;
import sleepyweasel.purplefluffernutter.AddMovieActivity;
import sleepyweasel.purplefluffernutter.PurpleFlufferNutterApplication;
import sleepyweasel.purplefluffernutter.dummy.TmdbApiaryClient;
import sleepyweasel.purplefluffernutter.modules.TmdbModule;
import sleepyweasel.purplefluffernutter.rest.tmdb.Tmdb;

@Component(modules = TmdbModule.class)
public interface TmdbComponent {
    void inject(TmdbApiaryClient client);
    void inject(AddMovieActivity activity);
    void inject(PurpleFlufferNutterApplication application);

    //this provide is for tmdb apiary test, i tried generating new component in tests but it seems not to work
    //https://bitbucket.org/hvisser/android-apt/issue/22/dagger-robolectric-gradle-apt-source-not
    Tmdb provideTmdb();
}
