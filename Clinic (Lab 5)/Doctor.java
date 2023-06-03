package clinic;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class Doctor {

    private String first;
    private String last;
    private String ssn;
    private int docID;
    private String specialization;

    private TreeMap<String,Patient> patients;

    public Doctor(String first, String last, String ssn, int docID, String specialization) {
        this.first = first;
        this.last = last;
        this.ssn = ssn;
        this.docID = docID;
        this.specialization = specialization;
        patients = new TreeMap<>();
    }

    @Override
    public String toString() {
        return "" + last + " " + first + "(" + ssn + ")" + " " + "[" + docID + "]:" + " " + specialization;
    }

    public void addPatient(Patient patient){
        patients.put(patient.getSsn(),patient);
    }

    public int getDocID() {
        return docID;
    }

    public Set<String> getPatientsSSN() {
        return patients.keySet();
    }

    public int getNumPatients(){
        return patients.size();
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getSpecialization() {
        return specialization;
    }
}
