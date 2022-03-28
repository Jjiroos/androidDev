package fr.isen.cremades.androiderestaurant

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DataMenu(
    @SerializedName("data") val data: List<Menu>
) : Serializable

data class Menu(
    @SerializedName("name_fr") val name_fr: String? = null,
    @SerializedName("name_en") val name_en: String? = null,
    @SerializedName("items") val items: List<Items>? = null
) : Serializable

data class Items(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name_fr") val name_item_fr: String? = null,
    @SerializedName("name_en") val name_item_en: String? = null,
    @SerializedName("id_category") val id_category: Int? = null,
    @SerializedName("category_name_fr") val category_name_fr: String? = null,
    @SerializedName("category_name_en") val category_name_en: String? = null,
    @SerializedName("images") val image: List<String>? = null,
    @SerializedName("ingredients") val ingredients: List<Ingredients>? = null,
    @SerializedName("prices") val prices: List<Prices>? = null,
) : Serializable

data class Ingredients(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name_fr") val name_ingredient_fr: String? = null,
    @SerializedName("name_en") val name_ingredient_en: String? = null,
) : Serializable

data class Prices(
    @SerializedName("price") val price: Float? = null,
) : Serializable

