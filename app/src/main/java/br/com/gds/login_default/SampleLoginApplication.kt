package br.com.gds.login_default

import android.app.Application
import br.com.gds.login.ci.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SampleLoginApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@SampleLoginApplication)
            modules(appModules)
        }
    }
}