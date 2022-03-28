package fr.isen.cremades.androiderestaurant

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.cremades.androiderestaurant.databinding.ActivityPanierBinding

class CartActivity : CartCompactActivity() {
    private lateinit var binding: ActivityPanierBinding

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPanierBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.listCart.layoutManager = LinearLayoutManager(this)
        binding.listCart.adapter = CartAdapter(Panier.getCart(this)){ itCart ->
            val item  = itCart.copy()
            item.quantity = -1
            Panier.updateCart(item,this)
            setupBadge()
            updatePrice(Panier.panier.listItem)
            binding.listCart.adapter?.notifyDataSetChanged()
        }
        updatePrice(Panier.panier.listItem)
    }
    @SuppressLint("SetTextI18n")
    fun updatePrice(list : MutableList<ItemPanier>){
        if(list.sumOf{(it.price* it.quantity).toDouble()}!=0.0){
            binding.buyButton.text =  "Payer ${list.sumOf{(it.price * it.quantity).toDouble()}} €"
        }
        else{
            binding.buyButton.visibility = View.GONE
        }
    }
}