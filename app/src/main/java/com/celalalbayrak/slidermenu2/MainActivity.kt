package com.celalalbayrak.slidermenu2

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener { menuItem ->
            handleMenuClick(menuItem)
            true
        }

        // İlk açılışta anasayfa fragmenti göster
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
            navView.setCheckedItem(R.id.nav_home)
        }
    }

    private fun handleMenuClick(menuItem: MenuItem) {
        val fragment = when (menuItem.itemId) {
            R.id.nav_home -> HomeFragment()
            R.id.nav_settings -> SettingsFragment()
            R.id.nav_about -> AboutFragment()
            else -> null
        }

        fragment?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, it)
                .commit()
        }

        drawerLayout.closeDrawers()
    }
}
