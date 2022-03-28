package fr.isen.cremades.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.isen.cremades.androiderestaurant.databinding.ActivityMenuBinding
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONObject

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val  ligneMenu = creerMesLigne()
        val title = intent.getStringExtra(HomeActivity.CATEGORY_KEY) ?: ""
        binding.categoryTitle.text = title
        binding.menuList.layoutManager = LinearLayoutManager(this)
        binding.menuList.adapter = MenuAdapter(arrayListOf()) {}

        requeteUrlItemList(title)
        /*{
            //OBJECTIF : Quand on clique on est rediriger vers une autre activity (autre page) avec le détail du plat
            Toast.makeText(this,"Vous avez sélectionné $it",Toast.LENGTH_SHORT).show()
        }*/
    }

    fun requeteUrlItemList(categoryTitle: String) {
        val volleyQueue = Volley.newRequestQueue(this@MenuActivity)
        var reqParam = JSONObject("{\"id_shop\":1}")
        val urlMenuList = "http://test.api.catering.bluecodegames.com/menu"

        // Volley post request with parameters
        val request = JsonObjectRequest(Request.Method.POST,urlMenuList,reqParam,
            { response ->
                // Process the json
                try {
                    Log.d("CA00", "Response: $response")
                    //parseRequete(response)
                    val menu = Gson().fromJson(response.toString(), DataMenu::class.java)
                    val items = menu.data.firstOrNull{it.name_fr==categoryTitle}?.items ?: arrayListOf()

                    val adapter = MenuAdapter(items) {
                        val intent = Intent(this@MenuActivity, DetailsActivity::class.java)
                        intent.putExtra(DETAILS_KEY, it)
                        startActivity(intent)
                    }
                    binding.menuList.adapter = adapter

                }catch (e:Exception){
                    Log.d("CA01", "Exception: $e")
                }

            }, {
                // Error in request
                Log.d("CA02", "Volley error: $it")
            })
        volleyQueue.add(request)
    }

    companion object {
        const val DETAILS_KEY = "details"
    }
}


