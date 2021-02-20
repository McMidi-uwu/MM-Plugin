package xyz.tehbrian.mcmidi.representation;

/**
 * Represents a note that can be played.
 */
public final class Note {

    public final Instrument instrument;
    public final Pitch pitch;
    public final float velocity;

    public Note(final Instrument instrument, final Pitch pitch, final float velocity) {
        this.instrument = instrument;
        this.pitch = pitch;
        this.velocity = velocity;
    }

    public Instrument getInstrument() {
        return this.instrument;
    }

    public Pitch getPitch() {
        return this.pitch;
    }

    public float getVelocity() {
        return this.velocity;
    }
}
