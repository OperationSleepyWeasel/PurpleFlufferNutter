package sleepyweasel.purplefluffernutter.components;

import dagger.Component;
import sleepyweasel.purplefluffernutter.AddMovieActivity;
import sleepyweasel.purplefluffernutter.dummy.retrofit.TmdbApiaryClient;
import sleepyweasel.purplefluffernutter.modules.TmdbModule;

@Component(modules = TmdbModule.class)
public interface TmdbComponent {
    void inject(TmdbApiaryClient client);
    void inject(AddMovieActivity activity);
}
