package psm.lab.w3_navigation_cyklzycia

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ArtApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ArtApp)
            modules(appModule)
        }
    }
}