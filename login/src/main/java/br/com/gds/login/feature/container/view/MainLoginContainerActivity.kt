package br.com.gds.login.feature.container.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import br.com.gds.login.LoginModuleDependency
import br.com.gds.login.LoginModuleRouter
import br.com.gds.login.LoginModuleSession
import br.com.gds.login.R
import br.com.gds.login.databinding.ActivityMainLoginContainerBinding
import br.com.gds.login.feature.container.action.NavigationScreenAction
import br.com.gds.login.utils.extensions.adjustPaddingView
import com.google.firebase.FirebaseApp

class MainLoginContainerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainLoginContainerBinding

    private val loginModuleDependency by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.extras?.getParcelable(DEPENDENCY_KEY, LoginModuleDependency::class.java)
        } else {
            intent.getParcelableExtra(DEPENDENCY_KEY)
        } ?: LoginModuleDependency()
    }

    private val navigationScreenAction by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.extras?.getParcelable(NAVIGATION_EVENT_KEY, NavigationScreenAction::class.java)
        } else {
            intent.getParcelableExtra(NAVIGATION_EVENT_KEY)
        } ?: NavigationScreenAction.ToLogin
    }

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_login_module) as NavHostFragment).navController
    }

    private val router by lazy { LoginModuleRouter(navController) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LoginModuleSession.loginModuleDependency = loginModuleDependency
        setupActivity()
        FirebaseApp.initializeApp(applicationContext)
    }

    @SuppressLint("ResourceType")
    private fun setupActivity() {
        binding = ActivityMainLoginContainerBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        adjustPaddingView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, loginModuleDependency.loginLayoutDefault.statusBarColor)
        window.navigationBarColor = ContextCompat.getColor(this, loginModuleDependency.loginLayoutDefault.navigationBarColor)

        when(navigationScreenAction){
            is NavigationScreenAction.ToLogin -> router.navigateGlobalToLogin()
            is NavigationScreenAction.ToRegisterPerson -> router.navigateGlobalToRegister()
            is NavigationScreenAction.ToResetPassword -> router.navigateLoginToResetPassword()
        }
    }

    companion object {
        private const val DEPENDENCY_KEY = "dependency_key"
        private const val NAVIGATION_EVENT_KEY = "navigation_event_key"

        fun newInstance(
            context: Context,
            loginModuleDependency: LoginModuleDependency,
            navigationScreenAction: NavigationScreenAction
        ) = Intent(context, MainLoginContainerActivity::class.java).apply {
            putExtra(DEPENDENCY_KEY, loginModuleDependency)
            putExtra(NAVIGATION_EVENT_KEY, navigationScreenAction)
        }
    }
}





//        // Dentro do LoginModule

//        LoginModuleInitializer.Builder(
//            context = this,
//            navController = navController,
//            loginModuleDependency = LoginModuleDependency(
//                registerFragment = RegisterPersonalUI(
//                    enableButtonAddress = false,
//                    enableNickname = false,
//                    backgroundColor = getColor(R.color.test_1),
//                    titleColor = getColor(R.color.test_2),
//                ),
//                addressRegisterFragment = AddressRegisterUI(backgroundColor = 0, titleColor = 0),
//                autoRegisterFragment = AutomovelRegisterUI(backgroundColor = 0, titleColor = 0),
//                loginFragment = LoginUI(
//                    backgroundColor = R.color.test_1,
//                    titleColor = R.color.test_2
//                ),
//                resetPasswordFragment = ResetPasswordUI(backgroundColor = 0, titleColor = 0),
//            )
//        ).buildLogin()

//        LoginModuleInitializer.Builder(
//            context = this,
//            navController = navController,
//            loginModuleDependency = loginModuleDependency
//        ).buildRegister()