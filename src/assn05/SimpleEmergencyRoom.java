package assn05;

import java.util.ArrayList;
import java.util.List;

public class SimpleEmergencyRoom {
    private List<Patient> patients;

    public SimpleEmergencyRoom() {
        patients = new ArrayList<>();
    }

    // TODO (Task 1): dequeue
    public Patient dequeue() {
        int prio = 0;
        int prioIndex = 0;
        for (int i = 0; i< patients.size(); i++){
            Patient point = patients.get(i);
            if (point.getPriority().compareTo(prio) > 0){
                prio = (int) point.getPriority();
                prioIndex = i;
            }
        }
        Patient patient = patients.get(prioIndex);
        patients.remove(prioIndex);
    	return patient;
    }

    public <V, P> void addPatient(V value, P priority) {
        Patient patient = new Patient(value, (Integer) priority);
        patients.add(patient);
    }

    public <V> void addPatient(V value) {
        Patient patient = new Patient(value);
        patients.add(patient);
    }

    public List getPatients() {
        return patients;
    }

    public int size() {
        return patients.size();
    }

}
