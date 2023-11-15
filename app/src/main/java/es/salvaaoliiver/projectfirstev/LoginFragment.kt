package es.salvaaoliiver.projectfirstev

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import es.salvaaoliiver.projectfirstev.databinding.FragmentLoginBinding

class LoginFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentLoginBinding

    private var listener: LoginListener? = null

    private val usuarios: MutableList<String> = mutableListOf("Salva-1234")


    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is LoginListener){
            listener = context
        } else{
            throw Exception("EXCEPCION")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        binding.btnLogin.setOnClickListener(this)
        binding.btnRegister.setOnClickListener(this)

        return binding.root
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnLogin -> {
                iniciarSesion()

            }
            R.id.btnRegister -> {
                register()
            }
        }
    }

    private fun comprobarUserPassword(txtUsuario: String, txtPassword: String): Boolean {
        for (u: String in usuarios){
            val uUserPassword: List<String> = u.split("-")
            if (uUserPassword.get(0) == txtUsuario && uUserPassword.get(1) == txtPassword){
                return true
            }
        }
        return false
    }

    fun register(){
        if (binding.inputUser.text.isNullOrEmpty() || binding.inputPassword.text.isNullOrEmpty()) {
            Toast.makeText(context, "RELLENA LOS CAMPOS", Toast.LENGTH_SHORT).show()
        } else {
            val userPassword: String = binding.inputUser.text.toString() + "-" + binding.inputPassword.text.toString()
            usuarios.add(userPassword)
        }
    }

    fun iniciarSesion(){
        if (binding.inputUser.text.isNullOrEmpty() || binding.inputPassword.text.isNullOrEmpty()) {
            Toast.makeText(context, "RELLENA LOS CAMPOS", Toast.LENGTH_SHORT).show()
        } else {
            if (comprobarUserPassword(binding.inputUser.text.toString(), binding.inputPassword.text.toString())) {
                listener?.onBtnLoginClicked()
            }
            else
                Snackbar.make(binding.root, "USUARIO O CONTRASEÑA INCORRECTOS", Snackbar.LENGTH_LONG)
                    .setAction("REGISTRARSE AHORA") {
                        register()
                    }.show()
        }
    }

    interface LoginListener{
        fun onBtnLoginClicked()
        fun onBtnRegisterSnackbarClicked()
        fun onBtnRegisterClicked()

    }
}