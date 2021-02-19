package xyz.tehbrian.mcmidi;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import org.bukkit.Bukkit;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Objects;

import static spark.Spark.*;

public final class McMidi extends JavaPlugin {

    private static final int PORT = 30020;

    @Override
    public void onEnable() {
        port(PORT);

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

            playNote(player, noteRequest.getNote());
            res.status(200);
            return "Successfully played note for player " + player.getName();
        });

        post("/mcmidi/note-off", (req, res) -> "Nothing currently happening for note-off..");
    }

    @Override
    public void onDisable() {
        stop();
    }

    private void playNote(Player player, Note note) {
        player.getWorld().playSound(player.getLocation(),
                note.getInstrument().asSound(),
                SoundCategory.MASTER,
                note.getVelocity(),
                note.getPitch().asFloat());
    }
}
