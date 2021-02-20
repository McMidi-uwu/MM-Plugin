package xyz.tehbrian.mcmidi.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.bukkit.configuration.file.FileConfiguration;
import org.checkerframework.checker.nullness.qual.NonNull;
import xyz.tehbrian.mcmidi.Config;

import java.util.logging.Logger;

/**
 * Guice module which provides bindings for {@link xyz.tehbrian.mcmidi.Config}.
 */
public class ConfigModule extends AbstractModule {

    /**
     * Provides {@link xyz.tehbrian.mcmidi.Config}.
     */
    @Provides
    @Singleton
    public Config provideConfig(
            final @NonNull FileConfiguration fileConfiguration,
            final @NonNull Logger logger) {
        return new Config(mcMidi, fileConfiguration, logger);
    }
}
