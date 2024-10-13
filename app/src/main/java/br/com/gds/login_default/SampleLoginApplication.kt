package br.com.gds.login_default

import android.app.Application
import android.util.Log
import br.com.gds.login.BuildConfig
import br.com.gds.login.ci.appModules
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.app
import com.google.firebase.options
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
            modules(appModules)
        }
    }
}