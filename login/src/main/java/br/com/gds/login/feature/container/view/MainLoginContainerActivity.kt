package br.com.gds.login.feature.container.view

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import br.com.gds.login.LoginModuleDependency
import br.com.gds.login.LoginModuleRouter
import br.com.gds.login.LoginModuleSession
import br.com.gds.login.R
import br.com.gds.login.databinding.ActivityMainLoginContainerBinding
import br.com.gds.login.feature.container.action.NavigationScreenAction
import br.com.gds.login.utils.commons.LayoutSetup
import com.google.firebase.FirebaseApp

class MainLoginContainerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainLoginContainerBinding

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

    private val layoutSetup by lazy {
        LoginModuleSession.loginModuleDependency?.layoutSetup ?: LayoutSetup()
    }

    private val router by lazy { LoginModuleRouter(navController) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActivity()
        FirebaseApp.initializeApp(applicationContext)
    }

    private fun setupActivity() {
        binding = ActivityMainLoginContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configStatusAndNavBar()
        navigationHandler()
    }

    private fun navigationHandler() {
        when (navigationScreenAction) {
            is NavigationScreenAction.ToLogin -> router.navigateGlobalToLogin()
            is NavigationScreenAction.ToRegisterPerson -> router.navigateGlobalToRegister()
            is NavigationScreenAction.ToResetPassword -> router.navigateLoginToResetPassword()
        }
    }

    private fun configStatusAndNavBar() {
        if (layoutSetup.layoutDefault.isStatusBarEnabled) showStatusBar() else hideStatusBar()
        if (layoutSetup.layoutDefault.isNavigationBarEnabled) showNavigationBar() else hideNavigationBar()
    }

    private fun hideNavigationBarAndStatusBar() {
        hideStatusBar()
        hideNavigationBar()
    }


    private fun showNavigationBarAndStatusBar() {
        showStatusBar()
        showNavigationBar()
    }

    private fun hideStatusBar() {
        WindowCompat.getInsetsController(window, window.decorView)
            .hide(WindowInsetsCompat.Type.statusBars())
    }

    private fun hideNavigationBar() {
        WindowCompat.getInsetsController(window, window.decorView)
            .hide(WindowInsetsCompat.Type.navigationBars())
    }

    private fun showStatusBar() {
        WindowCompat.getInsetsController(window, window.decorView)
            .show(WindowInsetsCompat.Type.navigationBars())

        window.statusBarColor = ContextCompat.getColor(
            this@MainLoginContainerActivity,
            layoutSetup.layoutDefault.statusBarColor
        )
    }

    private fun showNavigationBar() {
        WindowCompat.getInsetsController(window, window.decorView)
            .show(WindowInsetsCompat.Type.navigationBars())

        window.navigationBarColor = ContextCompat.getColor(
            this@MainLoginContainerActivity,
            layoutSetup.layoutDefault.navigationBarColor
        )

    }

    companion object {
        private const val NAVIGATION_EVENT_KEY = "navigation_event_key"

        fun newInstance(
            context: Context,
            navigationScreenAction: NavigationScreenAction
        ) = Intent(context, MainLoginContainerActivity::class.java).apply {
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