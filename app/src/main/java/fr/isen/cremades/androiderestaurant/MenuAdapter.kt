package fr.isen.cremades.androiderestaurant

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MenuAdapter(val  items: List<Items>, val mListener: (Items) -> Unit)  : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.menu_cell, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MenuAdapter.ViewHolder, position: Int) {
        val plat = items[position]
        holder.dishTile.text = plat.name_item_fr
        holder.dishPrice.text = plat.prices?.get(0)?.price.toString()+"€"
        Picasso.get().load(plat.image?.get(0)?.ifEmpty { null }).error(R.drawable.loading).into(holder.imageDish)

        holder.itemView.setOnClickListener {
            mListener(plat)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        //val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val dishTile: TextView = ItemView.findViewById(R.id.nomPlat)
        val dishPrice: TextView = ItemView.findViewById(R.id.prixPlat)
        val imageDish : ImageView = ItemView.findViewById(R.id.imgPlat)
    }
}