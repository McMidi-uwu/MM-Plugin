package xyz.tehbrian.mcmidi;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import xyz.tehbrian.mcmidi.inject.ConfigModule;
import xyz.tehbrian.mcmidi.inject.PluginModule;
import xyz.tehbrian.mcmidi.inject.SparkModule;

import java.util.Objects;

/**
 * The main plugin class for McMidi.
 */
public final class McMidi extends JavaPlugin {

    /**
     * SparkController reference.
     */
    // thanks brocc <3
    private @MonotonicNonNull SparkController sparkController = null;

    @Override
    public void onEnable() {
        Injector injector = Guice.createInjector(
                new PluginModule(this),
                new ConfigModule(),
                new SparkModule()
        );

        this.sparkController = injector.getInstance(SparkController.class);
        this.sparkController.start();
    }

    @Override
    public void onDisable() {
        Objects.requireNonNull(this.sparkController);
        this.sparkController.stop();
    }
}
