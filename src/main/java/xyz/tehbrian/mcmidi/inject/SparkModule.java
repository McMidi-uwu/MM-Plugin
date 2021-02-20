package xyz.tehbrian.mcmidi.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.checkerframework.checker.nullness.qual.NonNull;
import xyz.tehbrian.mcmidi.Config;
import xyz.tehbrian.mcmidi.NotePlayer;
import xyz.tehbrian.mcmidi.SparkController;

/**
 * Guice module which provides bindings for {@link xyz.tehbrian.mcmidi.SparkController}.
 */
public class SparkModule extends AbstractModule {

    /**
     * Provides {@link xyz.tehbrian.mcmidi.SparkController}.
     */
    @Provides
    @Singleton
    public SparkController provideSparkController(
            final @NonNull Config config,
            final @NonNull NotePlayer notePlayer) {
        return new SparkController(config, notePlayer);
    }
}
