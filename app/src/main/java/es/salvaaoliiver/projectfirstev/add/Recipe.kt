package es.salvaaoliiver.projectfirstev.add

import android.net.Uri

import java.io.Serializable

data class Recipe(
    val title: String,
    val steps: String,
    var imagePath: Uri?
) : Serializable