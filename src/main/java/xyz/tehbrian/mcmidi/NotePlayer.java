package xyz.tehbrian.mcmidi;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import xyz.tehbrian.mcmidi.representation.Note;

/**
 * Plays notes.
 */
public class NotePlayer {

    /**
     * Plays a note and creates a fancy particle too.
     *
     * @param player the player to do it at
     * @param note   the note to play
     */
    public void playNote(final Player player, final Note note) {
        player.getWorld().playSound(player.getLocation(),
                note.getInstrument().asSound(),
                SoundCategory.MASTER,
                note.getVelocity(),
                note.getPitch().asFloat());

        Location location = player.getLocation();
        player.getWorld().spawnParticle(Particle.NOTE,
                location.getX(),
                location.getY() + 2.25,
                location.getZ(),
                0,
                note.getPitch().getParticleColor(),
                0,
                0,
                1);
    }
}
