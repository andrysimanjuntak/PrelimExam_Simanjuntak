package ph.edu.uic.prelimexam_simanjuntak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore

class SignUp : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnSignUp: Button
    private lateinit var btnBack: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportActionBar?.hide()

        edtName = findViewById(R.id.edt_name)
        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        btnSignUp = findViewById(R.id.btnSignUp)
        btnBack = findViewById(R.id.btnBack)

        mAuth = FirebaseAuth.getInstance()

        btnSignUp.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            val name = edtName.text.toString()

            signUp(email, password, name)
        }
        btnBack.setOnClickListener {
            val intent = Intent(this@SignUp, Login::class.java)
            startActivity(intent)
        }

    }

    private fun signUp(email: String, password: String, name: String){
        try {
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        // jump to home activity
                        val db = FirebaseFirestore.getInstance()

                        data class User(val name: String, val email: String)

                        // Create a new user
                        val newUser = User(name, email)
                        db.collection("users")
                            .add(newUser)
                            .addOnSuccessListener { documentReference ->
                                //println("DocumentSnapshot added with ID: ${documentReference.id}")
                                //Toast.makeText(this@SignUp, "DocumentSnapshot added with ID: ${documentReference.id}", Toast.LENGTH_SHORT).show()
                                Toast.makeText(this@SignUp, "User $name is added to Firestore Database", Toast.LENGTH_SHORT).show()
                            }
                            .addOnFailureListener { e ->
                                //println("Error adding document: $e")
                                Toast.makeText(this@SignUp, "Error adding document: $e", Toast.LENGTH_SHORT).show()
                            }

                        val intent = Intent(this@SignUp, MainActivity::class.java)
                        intent.putExtra("email", email)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        //Toast.makeText(this@SignUp, "Some error occured", Toast.LENGTH_SHORT).show()
                    }
                }

            .addOnFailureListener { exception ->
                // Check for specific exceptions
                when (exception) {
                    is FirebaseAuthWeakPasswordException -> {
                        //val message = exception.reason
                        Toast.makeText(this@SignUp, "Invalid password : ${exception.reason}", Toast.LENGTH_SHORT).show()
                    }
                    is FirebaseAuthInvalidCredentialsException -> {
                        Toast.makeText(this@SignUp, "Invalid email format", Toast.LENGTH_SHORT).show()
                    }

                    else -> {
                        Toast.makeText(this@SignUp, "An error occurred: ${exception.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        catch (e: Exception) {
            Toast.makeText(this@SignUp, "An error occurred: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}