package eecs2030.project.Utilities;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import eecs2030.project.Models.Score;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Haider on 7/5/2017.
 *
 * This class handles setting the HighScore Values to a RealTimeDateBase!
 */
public final class Database {

    private FirebaseDatabase database;
    private DatabaseReference databaseRef;
    private DatabaseReference databaseRefError;
    
    private static Database INSTANCE = null;

    /**
     * Initialize the connection to the Real-time Database and maintain it in an instance.
     *
     * @throws IOException when the required certificate file for the database connection is not found or failed.
     */
    private Database() throws IOException {
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

    /**
     * Initiate an instance of the database if it is not. Getter for the database instance.
     *
     * @return the instance of the database.
     */
    public static Database getInstance() {
    	if (Database.INSTANCE == null) {
            try {
                Database.INSTANCE = new Database();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    	return Database.INSTANCE;
    }

    /**
     * Push score to the database!
     *
     * @param score the score of the player
     */
    public void addScore(Score score) {
        this.databaseRef.push().setValue(score);
    }

    /**
     * Get the Reference of the main database directory
     *
     * @return the Reference of the main database directory
     */
    public DatabaseReference getDatabaseRef() {
        return this.databaseRef;
    }

    /**
     * Push occurred error to the database
     *
     * @param Error Message of the Error
     */
    public void pushError(String Error) {
        this.databaseRefError.push().setValue(Error);
    }
}
