package com.udacity.shoestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.edit
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ActivityMainBinding
import com.udacity.shoestore.utils.clearAppData
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration : AppBarConfiguration

    private val mBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        drawerLayout = mBinding.drawerLayout
        val navController: NavController = this.findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        // prevent nav gesture if not on start destination
        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, bundle: Bundle? ->
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

            when (nd.id) {
                nc.graph.startDestination -> {
                    supportActionBar?.hide()
                }
                R.id.loginFragment -> {
                    supportActionBar?.hide()
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }
                R.id.welcomeFragment -> {
                    supportActionBar?.show()
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }
                R.id.shoeListFragment -> {
                    supportActionBar?.show()
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }
                else -> {
                    supportActionBar?.show()
                }

            }
        }
        NavigationUI.setupWithNavController(mBinding.navView, navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    fun logoutUser(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
        clearAppData()
    }
}