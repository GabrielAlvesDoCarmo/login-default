package br.com.gds.login_default

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import br.com.gds.login.LoginModuleDependency
import br.com.gds.login.feature.container.view.MainLoginContainerActivity
import br.com.gds.login.feature.container.action.NavigationScreenAction
import br.com.gds.login.feature.container.model.LayoutDefault
import br.com.gds.login.feature.login.model.LoginUI
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
        setContentView(binding.root)
        startActivity(
           MainLoginContainerActivity.newInstance(
               context = this,
               loginModuleDependency = LoginModuleDependency(
                   loginFragment = LoginUI(
                       backgroundColor = br.com.gds.login.R.color.test_2,
                       titleColor = br.com.gds.login.R.color.test_1
                   ),
                   layoutDefault = LayoutDefault(
                       statusBarColor = br.com.gds.login.R.color.edit_text_incorrect,
                       navigationBarColor = br.com.gds.login.R.color.test_1,
                   )
               ),
               navigationScreenAction = NavigationScreenAction.ToLogin
           )
        )


    }
}