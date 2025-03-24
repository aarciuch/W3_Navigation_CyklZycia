package psm.lab.w3_navigation_cyklzycia

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import psm.lab.w3_navigation_cyklzycia.databinding.ActivityMain2Binding

class MainActivity3 : AppCompatActivity() {

    lateinit var binding: ActivityMain2Binding
    lateinit var param: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goBack.setOnClickListener {
            intent.putExtra("liczba", param)
            setResult(111, intent)
            finish()
        }

        binding.posInt.setOnClickListener {
            val posIntent = Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?=" + "Warszawa, Kaliskiego 2"))
            startActivity(posIntent)
        }

    }

    override fun onResume() {
        super.onResume()
        param = intent.getStringExtra("liczba").toString()
        binding.tv2.text = param
        var paramInt = param.toInt() + 100
        param = paramInt.toString()
        Toast.makeText(
            applicationContext,
            "parametr po zmianie = ${param}",
            Toast.LENGTH_LONG
        ).show()
        binding.tv2.text = "przed = ${binding.tv2.text.toString()} :: po =  ${param}"
    }
}