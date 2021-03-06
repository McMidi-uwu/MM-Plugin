package xyz.tehbrian.mcmidi.representation;

/**
 * Represents a pitch that can be played as a note in Minecraft.
 */
public enum Pitch {
    Fs1(0.5F, 0),
    G1(0.529732F, 1),
    Gs1(0.561231F, 2),
    A1(0.594604F, 3),
    As1(0.629961F, 4),
    B1(0.667420F, 5),
    C1(0.707107F, 6),
    Cs1(0.749154F, 7),
    D1(0.793701F, 8),
    Ds1(0.840896F, 9),
    E1(0.890899F, 10),
    F1(0.943874F, 11),
    Fs2(1F, 12),
    G2(1.059463F, 13),
    Gs2(1.122462F, 14),
    A2(1.189207F, 15),
    As2(1.259921F, 16),
    B2(1.334840F, 17),
    C2(1.414214F, 18),
    Cs2(1.498307F, 19),
    D2(1.587401F, 20),
    Ds2(1.681793F, 21),
    E2(1.781797F, 22),
    F2(1.887749F, 23),
    Fs3(2F, 24);

    /**
     * Pitch represented as a float from 0.5 to 2.
     */
    private final float pitchFloat;
    /**
     * The number of clicks required to get the pitch
     * on a Minecraft note block.
     */
    private final int numOfClicks;

    Pitch(final float pitchFloat, final int numOfClicks) {
        this.pitchFloat = pitchFloat;
        this.numOfClicks = numOfClicks;
    }

    /**
     * Gets the {@link Pitch} which best corresponds to the
     * MIDI note number.
     *
     * @param number the MIDI note number
     * @return the Pitch
     */
    public static Pitch fromMidiNoteNumber(final byte number) {
        switch (number) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
                return Pitch.Fs1;
            case 43:
                return Pitch.G1;
            case 44:
                return Pitch.Gs1;
            case 45:
                return Pitch.A1;
            case 46:
                return Pitch.As1;
            case 47:
                return Pitch.B1;
            case 48:
                return Pitch.C1;
            case 49:
                return Pitch.Cs1;
            case 50:
                return Pitch.D1;
            case 51:
                return Pitch.Ds1;
            case 52:
                return Pitch.E1;
            case 53:
                return Pitch.F1;
            case 54:
                return Pitch.Fs2;
            case 55:
                return Pitch.G2;
            case 56:
                return Pitch.Gs2;
            case 57:
                return Pitch.A2;
            case 58:
                return Pitch.As2;
            case 59:
                return Pitch.B2;
            case 60:
                return Pitch.C2;
            case 61:
                return Pitch.Cs2;
            case 62:
                return Pitch.D2;
            case 63:
                return Pitch.Ds2;
            case 64:
                return Pitch.E2;
            case 65:
                return Pitch.F2;
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
            case 102:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 109:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
            case 115:
            case 116:
            case 117:
            case 118:
            case 119:
            case 120:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 126:
            case 127:
                return Pitch.Fs3;
            default:
                throw new IllegalArgumentException("number must be between 0 to 127 inclusive");
        }
    }

    public float asFloat() {
        return this.pitchFloat;
    }

    public int getNumOfClicks() {
        return this.numOfClicks;
    }

    /**
     * Gets a double which, if fed into a colored particle as
     * the dx offset, will correspond to the color emitted
     * by a noteblock playing the same pitch.
     *
     * @return a double representing the hue of the pitch
     */
    public double getParticleColor() {
        return (this.numOfClicks / 24D);
    }
}
