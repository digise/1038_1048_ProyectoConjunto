package com.example.a1039_1048_proyectoconjunto.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;

public class ConexionFirebase {

    public Firestore iniciarFirebase(){


        FirebaseOptions.Builder options = null;
        try {
            options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(getClass().getResourceAsStream("proyectoparadis-64cf2-firebase-adminsdk-bd141-e1431958be.json")));
            FirebaseApp.initializeApp(options.build());
        } catch (IOException e) {
            e.printStackTrace();
        }


        return FirestoreClient.getFirestore();


    }


}
