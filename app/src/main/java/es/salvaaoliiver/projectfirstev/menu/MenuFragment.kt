package es.salvaaoliiver.projectfirstev.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.salvaaoliiver.projectfirstev.MainActivity
import es.salvaaoliiver.projectfirstev.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(layoutInflater, container, false)

        binding.frameLayout1
        binding.frameLayout2.setOnClickListener {
            if (activity is MainActivity) {
                val mainActivity = activity as MainActivity
                val usuario = mainActivity.usuario
                val password = mainActivity.password
                val customDialog = MyDialog(usuario, password)

                customDialog.show(parentFragmentManager, "CustomDialogFragmentTag")
            }


        }
        binding.frameLayout3.setOnClickListener {
            if (activity is MainActivity) {
                val myActivityy = activity as MainActivity
                myActivityy.loadFragment(UserActivityFragment())
            }
        }

        return binding.root
    }

}