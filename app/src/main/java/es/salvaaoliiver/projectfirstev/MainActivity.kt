package es.salvaaoliiver.projectfirstev

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.navigation.NavigationBarView
import es.salvaaoliiver.projectfirstev.databinding.ActivityMainBinding
import androidx.fragment.app.Fragment
import es.salvaaoliiver.projectfirstev.add.AddFragment
import es.salvaaoliiver.projectfirstev.add.Recipe
import es.salvaaoliiver.projectfirstev.add.Step
import es.salvaaoliiver.projectfirstev.home.HomeFragment

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener  {

    private val recipesList = mutableListOf(
        Recipe(
            "Macarrones al Horno",
            listOf(
                Step("Calentar el aceite de oliva en una sartén y sofreír la cebolla a fuego medio-bajo.", "drawable/macarronesuno"),
                Step("Dejar cocer a fuego medio el tomate. Probar y ajustar de sal y pimienta.", "drawable/horno_image")
            ), ""
        ),
    )


    private lateinit var binding: ActivityMainBinding

    private val REQUEST_CAMERA_PERMISSION_CODE = 124

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.bottomNavigation.setOnItemSelectedListener(this)

        recipesList.addAll(loadRecipes())
    }

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

    // RECIPES
    private fun loadRecipes(): List<Recipe> {
        // FUTURA FUNCION PARA CARGAR LAS RECETAS DE LA CUENTA
        return emptyList()
    }

    fun getRecipesList(): List<Recipe> {
        return recipesList
    }

    fun addRecipe(recipe: Recipe) {
        recipesList.add(recipe)
        saveRecipes(recipesList)
    }

    private fun saveRecipes(recipes: List<Recipe>) {
        // FUTURA FUNCION PARA GUARDAR LAS RECETAS DE LA CUENTA
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CAMERA_PERMISSION_CODE){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso concedido para la cámara", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permiso denegado para la cámara", Toast.LENGTH_SHORT).show()
            }
        }else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
    //FIN RECIPES
}