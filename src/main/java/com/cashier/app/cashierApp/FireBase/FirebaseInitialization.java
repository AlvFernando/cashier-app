package com.cashier.app.cashierApp.FireBase;

import java.io.FileInputStream;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;

@Service
public class FirebaseInitialization {
    @PostConstruct
    public void initialization(){
        FileInputStream serviceAccount = null;
        
        try {
            serviceAccount = new FileInputStream("./accountService.json");
            FirebaseOptions options;
            options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build();
            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
        
    }
}