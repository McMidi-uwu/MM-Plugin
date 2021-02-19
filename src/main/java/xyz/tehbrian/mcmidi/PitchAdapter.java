package xyz.tehbrian.mcmidi;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

public class PitchAdapter {

    @ToJson
    String toJson(Pitch pitch) {
        return String.valueOf(pitch.asFloat());
    }

    @FromJson
    Pitch fromJson(String pitch) {
        return Pitch.fromMidiNoteNumber(Byte.parseByte(pitch));
    }
}
