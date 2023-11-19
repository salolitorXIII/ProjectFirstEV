package es.salvaaoliiver.projectfirstev.add

import android.net.Uri

data class Recipe(
    val title: String,
    val steps: String,
    var imagePath: Uri?
)