package fr.isen.cremades.androiderestaurant

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import fr.isen.cremades.androiderestaurant.databinding.ActivityDetailsBinding

class DetailsActivity : CartCompactActivity() {
    private lateinit var binding: ActivityDetailsBinding

    @SuppressLint("SetTextI18n", "CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val intent = intent
        var quantity = 1
        val item = intent!!.getSerializableExtra(MenuActivity.DETAILS_KEY) as Items

        binding.foodTitle.text = item.name_item_fr
        binding.detailsFood.text = "Ingrédients : "+ item.ingredients!!.joinToString(", ") { it.name_ingredient_fr.toString() }
        binding.buttonPrice.text = "Ajouter au panier : "+ item.prices!![0].price + "€"

        binding.buttonUp.setOnClickListener{
            quantity++
            Log.i("quantity",quantity.toString())
            display(quantity,quantity* item.prices[0].price!!)

        }
        binding.buttonDown.setOnClickListener{
            if(quantity!=1) {
                quantity--
            }
            display(quantity,quantity* item.prices[0].price!!)
        }

        binding.buttonPrice.setOnClickListener{

            Panier.updateCart(ItemPanier(item.image!![0], item.name_item_fr!!,quantity,
                item.prices[0].price!!.toFloat()),this)
            setupBadge()
            Snackbar.make(binding.root,"$quantity ${item.name_item_fr} bien ajouté au panier", Snackbar.LENGTH_SHORT ).show()

        }

        binding.viewSilder.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.viewSilder.adapter = item.image?.let { ViewAdapter(this, it) }

    }
    @SuppressLint("SetTextI18n")
    private fun display(quantity : Int, price : Float){
        binding.itemQuantity.text=quantity.toString()
        binding.buttonPrice.text = "Ajouter au panier : "+price.toString()+"€"
    }

}