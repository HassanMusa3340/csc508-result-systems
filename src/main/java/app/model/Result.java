package app.model;

/** A raw score a student obtained in a course in a semester. Grades are derived, not stored. */
public class Result {
    private final int studentId;
    private final String courseCode;
    private final int semesterId;
    private final double score;

    public Result(int studentId, String courseCode, int semesterId, double score) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.semesterId = semesterId;
        this.score = score;
    }

    public int getStudentId() { return studentId; }
    public String getCourseCode() { return courseCode; }
    public int getSemesterId() { return semesterId; }
    public double getScore() { return score; }
}
