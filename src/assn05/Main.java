package assn05;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        testP1();
        testP2();
        //  testP3();
        testP4();
    }

    // test Part 1
    public static void testP1(){
        SimpleEmergencyRoom abc = new SimpleEmergencyRoom();
        abc.addPatient(7, 9);
        abc.addPatient(2, 3);
        abc.addPatient(10, 5);
        System.out.println(abc.dequeue().getValue());
        System.out.println(abc.dequeue().getValue());
        System.out.println(abc.dequeue().getValue());



        /*
        Part 1 - Write some tests to convince yourself that your code for Part 1 is working
         */
    }

    // test Part 2
    public static void testP2(){
       /*
        Part 2 - Write some tests to convince yourself that your code for Part 2 is working
         */
        System.out.println("Test2");
        MaxBinHeapER ewa = new MaxBinHeapER();
        ewa.enqueue(4, 1234);


        ewa.enqueue(1,132);


        ewa.enqueue(9,645);


        ewa.enqueue(2,7);
        ewa.enqueue(10,3245);
        ewa.enqueue(5,12);
        System.out.println();
//        for (int i = 0; i<ewa.getAsArray().length; i++){
//            System.out.println(ewa.getAsArray()[i].getPriority());
//        }
//        System.out.println(ewa.getMax());

//        ewa.updatePriority(4, 3);
        for (int i = 0; i<ewa.getAsArray().length; i++){
            System.out.println(ewa.getAsArray()[i].getPriority());
        }
        System.out.println();
        ewa.dequeue();
        for (int i = 0; i<ewa.getAsArray().length; i++){
            System.out.println(ewa.getAsArray()[i].getPriority());
        }
        System.out.println();
        ewa.dequeue();
        for (int i = 0; i<ewa.getAsArray().length; i++){
            System.out.println(ewa.getAsArray()[i].getPriority());
        }
        System.out.println();
        ewa.dequeue();
        for (int i = 0; i<ewa.getAsArray().length; i++){
            System.out.println(ewa.getAsArray()[i].getPriority());
        }
        System.out.println();


    }

    /*
    Part 3
     */
    public static void testP3(){
        MaxBinHeapER transfer = new MaxBinHeapER(makePatients());
        Prioritized[] arr = transfer.getAsArray();
        for(int i = 0; i < transfer.size(); i++) {
            System.out.println("Value: " + arr[i].getValue()
                    + ", Priority: " + arr[i].getPriority());
        }
    }

    /*
    Part 4
     */
    public static void testP4() {
               /*
        Part 4 - Write some tests to convince yourself that your code for Part 4 is working
         */

    }

    public static void fillER(MaxBinHeapER complexER) {
        for(int i = 0; i < 100000; i++) {
            complexER.enqueue(i);
        }
    }
    public static void fillER(SimpleEmergencyRoom simpleER) {
        for(int i = 0; i < 100000; i++) {
            simpleER.addPatient(i);
        }
    }

    public static Patient[] makePatients() {
        Patient[] patients = new Patient[10];
        for(int i = 0; i < 10; i++) {
            patients[i] = new Patient(i);
        }
        return patients;
    }

    public static double[] compareRuntimes() {
        // Array which you will populate as part of Part 4
        double[] results = new double[4];

        SimpleEmergencyRoom simplePQ = new SimpleEmergencyRoom();
        fillER(simplePQ);

        // Code for (Task 4.1) Here
        long beforeS = System.nanoTime();
        for (int i = 0; i< simplePQ.size(); i++){
            simplePQ.dequeue();
        }
        long afterS = System.nanoTime();
        results[0] = afterS-beforeS;
        results[1] = (double) (afterS - beforeS) /100000;

        MaxBinHeapER binHeap = new MaxBinHeapER();
        fillER(binHeap);

        // Code for (Task 4.2) Here
        long beforeB = System.nanoTime();
        for (int i = 0; i < binHeap.size(); i++){
            binHeap.dequeue();
        }
        long afterB = System.nanoTime();
        results[2] = afterB-beforeB;
        results[3] = (double) (afterB - beforeB) /100000;
        return results;
    }

}
