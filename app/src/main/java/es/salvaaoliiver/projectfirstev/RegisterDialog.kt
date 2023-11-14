package es.salvaaoliiver.projectfirstev

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class RegisterDialog: DialogFragment() {

    private lateinit var mListener: DialogFragmentListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is DialogFragmentListener){
            mListener = context
        } else {
            throw Exception("EXCEPTION")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)

            val inflater = requireActivity().layoutInflater

            builder
                .setView(inflater.inflate(R.layout.dialog, null))
                .setPositiveButton("OK") { dialog, id ->
                    mListener.onDialogPostiviveClicked()
                }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    interface DialogFragmentListener {
        fun onDialogPostiviveClicked()
    }
}