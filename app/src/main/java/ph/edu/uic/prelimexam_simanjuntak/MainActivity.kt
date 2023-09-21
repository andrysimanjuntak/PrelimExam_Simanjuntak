package ph.edu.uic.prelimexam_simanjuntak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var edtWelcome: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val email = intent.getStringExtra("email")

        //edtWelcome = findViewById(R.id.edt_welcome)
        //edtWelcome.text = "Welcome to Login and Register App: $email"

        val db = FirebaseFirestore.getInstance()

        // Define the Firestore collection where user data is stored
        val usersCollection = db.collection("users")

        // Perform a query to find the user document with the specified email
        usersCollection
            .whereEqualTo("email", email)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    // Assuming your user document has a field called "name"
                    val userName = document.getString("name")
                    if (userName != null) {
                        edtWelcome = findViewById(R.id.edt_welcome)
                        edtWelcome.text = "Welcome to Login and Register App: $userName"
                        // Use the user's name as needed (e.g., display it in your app)
                        //println("User Name: $userName")
                    } else {
                        Toast.makeText(this@MainActivity, "User Name not found in Firestore document.", Toast.LENGTH_SHORT).show()
                        //println("User Name not found in Firestore document.")
                    }
                }
            }
            .addOnFailureListener { exception ->
                // Handle errors, e.g., Firestore query failure
                Toast.makeText(this@MainActivity, "Error fetching user data: ${exception.message}", Toast.LENGTH_SHORT).show()
                //println("Error fetching user data: ${exception.message}")
            }
        //*/
    }
}