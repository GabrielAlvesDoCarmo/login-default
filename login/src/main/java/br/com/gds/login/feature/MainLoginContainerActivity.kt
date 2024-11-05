package br.com.gds.login.feature

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import br.com.gds.login.databinding.ActivityMainLoginContainerBinding
import br.com.gds.login.utils.extensions.adjustPaddingView
import com.google.firebase.FirebaseApp

class MainLoginContainerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainLoginContainerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActivity()
        FirebaseApp.initializeApp(applicationContext)
    }

    private fun setupActivity() {
        binding = ActivityMainLoginContainerBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        adjustPaddingView(binding.root)
    }
}