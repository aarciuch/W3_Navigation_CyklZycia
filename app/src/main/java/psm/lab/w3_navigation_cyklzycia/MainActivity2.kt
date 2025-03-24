package psm.lab.w3_navigation_cyklzycia

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil.setContentView
import psm.lab.w3_navigation_cyklzycia.databinding.ActivityMainBinding
import java.util.Date

class MainActivity2 : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    var startForResult: ActivityResultLauncher<Intent>? = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
        result: ActivityResult ->
            if(result.resultCode == 111) {
                binding.wynik.text = "wynik = ${result.data?.getStringExtra("liczba")}"
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //enableEdgeToEdge()
       binding = ActivityMainBinding.inflate(layoutInflater)
       setContentView(binding.root)
       binding.tv1.text = Date().toString()

       binding.go22WithParams.setOnClickListener {
           val intent = Intent(applicationContext, MainActivity3::class.java)
           intent.putExtra("liczba", binding.paramTxt.text.toString())
           startForResult?.launch(intent)
       }


    }
}