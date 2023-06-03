package clinic;

import javax.print.Doc;
import javax.swing.tree.TreeCellRenderer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;


/**
 * Represents a clinic with patients and doctors.
 *
 */
public class Clinic {

    /**
     * Add a new clinic patient.
     *
     * @param first first name of the patient
     * @param last last name of the patient
     * @param ssn SSN number of the patient
     */

    TreeMap<String,Patient> patients = new TreeMap<>();

    public void addPatient(String first, String last, String ssn) {
        Patient patient = new Patient(first,last,ssn);
        patients.put(ssn,patient);
    }


    /**
     * Retrieves a patient information
     *
     * @param ssn SSN of the patient
     * @return the object representing the patient
     * @throws NoSuchPatient in case of no patient with matching SSN
     */
    public String getPatient(String ssn) throws NoSuchPatient {
        if(!patients.containsKey(ssn)) throw new NoSuchPatient();
        return patients.get(ssn).toString();
    }

    /**
     * Add a new doctor working at the clinic
     *
     * @param first first name of the doctor
     * @param last last name of the doctor
     * @param ssn SSN number of the doctor
     * @param docID unique ID of the doctor
     * @param specialization doctor's specialization
     */

    TreeMap<Integer,Doctor> doctors = new TreeMap<>();

    public void addDoctor(String first, String last, String ssn, int docID, String specialization) {
        Doctor doctor = new Doctor(first,last,ssn,docID,specialization);
        doctors.put(docID,doctor);
    }

    /**
     * Retrieves information about a doctor
     *
     * @param docID ID of the doctor
     * @return object with information about the doctor
     * @throws NoSuchDoctor in case no doctor exists with a matching ID
     */
    public String getDoctor(int docID) throws NoSuchDoctor {
        if(!doctors.containsKey(docID)) throw new NoSuchDoctor(docID);
        return doctors.get(docID).toString();
    }

    /**
     * Assign a given doctor to a patient
     *
     * @param ssn SSN of the patient
     * @param docID ID of the doctor
     * @throws NoSuchPatient in case of not patient with matching SSN
     * @throws NoSuchDoctor in case no doctor exists with a matching ID
     */
    public void assignPatientToDoctor(String ssn, int docID) throws NoSuchPatient, NoSuchDoctor {
        if(!patients.containsKey(ssn)) throw new NoSuchPatient();
        if(!doctors.containsKey(docID)) throw new NoSuchDoctor();

        Patient patient = patients.get(ssn);
        Doctor doctor = doctors.get(docID);
        patient.setDoctor(doctor);

        // forse devo considerare il caso in cui un paziente cambia dottore e quindi questo deve essere eliminato dalla lista del dottore precedente
        doctor.addPatient(patient);

    }

    /**
     * Retrieves the id of the doctor assigned to a given patient.
     *
     * @param ssn SSN of the patient
     * @return id of the doctor
     * @throws NoSuchPatient in case of not patient with matching SSN
     * @throws NoSuchDoctor in case no doctor has been assigned to the patient
     */
    public int getAssignedDoctor(String ssn) throws NoSuchPatient, NoSuchDoctor {

        if(!patients.containsKey(ssn)) throw new NoSuchPatient();
        Patient patient = patients.get(ssn);
        if(!patient.hasDoctor()) throw new NoSuchDoctor();

        return patient.getDoctorID();


    }

    /**
     * Retrieves the patients assigned to a doctor
     *
     * @param id ID of the doctor
     * @return collection of patient SSNs
     * @throws NoSuchDoctor in case the {@code id} does not match any doctor
     */
    public Collection<String> getAssignedPatients(int id) throws NoSuchDoctor {
        if(!doctors.containsKey(id)) throw new NoSuchDoctor();
        return doctors.get(id).getPatientsSSN();
    }

    /**
     * Loads data about doctors and patients from the given stream.
     * <p>
     * The text file is organized by rows, each row contains info about
     * either a patient or a doctor.</p>
     * <p>
     * Rows containing a patient's info begin with letter {@code "P"} followed by first name,
     * last name, and SSN. Rows containing doctor's info start with letter {@code "M"},
     * followed by badge ID, first name, last name, SSN, and speciality.<br>
     * The elements on a line are separated by the {@code ';'} character possibly
     * surrounded by spaces that should be ignored.</p>
     * <p>
     * In case of error in the data present on a given row, the method should be able
     * to ignore the row and skip to the next one.<br>

     *
     * @param reader reader linked to the file to be read
     * @throws IOException in case of IO error
     */
    public int loadData(Reader reader) throws IOException {

        int n_lines = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim(); // Remove leading/trailing spaces

                if (line.startsWith("P")) {
                    // Process patient info
                    String[] patientInfo = line.substring(1).split(";"); // Remove leading 'P' and split by ';'
                    if (patientInfo.length >= 3) {
                        String firstName = patientInfo[1].trim();
                        String lastName = patientInfo[2].trim();
                        String ssn = patientInfo[3].trim();
                        // Process patient information (addPatient method)
                        addPatient(firstName, lastName, ssn);
                        n_lines++;
                    }
                } else if (line.startsWith("M")) {
                    // Process doctor info
                    String[] doctorInfo = line.substring(1).split(";"); // Remove leading 'M' and split by ';'
                    if (doctorInfo.length >= 5) {
                        String badgeID = doctorInfo[1].trim();
                        String firstName = doctorInfo[2].trim();
                        String lastName = doctorInfo[3].trim();
                        String ssn = doctorInfo[4].trim();
                        String specialization = doctorInfo[5].trim();

                        // Process doctor information (addDoctor method)
                        addDoctor(firstName, lastName, ssn, Integer.parseInt(badgeID), specialization);
                        n_lines++;
                    }
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            throw e; // Propagate the IOException to the caller
        }

        return n_lines;
    }


