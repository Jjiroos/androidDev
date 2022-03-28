package fr.isen.cremades.androiderestaurant

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.cremades.androiderestaurant.databinding.PanierCellBinding

class CartAdapter(private val  cart: DataPanier,private val mListener: (ItemPanier) -> Unit) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    private lateinit var binding: PanierCellBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = PanierCellBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding,mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.display(cart.listItem[position])
    }

    override fun getItemCount(): Int {
        return cart.listItem.size
    }

    class ViewHolder(private val binding: PanierCellBinding,private val mListener: (ItemPanier) -> Unit): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun display(item : ItemPanier){
            binding.detailsPrice.text = "${item.price} € * ${item.quantity} = ${item.price*item.quantity.toFloat()} €"
            binding.idDish.text = item.name
            Picasso.get().load(item.image.ifEmpty { null })
                .placeholder(R.drawable.logo)
                .error(R.drawable.logo)
                .into(binding.imDish)
            binding.btReduce.setOnClickListener {
                mListener(item) }
        }
    }
}