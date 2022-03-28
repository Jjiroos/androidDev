package fr.isen.cremades.androiderestaurant

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import java.io.File

@SuppressLint("StaticFieldLeak")
object Panier {

    var panier : DataPanier = DataPanier(arrayListOf())

    fun updateCart(cartItem: ItemPanier,context: Context) {
        if(cartItem.quantity==0){
            panier.listItem.remove(cartItem)
        }
        if(panier.listItem.any{it.name == cartItem.name}){
            val existElem = panier.listItem.first{it.name == cartItem.name}
            existElem.quantity += cartItem.quantity
            if(existElem.quantity<=0)
                panier.listItem.remove(existElem)
        }else{
            if(cartItem.quantity>0)
                panier.listItem.add(cartItem)
        }
        saveCart(context)

    }

    @SuppressLint("CommitPrefEdits")
    fun saveCart ( context: Context){
        val panierJson : String = Gson().toJson(panier)
        val file = File(context.filesDir,"cart.json")
        file.writeText(panierJson)
        val objectSharedPreference : SharedPreferences = context.getSharedPreferences("cart",Context.MODE_PRIVATE)
        val fileSharedPreferences = objectSharedPreference.edit()
        fileSharedPreferences.putInt("nombre total",panier.listItem.sumOf { it.quantity })
        fileSharedPreferences.apply()
    }

    fun getCart(context: Context): DataPanier {
        val getJson = File(context.filesDir, "cart.json")
        val read = getJson.readText()
        panier = Gson().fromJson(read, DataPanier::class.java)
        return panier
    }

}