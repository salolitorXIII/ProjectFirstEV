package es.salvaaoliiver.projectfirstev.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.salvaaoliiver.projectfirstev.databinding.FragmentUserActivityBinding
import java.io.File

class UserActivityFragment : Fragment() {

    private lateinit var binding: FragmentUserActivityBinding

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
        binding = FragmentUserActivityBinding.inflate(layoutInflater, container, false)

        binding.textViewNum.text = getNumRecipes().toString()





        return binding.root
    }

    private fun getNumRecipes(): Int {
        var num = 0
        val recipeDirectories = userImagesDirectory.listFiles()
        recipeDirectories?.forEach { recipeDir ->
            num +=1
        }
        return num
    }



}