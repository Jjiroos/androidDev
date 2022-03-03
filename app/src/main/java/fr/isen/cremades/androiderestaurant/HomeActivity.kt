package fr.isen.cremades.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class HomeActivity : AppCompatActivity() {
    companion object{
        const val ORDER_KEY="order";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // init the button references
        val choixEntree = findViewById<TextView>(R.id.choixEntree)
        val choixPlat = findViewById<TextView>(R.id.choixPlat)
        val choixDessert = findViewById<TextView>(R.id.choixDessert)

        // set on-click listener for
        choixEntree.setOnClickListener {
            displayOrder(getString(R.string.choixEntree))
        }


        // set on-click listener
        choixPlat.setOnClickListener {
            displayOrder(getString(R.string.choixPlat))
        }


        // set on-click listener
        choixDessert.setOnClickListener {
            displayOrder(getString(R.string.choixDessert))
        }
    }

    override fun onStop() {
        super.onStop()
        Log.d("HomeActivity", "l'activité est arrétée")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.d("HomeActivity", "l'activité est détruite")
    }

    private fun displayOrder(message: CharSequence) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, OrderActivity::class.java)
        startActivity(intent)
    }
}