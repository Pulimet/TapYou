package com.att.tapyou.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.att.tapyou.R
import com.att.tapyou.databinding.ActivityMainBinding
import com.att.tapyou.ui.navigation.IntentParams
import com.att.tapyou.ui.navigation.NavParams
import com.att.tapyou.ui.navigation.NavViewModel
import com.att.tapyou.utils.extensions.Extensions.collectIt
import com.att.tapyou.utils.logs.logE
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val navViewModel: NavViewModel by viewModel()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewBinding()
        observeFragmentNavigation()
        observeActivityNavigation()
    }

    private fun setViewBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        setupNavigationUi()
    }

    private fun setupNavigationUi() {
        navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(getListOfHomeDestinations())
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
    }

    private fun observeFragmentNavigation() {
        navViewModel.apply {
            getChangeNavigation.collectIt(this@MainActivity) { navParams ->
                try {
                    navigateTo(navParams)
                } catch (e: IllegalArgumentException) {
                    logE(t = e)
                }
            }
            getNavigateUp.collectIt(this@MainActivity) {
                if (it) navController.navigateUp()
            }
        }
    }

    private fun navigateTo(navParams: NavParams) {
        if (navParams.navDirections == null) return
        navController.navigate(
            navParams.navDirections.actionId,
            navParams.navDirections.arguments,
            navParams.navOptions,
            navParams.extras
        )
    }

    private fun observeActivityNavigation() {
        navViewModel.getStartActivity.collectIt(this) { intentParams ->
            try {
                startActivity(intentParams)
            } catch (e: Exception) {
                logE(t = e)
            }
        }
    }

    private fun startActivity(intentParams: IntentParams) {
        if (intentParams.intent == null) return
        intentParams.clazz?.let { clazz ->
            intentParams.intent.setClass(this, clazz)
        }
        startActivity(intentParams.intent)
        if (intentParams.finish) finish()
    }

    private fun getListOfHomeDestinations() = setOf(R.id.homeFragment)

    override fun onSupportNavigateUp() = navController.navigateUp() || super.onSupportNavigateUp()

}