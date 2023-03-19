package com.cashier.app.cashierApp.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.cashier.app.cashierApp.Model.Entity.ProductKey;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class ProductKeyService {
    private static final String COLLECTION_NAME = "product_key";

    //select all
    public List<ProductKey> getProductKey() throws ExecutionException, InterruptedException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
    
        Iterable<DocumentReference> documentReference = dbFirestore.collection(COLLECTION_NAME).listDocuments();
        Iterator<DocumentReference> iterator = documentReference.iterator();
    
        List<ProductKey> productKeyList = new ArrayList<>();
        ProductKey productKey = null;
    
        while(iterator.hasNext()){
            DocumentReference documentReference1 = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference1.get();
            DocumentSnapshot document = future.get();
            productKey = document.toObject(ProductKey.class);
            productKeyList.add(productKey);
        }
    
        return productKeyList;
    }
}
