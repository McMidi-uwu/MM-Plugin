package xyz.tehbrian.mcmidi;

public class NoteRequest {

    public final String playerName;
    public final Note note;

    public NoteRequest(final String playerName, final Note note) {
        this.playerName = playerName;
        this.note = note;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Note getNote() {
        return note;
    }
}
