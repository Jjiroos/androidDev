package fr.isen.cremades.androiderestaurant

import java.io.Serializable

data class DataPanier(

    var listItem : MutableList<ItemPanier>
) : Serializable

data class ItemPanier(
    var image : String,
    var name : String,
    var quantity : Int,
    var price : Float
): Serializable