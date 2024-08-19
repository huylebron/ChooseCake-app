package com.example.choosecakes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.choosecakes.databinding.ActivityMainBinding

data class Dessert (
    val nameDessert: String,
    val priceDessert: Float,
    var limitAmount: Int,
    val image: Int = R.drawable.img_1
)

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var currentDessertIndex = 0
    private var totalItems = 0
    private var totalPrice = 0.0f

    private val desserts = listOf(
        Dessert("chiffon cake", 2.0f, 10, R.drawable.img_1),
        Dessert("donut cake", 3.5f, 8, R.drawable.img_2),
        Dessert("ice-creams cake", 4.0f, 5, R.drawable.img_3)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayDessert(currentDessertIndex)

        binding.imgCake.setOnClickListener {
            val dessert = desserts[currentDessertIndex]
            if (dessert.limitAmount > 0) {
                totalItems++
                totalPrice += dessert.priceDessert
                dessert.limitAmount--

                binding.tvSummary.text = "you have $totalItems items\n total: $${String.format("%.2f", totalPrice)}"
                binding.tvCakeInfo.text = "${dessert.nameDessert} ${dessert.limitAmount} psc"
            }
        }

        binding.arrowIcon.setOnClickListener {
            currentDessertIndex = (currentDessertIndex + 1) % desserts.size
            displayDessert(currentDessertIndex)
        }
    }

    private fun displayDessert(currentDessertIndex: Int) {
        val dessert = desserts[currentDessertIndex]
        binding.imgCake.setImageResource(dessert.image)
        binding.tvCakeInfo.text = "${dessert.nameDessert} ${dessert.limitAmount} psc"
        binding.tvPrice.text = "price $${dessert.priceDessert}"
    }
}