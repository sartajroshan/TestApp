package me.sartajroshan.myapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import me.sartajroshan.myapp.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.notesFragment) {
                bottomNav.visibility = View.VISIBLE
                bottomNav.selectedItemId = R.id.notes
            } else {
                bottomNav.visibility = View.GONE

            }
        }
    }
}