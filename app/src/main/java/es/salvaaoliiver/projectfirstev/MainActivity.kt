package es.salvaaoliiver.projectfirstev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.navigation.NavigationBarView
import es.salvaaoliiver.projectfirstev.databinding.ActivityMainBinding
import androidx.fragment.app.Fragment
import es.salvaaoliiver.projectfirstev.add.AddFragment
import es.salvaaoliiver.projectfirstev.home.HomeFragment
import es.salvaaoliiver.projectfirstev.login.LoginActivity
import es.salvaaoliiver.projectfirstev.menu.MenuFragment
import java.io.File

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userImagesDirectory: File
    var usuario: String = ""
    var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.myToolbar)

        userImagesDirectory = File(filesDir, "user_images")
        if (!userImagesDirectory.exists()) {
            userImagesDirectory.mkdirs()
        }

        binding.bottomNavigation.setOnItemSelectedListener(this)

        usuario = intent.getStringExtra("USUARIO") ?: ""
        password = intent.getStringExtra("PASSWORD") ?: ""
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_logout ->{
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                true
            }

            else -> false
        }
    }

    // REPLACE FRAGMENT
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btnHome -> {
                loadFragment(HomeFragment())
                return true
            }
            R.id.btnSearch -> {
                loadFragment(SearchFragment())
                return true
            }
            R.id.btnAdd -> {
                loadFragment(AddFragment())
                return true
            }
            R.id.btnDrawer -> {
                loadFragment(MenuFragment())
                return true
            }
        }
        return false
    }

    fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.menuFragmentoContainer, fragment)
            .commit()
    }
    // END REPLACE FRAGMENT

}