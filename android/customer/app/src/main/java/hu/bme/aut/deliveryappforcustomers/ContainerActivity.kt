package hu.bme.aut.deliveryappforcustomers

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import hu.bme.aut.deliveryappforcustomers.databinding.ActivityContainerBinding
import hu.bme.aut.deliveryappforcustomers.view.fragments.*

class ContainerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityContainerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        binding.navView.setNavigationItemSelectedListener(this)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, LoginFragment()).commit()
        binding.navView.setCheckedItem(R.id.login)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.new_transport -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, NewTransportFragment()).commit()
            R.id.active_transports -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ActiveTransportsFragment()).commit()
            R.id.history -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HistoryFragment()).commit()
            R.id.map_view -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MapViewFragment()).commit()
            R.id.settings -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SettingsFragment()).commit()
            R.id.login -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoginFragment()).commit()
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}