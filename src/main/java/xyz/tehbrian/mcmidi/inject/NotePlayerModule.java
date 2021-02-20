package xyz.tehbrian.mcmidi.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import xyz.tehbrian.mcmidi.NotePlayer;

/**
 * Guice module which provides bindings for {@link xyz.tehbrian.mcmidi.NotePlayer}.
 */
public class NotePlayerModule extends AbstractModule {

    /**
     * Provides {@link xyz.tehbrian.mcmidi.NotePlayer}.
     */
    @Provides
    @Singleton
    public NotePlayer provideNotePlayer() {
        return new NotePlayer();
    }
}
