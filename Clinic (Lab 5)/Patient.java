package clinic;

public class Patient {

    private String first;
    private String last;
    private String ssn;

    private Doctor doctor;
    private boolean hasDoctor;

    public Patient(String first, String last, String ssn) {
        this.first = first;
        this.last = last;
        this.ssn = ssn;
        hasDoctor = false;
    }

    @Override
    public String toString() {
        return "" + last + " " + first + " " + "(" + ssn + ")" + "";
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        hasDoctor = true;
    }

    public String getSsn() {
        return ssn;
    }

    public boolean hasDoctor() {
        return hasDoctor;
    }

    public int getDoctorID() {
        return doctor.getDocID();
    }
}
