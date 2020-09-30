package com.ahmedalaa.recipes.ui.list.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahmedalaa.recipes.data.model.Recipe
import com.ahmedalaa.recipes.databinding.ItemRecipeBinding
import org.jetbrains.annotations.NotNull

class RecipesListAdapter : RecyclerView.Adapter<RecipesListAdapter.ViewHolder>() {

    var onItemClick: ((Recipe, ImageView) -> Unit)? = null


    fun setOnItemClickListener(listener: (Recipe, ImageView) -> Unit) {
        onItemClick = listener
    }

    private val itemCallback = object : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    }
    val differ = AsyncListDiffer(this, itemCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }


    var recipe: List<Recipe>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = recipe[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = recipe.size

    inner class ViewHolder(private val itemRecipeBinding: @NotNull ItemRecipeBinding) :
        RecyclerView.ViewHolder(itemRecipeBinding.root) {
        fun bind(recipe: Recipe) {
            itemRecipeBinding.apply {
                this.recipe = recipe
                executePendingBindings()
                root.setOnClickListener {
                    onItemClick?.invoke(recipe, ivRecipeImg)
                }
            }
        }
    }
}