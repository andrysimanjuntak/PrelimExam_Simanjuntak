package ph.edu.uic.prelimexam_simanjuntak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var edtWelcome: TextView
    private lateinit var edtLogOut: Button
    private lateinit var btnCurrencyConversion: Button
    private lateinit var btnLemonade: Button
    private lateinit var btnTipCalculator: Button
    private lateinit var btnTemperature: Button
    private lateinit var btnHappyBirthday: Button
    private lateinit var btnBMICalculator: Button
    private lateinit var btnTaskBuddy: Button




    private lateinit var mAuth: FirebaseAuth

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

        mAuth = FirebaseAuth.getInstance()
        edtLogOut = findViewById(R.id.btnLogOut)
        btnCurrencyConversion = findViewById(R.id.btnCurrencyConversion)
        btnLemonade = findViewById(R.id.btnLemonade)
        btnTipCalculator = findViewById(R.id.btnTipCalculator)
        btnTemperature = findViewById(R.id.btnTemperature)
        btnHappyBirthday = findViewById(R.id.btnHappyBirthday)
        btnBMICalculator = findViewById(R.id.btnBMICalculator)
        btnTaskBuddy =findViewById(R.id.btnTaskBuddy)

        edtLogOut.setOnClickListener{
            logout()
        }

        btnCurrencyConversion.setOnClickListener {
            val intent = Intent(this@MainActivity, CurrencyConversion::class.java)
            intent.putExtra("email", email)
            startActivity(intent)
        }

        btnLemonade.setOnClickListener {
            val intent = Intent(this@MainActivity, Lemonade::class.java)
            intent.putExtra("email", email)
            startActivity(intent)
        }

        btnTipCalculator.setOnClickListener {
            val intent = Intent(this@MainActivity, TipCalculator::class.java)
            intent.putExtra("email", email)
            startActivity(intent)
        }

        btnTemperature.setOnClickListener {
            val intent = Intent(this@MainActivity, TemperatureConversion::class.java)
            intent.putExtra("email", email)
            startActivity(intent)
        }

        btnHappyBirthday.setOnClickListener {
            val intent = Intent(this@MainActivity, HappyBirthday::class.java)
            intent.putExtra("email", email)
            startActivity(intent)
        }

        btnBMICalculator.setOnClickListener {
            val intent = Intent(this@MainActivity, BmiCalculator::class.java)
            intent.putExtra("email", email)
            startActivity(intent)
        }

        btnTaskBuddy.setOnClickListener {
            val intent = Intent(this@MainActivity, TaskBuddy::class.java)
            intent.putExtra("email", email)
            startActivity(intent)
        }

    }




    private fun logout(){
        mAuth.signOut()
        val intent = Intent(this@MainActivity, Login::class.java)
        startActivity(intent)



    }
}