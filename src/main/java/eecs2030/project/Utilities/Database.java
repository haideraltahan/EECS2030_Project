package eecs2030.project.Utilities;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import eecs2030.project.Models.Score;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Haider on 7/5/2017.
 *
 * This class handles setting the HighScore Values to a RealTimeDateBase!
 */
public final class Database{

    private FirebaseDatabase database;
    private DatabaseReference ref;

    public Database() throws Exception {
        File file = new File("src\\main\\resources\\service-account.json");
        FileInputStream serviceAccount = new FileInputStream(file);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
                .setDatabaseUrl("https://snakegame-2a153.firebaseio.com/")
                .build();

        FirebaseApp.initializeApp(options);

        this.database = FirebaseDatabase.getInstance();
        this.ref = this.database.getReference("Scores");
    }

    public void addScore(Score score){
        this.ref.push().setValue(score);
    }
}
