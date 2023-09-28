package ph.edu.uic.prelimexam_simanjuntak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ph.edu.uic.prelimexam_simanjuntak.databinding.ActivityBmiConversionBinding

private lateinit var binding: ActivityBmiConversionBinding
class BmiCalculator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBmiConversionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener {
            calculateBMI()
        }

        binding.btnBackBMI.setOnClickListener {
            val email = intent.getStringExtra("email")
            val intent = Intent(this@BmiCalculator, MainActivity::class.java)
            intent.putExtra("email", email)
            startActivity(intent)
        }

    }
}

private fun calculateBMI() {
    // Get the decimal value from the edtWeight EditText field
    val stringInTextFieldWeight = binding.edtWeight.text.toString()

    // Get the decimal value from the edtHeight EditText field
    val stringInTextFieldHeight = binding.edtHeight.text.toString()


    val weight = stringInTextFieldWeight.toDouble()
    val height = stringInTextFieldHeight.toDouble()/100

    val bmi = weight / (height * height)

    binding.txtBMI.text = "BMI"
    binding.txtResultBMI.text = String.format("%.2f", bmi)


}