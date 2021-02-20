package xyz.tehbrian.mcmidi;

import org.bukkit.configuration.file.FileConfiguration;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.logging.Logger;

/**
 * Grabs and holds values from a {@link org.bukkit.configuration.file.FileConfiguration}
 * for easy access.
 */
public class Config {

    private static final int PORT_MIN = 0;
    private static final int PORT_MAX = 65535;

    /**
     * McMidi reference.
     */
    private final McMidi mcMidi;
    /**
     * FileConfiguration reference.
     */
    private final FileConfiguration config;
    /**
     * Logger reference.
     */
    private final Logger logger;

    private int port;
    private boolean isSecureEnabled;
    private String secureKeystoreFile;
    private String secureKeystorePassword;

    /**
     * Constructs {@link Config}.
     *
     * @param config the {@link FileConfiguration} to use for values
     * @param logger the {@link Logger} to yell at when values are absent
     */
    public Config(
            final @NonNull McMidi mcMidi,
            final @NonNull FileConfiguration config,
            final @NonNull Logger logger) {
        this.mcMidi = mcMidi;
        this.config = config;
        this.logger = logger;
    }

    /**
     * Loads and validates values from {@link #config}. Whines to
     * {@link #logger} and shuts down if they're invalid.
     */
    public void loadValues() {
        this.port = this.config.getInt("port", 61672);
        this.isSecureEnabled = this.config.getBoolean("secure.enabled", false);
        this.secureKeystoreFile = this.config.getString("secure.keystore_file");
        this.secureKeystorePassword = this.config.getString("secure.keystore_password");

        if (this.port < PORT_MIN || this.port > PORT_MAX) {
            this.whine("Port must be between 65535.");
        }

        if (this.isSecureEnabled) {
            if (this.secureKeystoreFile == null) {
                this.whine("Keystore file is null.");
            }
            if (this.secureKeystorePassword == null) {
                this.whine("Keystore password is null.");
            }
        }
    }

    /**
     * Logs a severe message and shuts down the server.
     *
     * @param message the message to log
     */
    private void whine(final String message) {
        this.logger.severe(message + " You can change this value in McMidi's config. Shutting down server..");
        this.mcMidi.getServer().shutdown();
    }

    /**
     * What port the McMidi API will use.
     *
     * @return an integer between 0-65535 inclusive
     */
    public int getPort() {
        return this.port;
    }

    /**
     * Whether or not the McMidi API will attempt to use SSL/HTTPS.
     *
     * @return a boolean
     */
    public boolean isSecureEnabled() {
        return this.isSecureEnabled;
    }

    /**
     * What keystore file to use, relative to the server's root directory.
     *
     * @return the filepath
     */
    public String getSecureKeystoreFile() {
        return this.secureKeystoreFile;
    }

    /**
     * What keystore password to use.
     *
     * @return the password
     */
    public String getSecureKeystorePassword() {
        return this.secureKeystorePassword;
    }
}
