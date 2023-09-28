package ph.edu.uic.prelimexam_simanjuntak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HappyBirthday : AppCompatActivity() {

    private lateinit var btnBack: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_happy_birthday)

        btnBack = findViewById(R.id.btnBackHappyBirthday)
        btnBack.setOnClickListener {
            val email = intent.getStringExtra("email")
            val intent = Intent(this@HappyBirthday, MainActivity::class.java)
            intent.putExtra("email", email)
            startActivity(intent)
        }
    }
}