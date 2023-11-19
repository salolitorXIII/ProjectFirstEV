package es.salvaaoliiver.projectfirstev.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import es.salvaaoliiver.projectfirstev.R
import es.salvaaoliiver.projectfirstev.add.Recipe
import es.salvaaoliiver.projectfirstev.databinding.FragmentHomeBinding
import java.io.File

class HomeFragment : Fragment(), RecipesAdapter.RecipeClickListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var recipesAdapter: RecipesAdapter
    private lateinit var recipesList: MutableList<Recipe>

    private val userImagesDirectory: File by lazy {
        File(requireContext().filesDir, "user_images").apply {
            if (!exists()) {
                mkdirs()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipesList = loadRecipes().toMutableList()

        recipesAdapter = RecipesAdapter(this)
        binding.recyclerViewRecipes.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewRecipes.adapter = recipesAdapter

        recipesAdapter.submitList(recipesList)
    }

    private fun loadRecipes(): List<Recipe> {
        val loadedRecipes = mutableListOf<Recipe>()

        val recipeDirectories = userImagesDirectory.listFiles()
        recipeDirectories?.forEach { recipeDir ->
            val imagePath = File(recipeDir, "image.jpg").toUri()
            val ficherotxt = readRecipeText(recipeDir)

            val stepsLines = ficherotxt.split("\n")

            val firstLine = stepsLines.firstOrNull() ?: ""

            val remainingLines = stepsLines.drop(1).joinToString("\n")

            loadedRecipes.add(Recipe(firstLine, remainingLines, imagePath))
        }

        return loadedRecipes
    }

    private fun readRecipeText(recipeDir: File): String {
        val textFile = File(recipeDir, "receta.txt")
        return if (textFile.exists()) {
            textFile.readText()
        } else {
            ""
        }
    }

    override fun onRecipeClick(recipe: Recipe) {
        val fragment = RecipeDetailFragment.newInstance(recipe)
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.menuFragmentoContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
}

