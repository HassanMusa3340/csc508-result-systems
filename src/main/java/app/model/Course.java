package app.model;

public class Course {
    private final String code;
    private final String title;
    private final int creditUnits;

    public Course(String code, String title, int creditUnits) {
        this.code = code;
        this.title = title;
        this.creditUnits = creditUnits;
    }

    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCreditUnits() { return creditUnits; }

    @Override public String toString() { return code + " - " + title; }
}
