package com.example.privatecliniclb1;

import com.example.privatecliniclb1.ds.Patient;

import java.io.*;

public class RwUtils {

    public static void writeTofilePatient(String fileName, Patient patient){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(patient);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Patient loadPatientFromFile(String fileName){
        Patient patient = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            patient = (Patient) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return patient;
    }
}
