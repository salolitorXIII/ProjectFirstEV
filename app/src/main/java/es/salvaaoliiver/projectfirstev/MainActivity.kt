package es.salvaaoliiver.projectfirstev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.navigation.NavigationBarView
import es.salvaaoliiver.projectfirstev.databinding.ActivityMainBinding
import androidx.fragment.app.Fragment
import es.salvaaoliiver.projectfirstev.add.AddFragment
import es.salvaaoliiver.projectfirstev.home.HomeFragment
import java.io.File

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener  {

    private lateinit var binding: ActivityMainBinding

    // VARIABLE DE CLASE FILE PARA CREAR UNAS CARPETAS EN EL METODO onCreate
    private lateinit var userImagesDirectory: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // CREAR CARPETA DONDE SE ALMACENAN LAS IMAGENES DE LAS RECETAS
        // /data/data/es.salaaoliiver.projectfirstev/user_images
        userImagesDirectory = File(filesDir, "user_images")
        if (!userImagesDirectory.exists()) {
            userImagesDirectory.mkdirs()
        }

        binding.bottomNavigation.setOnItemSelectedListener(this)
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

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.menuFragmentoContainer, fragment)
            .commit()
    }
    // END REPLACE FRAGMENT

}