package es.salvaaoliiver.projectfirstev.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import es.salvaaoliiver.projectfirstev.R
import es.salvaaoliiver.projectfirstev.add.Recipe

class RecipesAdapter(private val recipesList: List<Recipe>) :
    RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipesList[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = recipesList.size

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.textTitle)
        private val imageRecipeView: ImageView = itemView.findViewById(R.id.imageRecipe)

        fun bind(recipe: Recipe) {
            titleTextView.text = recipe.title

            // Carga la imagen utilizando Glide (o la biblioteca que prefieras)
            Glide.with(itemView)
                .load(recipe.imagePath)  // Supongamos que Recipe tiene un atributo imagePath con la ruta de la imagen
                .placeholder(R.drawable.placeholder_image)  // Imagen de carga mientras se descarga la imagen
                .into(imageRecipeView)
        }
    }
}
