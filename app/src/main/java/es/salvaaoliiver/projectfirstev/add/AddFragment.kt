package es.salvaaoliiver.projectfirstev.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import es.salvaaoliiver.projectfirstev.MainActivity
import es.salvaaoliiver.projectfirstev.R
import es.salvaaoliiver.projectfirstev.databinding.FragmentAddBinding

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var stepAdapter: StepAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stepAdapter = StepAdapter()
        binding.recyclerViewSteps.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewSteps.adapter = stepAdapter

        binding.btnAddStep.setOnClickListener {
            stepAdapter.addStep()
        }

        binding.btnSaveRecipe.setOnClickListener {
            val recipeTitle = binding.editTextTitle.text.toString()
            val stepsList = stepAdapter.getStepsList()

            val recipe = Recipe(recipeTitle, stepsList)

            (activity as? MainActivity)?.addRecipe(recipe)

            Toast.makeText(requireContext(), "Receta guardada", Toast.LENGTH_SHORT).show()

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.menuFragmentoContainer, AddFragment())
                .commit()
        }
    }
}