    /**
     * Loads data about doctors and patients from the given stream.
     * <p>
     * The text file is organized by rows, each row contains info about
     * either a patient or a doctor.</p>
     * <p>
     * Rows containing a patient's info begin with letter {@code "P"} followed by first name,
     * last name, and SSN. Rows containing doctor's info start with letter {@code "M"},
     * followed by badge ID, first name, last name, SSN, and speciality.<br>
     * The elements on a line are separated by the {@code ';'} character possibly
     * surrounded by spaces that should be ignored.</p>
     * <p>
     * In case of error in the data present on a given row, the method calls the
     * {@link ErrorListener#offending} method passing the line itself,
     * ignores the row, and skip to the next one.<br>
     *
     * @param reader reader linked to the file to be read
     * @param listener listener used for wrong line notifications
     * @throws IOException in case of IO error
     */
    public int loadData(Reader reader, ErrorListener listener) throws IOException {

        return -1;


    }




    /**
     * Retrieves the collection of doctors that have no patient at all.
     * The doctors are returned sorted in alphabetical order
     *
     * @return the collection of doctors' ids
     */
    public Collection<Integer> idleDoctors(){
       TreeMap<String,Integer> map = new TreeMap<>();

       for(Doctor doctor:doctors.values()){

           String key = doctor.getLast() + doctor.getFirst() + doctor.getDocID();
           map.put(key,doctor.getDocID());

       }

       return map.values();

    }

    /**
     * Retrieves the collection of doctors having a number of patients larger than the average.
     *
     * @return  the collection of doctors' ids
     */
    public Collection<Integer> busyDoctors(){

        LinkedList<Integer> list = new LinkedList<>();

        float avg = 0;
        int count = 0;

        for(Doctor d:doctors.values()){
            avg += d.getNumPatients();
            count ++;
        }

        avg = avg / count;

        for(Doctor d:doctors.values()){
            if(d.getNumPatients()>avg) list.add(d.getDocID());
        }

        return list;

    }

    /**
     * Retrieves the information about doctors and relative number of assigned patients.
     * <p>
     * The method returns list of strings formatted as "{@code ### : ID SURNAME NAME}" where {@code ###}
     * represent the number of patients (printed on three characters).
     * <p>
     * The list is sorted by decreasing number of patients.
     *
     * @return the collection of strings with information about doctors and patients count
     */
    public Collection<String> doctorsByNumPatients(){

        TreeMap<Integer,String> map = new TreeMap<>();

        for(Doctor doctor:doctors.values()){
            String formattedNumber = String.format("%03d", doctor.getNumPatients());
            String s = formattedNumber + " : " + doctor.getDocID() + " " + doctor.getLast() + " " + doctor.getFirst();
            map.put(doctor.getNumPatients(),s);
        }

        List<String> lista = new ArrayList<>();

        for(String s:map.values()){
            lista.add(0,s); // aggiungo in testa per fare reverse order
        }

        return lista;

    }

    /**
     * Retrieves the number of patients per (their doctor's)  speciality
     * <p>
     * The information is a collections of strings structured as {@code ### - SPECIALITY}
     * where {@code SPECIALITY} is the name of the speciality and
     * {@code ###} is the number of patients cured by doctors with such speciality (printed on three characters).
     * <p>
     * The elements are sorted first by decreasing count and then by alphabetic speciality.
     *
     * @return the collection of strings with speciality and patient count information.
     */
    public Collection<String> countPatientsPerSpecialization() {

        TreeMap<String, Integer> specializations = new TreeMap<>();

        for (Doctor doctor : doctors.values()) {

            if (specializations.containsKey(doctor.getSpecialization())) {
                int k = specializations.get(doctor.getSpecialization());
                k += doctor.getNumPatients();
                specializations.put(doctor.getSpecialization(), k);
            } else {
                specializations.put(doctor.getSpecialization(), doctor.getNumPatients());
            }

        }


        TreeMap<String, String> map = new TreeMap();

        for (String s : specializations.keySet()) {

            if (specializations.get(s) != 0) {
                String formattedNumber = String.format("%03d", specializations.get(s));
                String str = formattedNumber + " - " + s;
                map.put(str, s);
            }

        }

        List<String> lista = new ArrayList<>();

        for(String s:map.keySet()){
            lista.add(0,s); // aggiungo in testa per fare reverse order
        }
        
        return lista.stream()
                .sorted(Comparator
                        .comparing((String s) -> Integer.parseInt(s.split(" - ")[0]))
                        .thenComparing(s -> s.split(" - ")[1])
                )
                .toList();

    }
}
