package eecs2030.project.Utilities;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.*;
import eecs2030.project.Models.Score;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

/**
 * Created by Haider on 7/5/2017.
 *
 * This class handles setting the HighScore Values to a RealTimeDateBase!
 */
public final class Database{

    private FirebaseDatabase database;
    private DatabaseReference databaseRef;

    public Database() throws Exception {
        File file = new File("src\\main\\resources\\service-account.json");
        FileInputStream serviceAccount = new FileInputStream(file);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
                .setDatabaseUrl("https://snakegame-2a153.firebaseio.com/")
                .build();

        FirebaseApp.initializeApp(options);

        this.database = FirebaseDatabase.getInstance();
        this.databaseRef = this.database.getReference("Scores");

        this.databaseRef.orderByChild("points").limitToFirst(10).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                Score.updateInstance(dataSnapshot.getKey() , Integer.parseInt(dataSnapshot.getValue().toString()));
            }

            @Override
            public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
                Score.updateInstance(snapshot.getKey() , Integer.parseInt(snapshot.getValue().toString()));
            }

            @Override
            public void onChildRemoved(DataSnapshot snapshot) {
                Score.removeInstance(snapshot.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot snapshot, String previousChildName) {

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    public void updateScore(String name, int points){
        Score.updateInstance(name, points);
        this.databaseRef.updateChildren(new HashMap<>(Score.getInstances()));
    }

    public DatabaseReference getDatabaseRef() {
        return this.databaseRef;
    }
}
