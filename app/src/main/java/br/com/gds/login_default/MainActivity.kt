package br.com.gds.login_default

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.gds.login.LoginModuleDependency
import br.com.gds.login.LoginModuleInitializer
import br.com.gds.login.feature.container.action.NavigationScreenAction
import br.com.gds.login_default.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val builderLogin by lazy {
        LoginModuleInitializer.Builder(
            context = this,
            loginModuleDependency = getDependency()
        )
    }

    private val providerSampleImpl by lazy {
        CallbackProviderSampleImpl(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActivity()
        FirebaseApp.initializeApp(applicationContext)
    }

    private fun setupActivity() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        builderLogin.initModuleLogin(
            action = NavigationScreenAction.ToLogin
        )
    }


    private fun getDependency() = LoginModuleDependency(
        layoutSetup = MockUI.layoutSetup,
        loginModuleCallbackProvider = providerSampleImpl
    )
}