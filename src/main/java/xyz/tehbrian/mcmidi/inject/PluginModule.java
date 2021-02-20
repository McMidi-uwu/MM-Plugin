package xyz.tehbrian.mcmidi.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;
import xyz.tehbrian.mcmidi.McMidi;

/**
 * Guice module which provides bindings for instances originating from the plugin's main class.
 */
public final class PluginModule extends AbstractModule {

    /**
     * McMidi reference.
     */
    private final McMidi mcMidi;

    /**
     * Constructs {@link PluginModule}.
     *
     * @param mcMidi McMidi reference
     */
    public PluginModule(final @NonNull McMidi mcMidi) {
        this.mcMidi = mcMidi;
    }

    /**
     * Provides the plugin's {@link FileConfiguration}.
     *
     * @return the {@link FileConfiguration}
     */
    @Provides
    public FileConfiguration provideFileConfiguration() {
        return this.mcMidi.getConfig();
    }

    @Override
    protected void configure() {
        this.bind(McMidi.class).toInstance(this.mcMidi);
        this.bind(JavaPlugin.class).toInstance(this.mcMidi);
    }
}
