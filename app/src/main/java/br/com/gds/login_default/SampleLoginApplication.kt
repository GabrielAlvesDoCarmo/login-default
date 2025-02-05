package br.com.gds.login_default

import android.app.Application
import br.com.gds.login.BuildConfig
import br.com.gds.login.di.loginModulesInjection
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
private const val TAG = "SampleLoginApplication"
class SampleLoginApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        val options = FirebaseOptions.Builder()
            .setProjectId("login-default-module")
            .setApplicationId(BuildConfig.LIBRARY_PACKAGE_NAME)
            .setApiKey("AIzaSyBHyRMdG_coj1Em8za8zddofL0Wa6qPMmI")
            .build()
        FirebaseApp.initializeApp(this, options)

        startKoin{
            androidLogger()
            androidContext(this@SampleLoginApplication)
            modules(loginModulesInjection)
        }
    }
}