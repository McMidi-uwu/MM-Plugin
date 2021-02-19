package xyz.tehbrian.mcmidi;

public class Note {

    public final Instrument instrument;
    public final Pitch pitch;
    public final float velocity;

    public Note(final Instrument instrument, final Pitch pitch, final float velocity) {
        this.instrument = instrument;
        this.pitch = pitch;
        this.velocity = velocity;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public Pitch getPitch() {
        return pitch;
    }

    public float getVelocity() {
        return velocity;
    }
}
