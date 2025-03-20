package psm.lab.w3_navigation_cyklzycia

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import psm.lab.w3_navigation_cyklzycia.databinding.ActivityMainBinding
import java.util.Date

class MainActivity2 : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
       binding = ActivityMainBinding.inflate(layoutInflater)
       setContentView(binding.root)
       binding.tv1.text = Date().toString()
    }
}