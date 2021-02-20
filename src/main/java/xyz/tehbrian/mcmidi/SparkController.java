package xyz.tehbrian.mcmidi;

import com.google.inject.Inject;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import spark.Spark;
import xyz.tehbrian.mcmidi.adapters.PitchAdapter;
import xyz.tehbrian.mcmidi.representation.NoteRequest;

import java.io.IOException;
import java.util.Objects;

import static spark.Spark.options;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.secure;

public final class SparkController {

    /**
     * Config reference.
     */
    private final Config config;
    /**
     * NotePlayer reference.
     */
    private final NotePlayer notePlayer;

    /**
     * Constructs {@link SparkController}.
     *
     * @param config     Config reference
     * @param notePlayer NotePlayer reference
     */
    @Inject
    public SparkController(
            final @NonNull Config config,
            final @NonNull NotePlayer notePlayer) {
        this.config = config;
        this.notePlayer = notePlayer;
    }

    public void start() {
        port(this.config.getPort());

        if (this.config.isSecureEnabled()) {
            secure(this.config.getSecureKeystoreFile(), this.config.getSecureKeystorePassword(), null, null);
        }

        Moshi moshi = new Moshi.Builder()
                .add(new PitchAdapter())
                .build();
        JsonAdapter<NoteRequest> noteRequestAdapter = moshi.adapter(NoteRequest.class);

        options("/mcmidi/note-on", (req, res) -> {
            res.header("Access-Control-Allow-Origin", req.headers("Origin"));
            res.header("Access-Control-Allow-Methods", "POST");
            res.header("Access-Control-Allow-Headers", "Content-Type, Authorization");
            res.status(200);
            return "Please send things. I'm lonely.";
        });

        post("/mcmidi/note-on", (req, res) -> {
            res.header("Access-Control-Allow-Origin", req.headers("Origin"));
            res.header("Access-Control-Allow-Methods", "POST");
            res.header("Access-Control-Allow-Headers", "Content-Type, Authorization");

            NoteRequest noteRequest;
            try {
                noteRequest = Objects.requireNonNull(noteRequestAdapter.fromJson(req.body()));
            } catch (IOException e) {
                res.status(400);
                return "Couldn't parse given data." + e.toString();
            }

            Player player = Bukkit.getPlayer(noteRequest.getPlayerName());
            if (player == null) {
                res.status(400);
                return "Player not found.";
            }

            this.notePlayer.playNote(player, noteRequest.getNote());
            res.status(200);
            return "Successfully played note for player " + player.getName();
        });

        post("/mcmidi/note-off", (req, res) -> "Nothing currently happening for note-off..");
    }

    public void stop() {
        Spark.stop();
    }
}
