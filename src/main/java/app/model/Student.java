package app.model;

public class Student {
    private final int id;
    private final String matricNo;
    private final String fullName;
    private final String department;

    public Student(int id, String matricNo, String fullName, String department) {
        this.id = id;
        this.matricNo = matricNo;
        this.fullName = fullName;
        this.department = department;
    }

    public int getId() { return id; }
    public String getMatricNo() { return matricNo; }
    public String getFullName() { return fullName; }
    public String getDepartment() { return department; }

    @Override public String toString() { return matricNo + " - " + fullName; }
}
