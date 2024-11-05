package br.com.gds.login_default

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import br.com.gds.login.feature.MainLoginContainerActivity
import br.com.gds.login.utils.extensions.adjustPaddingView
import br.com.gds.login_default.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActivity()
        FirebaseApp.initializeApp(applicationContext)
    }

    private fun setupActivity() {

        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        adjustPaddingView(binding.root)

        startActivity(
            Intent(this,MainLoginContainerActivity::class.java)
        )
    }
}