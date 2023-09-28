package ph.edu.uic.prelimexam_simanjuntak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

private lateinit var edtPeso: EditText
private lateinit var btnConvert: Button
private lateinit var txtCurrency: TextView
private lateinit var txtValue: TextView
private lateinit var btnBackCurrency: Button
class CurrencyConversion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_conversion)

        edtPeso = findViewById(R.id.edtPeso)
        btnConvert = findViewById(R.id.btnCalculate)
        txtCurrency = findViewById(R.id.txtBMI)
        txtValue = findViewById(R.id.txtResultBMI)
        btnBackCurrency = findViewById(R.id.btnBackBMI)

        btnConvert.setOnClickListener{
            txtCurrency.text = "IDR"
            val pesoValue = edtPeso.text.toString().toDouble()
            val idrValue = convertPHPToIDR(pesoValue)
            txtValue.text = idrValue

        }

        btnBackCurrency.setOnClickListener{
            val email = intent.getStringExtra("email")
            val intent = Intent(this@CurrencyConversion, MainActivity::class.java)
            intent.putExtra("email", email)
            startActivity(intent)
        }

    }

    private fun convertPHPToIDR(peso: Double): String {
        val currency = 270.9
        var idr = String.format("%.2f", peso * currency)
        return idr

    }



}