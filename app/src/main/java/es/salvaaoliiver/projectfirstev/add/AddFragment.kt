package es.salvaaoliiver.projectfirstev.add

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.*
import es.salvaaoliiver.projectfirstev.R
import es.salvaaoliiver.projectfirstev.databinding.FragmentAddBinding
import java.io.File

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private var imagenPrincipal: Uri? = null

    private val pickmedia = registerForActivityResult(PickVisualMedia()) { uri ->
        if (uri != null) {
            // Imagen seleccionada
            imagenPrincipal = uri
            binding.imageViewCover.setImageURI(imagenPrincipal)
        } else {
            // No se seleccionó ninguna imagen
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageViewCover.setOnClickListener {
            pickmedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
        }

        binding.btnSaveRecipe.setOnClickListener {
            val recipe = Recipe(
                binding.editTextTitle.text.toString(),
                binding.editTextSteps.text.toString(),
                imagenPrincipal
            )

            saveRecipeImages(generateRecipeId(), recipe)

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.menuFragmentoContainer, AddFragment())
                .commit()
        }
    }

    private fun generateRecipeId(): String {
        return "receta_${System.currentTimeMillis()}"
    }

    private fun saveRecipeImages(recipeId: String, recipe: Recipe) {
        val userImagesDirectory = File(requireContext().filesDir, "user_images")
        val recipeDirectory = File(userImagesDirectory, recipeId)

        if (!recipeDirectory.exists()) {
            recipeDirectory.mkdirs()
        }

        // Guarda la imagen principal
        saveImage(recipeDirectory, recipe.imagePath)

        // Guarda el nombre y pasos en un archivo de texto
        saveRecipeText(recipeDirectory, recipe)

    }

    private fun saveImage(directory: File, imagePath: Uri?) {
        imagePath?.let { uri ->
            val inputStream = requireContext().contentResolver.openInputStream(uri)
            val destinationFile = File(directory, "image.jpg")

            inputStream?.use { input ->
                destinationFile.outputStream().use { output ->
                    input.copyTo(output)
                }
            }
        }
    }

    private fun saveRecipeText(directory: File, recipe: Recipe) {
        val recipeTextFile = File(directory, "receta.txt")
        recipeTextFile.writeText(recipe.title + "\n" + recipe.steps)
    }

}
