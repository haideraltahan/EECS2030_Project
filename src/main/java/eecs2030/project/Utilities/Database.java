package eecs2030.project.Utilities;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.*;
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
    private DatabaseReference databaseRef;
    private DatabaseReference databaseRefError;

    public Database() throws Exception {
        File file = new File(this.getClass().getResource(Constants.FIREBASE_FILE_PATH).getPath());
        FileInputStream serviceAccount = new FileInputStream(file);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
                .setDatabaseUrl(Constants.FIREBASE_LINK)
                .build();

        FirebaseApp.initializeApp(options);

        this.database = FirebaseDatabase.getInstance();
        this.databaseRef = this.database.getReference(Constants.DATABASE_MAIN_OBJECT);
        this.databaseRefError = this.database.getReference(Constants.DATABASE_ERROR_OBJECT);
    }

    public void addScore(Score score){
        this.databaseRef.push().setValue(score);
    }

    public DatabaseReference getDatabaseRef() {
        return this.databaseRef;
    }

    public void pushError(String Error) {
        this.databaseRefError.push().setValue(Error);
    }
}
