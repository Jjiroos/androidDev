package fr.isen.cremades.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // get reference to button
        val choixEntree = findViewById<TextView>(R.id.choixEntree)
        // set on-click listener
        choixEntree.setOnClickListener {
            Toast.makeText(this, "Soon => liste des entrées disponible", Toast.LENGTH_SHORT).show()
        }

        val choixPlat = findViewById<TextView>(R.id.choixPlat)
        // set on-click listener
        choixPlat.setOnClickListener {
            Toast.makeText(this, "Soon => liste des plats disponible", Toast.LENGTH_SHORT).show()
        }

        val choixDessert = findViewById<TextView>(R.id.choixDessert)
        // set on-click listener
        choixDessert.setOnClickListener {
            Toast.makeText(this, "Soon => liste des desserts disponible", Toast.LENGTH_SHORT).show()
        }
    }
}