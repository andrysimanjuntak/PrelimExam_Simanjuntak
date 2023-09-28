package ph.edu.uic.prelimexam_simanjuntak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ph.edu.uic.prelimexam_simanjuntak.databinding.ActivityTemperatureConversionBinding



private lateinit var binding: ActivityTemperatureConversionBinding
class TemperatureConversion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout XML file and return a binding object instance
        binding = ActivityTemperatureConversionBinding.inflate(layoutInflater)

        // Set the content view of the Activity to be the root view of the layout
        setContentView(binding.root)

        binding.btnConvert.setOnClickListener { calculateTemp() }

        binding.btnBackTemperatureConversion.setOnClickListener {
            val email = intent.getStringExtra("email")
            val intent = Intent(this@TemperatureConversion, MainActivity::class.java)
            intent.putExtra("email", email)
            startActivity(intent)
        }

    }

    private fun calculateTemp() {
        // Get the decimal value from the edtTemp EditText field
        val stringInTextField = binding.edtTemp.text.toString()
        val temp = stringInTextField.toDouble()

        // If the temp is null or 0, then display 0 tip and exit this function early.
        /*if (cost == null || cost == 0.0) {
            displayTip(0.0)
            return
        }*/

        // Get the tip percentage based on which radio button is selected
        val from = when (binding.optionFrom.checkedRadioButtonId) {
            R.id.option_from_celsius -> "celsius"
            R.id.option_from_fahrenheit -> "fahrenheit"
            else -> "kelvin"
        }

        val to = when (binding.optionTo.checkedRadioButtonId) {
            R.id.option_to_celsius -> "celsius"
            R.id.option_to_fahrenheit -> "fahrenheit"
            else -> "kelvin"
        }

        var result = 0.0
        if (from=="celsius"){
            result = when (to){
                "celsius" -> temp
                "fahrenheit" -> (temp * 9/5) + 32
                else -> temp + 273.15
            }
        }
        else if (from=="fahrenheit"){
            result = when (to){
                "celsius" -> (temp - 32) * 5 / 9
                "fahrenheit" -> temp
                else -> ((temp - 32) * 5 / 9) + 273.15
            }
        }
        else if (from=="kelvin"){
            result = when (to){
                "celsius" -> temp - 273.15
                "fahrenheit" -> ((temp - 273.15) * 9/5) + 32
                else -> temp
            }
        }

        binding.txtResult.text = String.format("%.2f", result)

    }
}