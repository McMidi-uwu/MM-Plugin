package xyz.tehbrian.mcmidi.adapters;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import xyz.tehbrian.mcmidi.representation.Pitch;

/**
 * Moshi adapter for {@link Pitch}.
 */
public final class PitchAdapter {

    @ToJson
    String toJson(final Pitch pitch) {
        return String.valueOf(pitch.asFloat());
    }

    @FromJson
    Pitch fromJson(final String pitch) {
        return Pitch.fromMidiNoteNumber(Byte.parseByte(pitch));
    }
}
