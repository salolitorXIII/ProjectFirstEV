package es.salvaaoliiver.projectfirstev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.salvaaoliiver.projectfirstev.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), LoginFragment.LoginListener {

    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }


    // LoginFragment.LoginListener
    override fun onBtnLoginClicked(registrado: Boolean) {
        if (registrado){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {

        }
    }

    override fun onBtnRegisterSnackbarClicked() {
        // Manejar la acción de registro desde aquí
        val loginFragment = supportFragmentManager.findFragmentById(R.id.menuFragmentoContainer) as? LoginFragment
        loginFragment?.register()
    }

    override fun onBtnRegisterClicked() {
        val dialog = RegisterDialog()
        dialog.show(supportFragmentManager, "")
    }
    //END
}