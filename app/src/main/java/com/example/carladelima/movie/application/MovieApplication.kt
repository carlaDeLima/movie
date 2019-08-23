package com.example.carladelima.movie.application

import android.app.Application
import com.example.carladelima.movie.BuildConfig.DEBUG
import com.facebook.stetho.Stetho
import com.uphyca.stetho_realm.RealmInspectorModulesProvider

class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupStetho()
    }

    private fun setupStetho() {
        if (DEBUG) {
            val realmInspector = RealmInspectorModulesProvider.builder(this)
                    .withDeleteIfMigrationNeeded(true)
                    .build()
            Stetho.initialize(
                    Stetho.newInitializerBuilder(this)
                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                            .enableWebKitInspector(realmInspector)
                            .build()
            )
        }
    }
}
