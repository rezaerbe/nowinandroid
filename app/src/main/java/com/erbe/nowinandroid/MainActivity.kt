package com.erbe.nowinandroid

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.erbe.nowinandroid.databinding.ActivityMainBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navHostFragment: NavHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }
    private val navController: NavController by lazy {
        navHostFragment.navController
    }

    @Inject
    lateinit var remoteConfig: FirebaseRemoteConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setupWithNavController(navController)

        // processRemoteConfig()
    }

    private fun processRemoteConfig() {
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val updated = task.result
                    Log.d("TAG_PRO", "Config params updated: $updated")
                    getRemoteConfig()
                } else {
                    Log.d("TAG_PRO", "Config params updated: failed")
                }
            }
    }

    private fun getRemoteConfig() {
        val one = remoteConfig.getBoolean("show_section_one")
        val two = remoteConfig.getBoolean("show_section_two")

        Log.d("TAG_PRO", "One: $one - Two: $two")
    }
}