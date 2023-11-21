package es.salvaaoliiver.projectfirstev.menu

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import es.salvaaoliiver.projectfirstev.R

class MyDialog(private val username: String, private val password: String) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.my_dialog, null)

            val textViewUsername = view.findViewById<TextView>(R.id.textViewUsername)
            val textViewPassword = view.findViewById<TextView>(R.id.textViewPassword)
            val closeButton = view.findViewById<Button>(R.id.button)

            textViewUsername.text = textViewUsername.text.toString() + ": " +username
            textViewPassword.text = textViewPassword.text.toString() + ": " +password

            closeButton.setOnClickListener {
                dismiss()
            }

            builder.setView(view)

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
