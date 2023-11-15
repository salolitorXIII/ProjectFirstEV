package es.salvaaoliiver.projectfirstev.add

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import es.salvaaoliiver.projectfirstev.databinding.ItemStepBinding

class StepAdapter : RecyclerView.Adapter<StepAdapter.StepViewHolder>() {

    private val stepsList = mutableListOf<Step>()
    private val REQUEST_CAMERA_PERMISSION_CODE = 124

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemStepBinding.inflate(inflater, parent, false)
        return StepViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StepViewHolder, position: Int) {
        val step = stepsList[position]
        holder.bind(step)
    }

    override fun getItemCount(): Int = stepsList.size

    fun addStep() {
        stepsList.add(Step("", ""))
        notifyItemInserted(stepsList.size - 1)
    }

    fun getStepsList(): List<Step> = stepsList

    inner class StepViewHolder(private val binding: ItemStepBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(step: Step) {
            binding.editTextStepDescription.setText(step.description)

            binding.btnSelectImage.setOnClickListener {
                requestCameraPermission()
            }
        }

        private fun requestCameraPermission() {
            val context = binding.root.context
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    context as Activity,
                    arrayOf(Manifest.permission.CAMERA),
                    REQUEST_CAMERA_PERMISSION_CODE
                )
            } else {
                Toast.makeText(context, "Permiso de la cámara ya concedido", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
