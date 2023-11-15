package es.salvaaoliiver.projectfirstev.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.salvaaoliiver.projectfirstev.MainActivity
import es.salvaaoliiver.projectfirstev.R
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
    override fun onBtnLoginClicked() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onBtnRegisterSnackbarClicked() {
        val loginFragment = supportFragmentManager.findFragmentById(R.id.menuFragmentoContainer) as? LoginFragment
        loginFragment?.register()
    }
    //END
}