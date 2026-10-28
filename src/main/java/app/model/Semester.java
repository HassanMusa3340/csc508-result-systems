package app.model;

public class Semester {
    private final int id;
    private final String session;
    private final String term;

    public Semester(int id, String session, String term) {
        this.id = id;
        this.session = session;
        this.term = term;
    }

    public int getId() { return id; }
    public String getSession() { return session; }
    public String getTerm() { return term; }

    @Override public String toString() { return session + " " + term + " Semester"; }
}
