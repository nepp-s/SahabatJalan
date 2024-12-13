package com.example.mycapstone.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.mycapstone.R
import com.example.mycapstone.database.AppDatabase
import com.example.mycapstone.database.Wishlist
import com.example.mycapstone.database.WishlistDao
import com.example.mycapstone.databinding.ActivityDetailDestinationBinding
import com.example.mycapstone.response.Data
import kotlinx.coroutines.launch

class DetailDestinationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailDestinationBinding
    private lateinit var db: AppDatabase
    private lateinit var favoriteDao: WishlistDao
    private lateinit var mainViewModel: DetailViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailDestinationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = obtainViewModel(this@DetailDestinationActivity)

        val userId = intent.getIntExtra(EXTRA_ID, 0)
        mainViewModel.findEventActive(userId)

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "app-database").build()
        favoriteDao = db.destinationDao()


        mainViewModel.detailEvents.observe(this) { response ->
            if (response != null) {
                setDetailData(response)

                mainViewModel.findUsername(response.name!!).observe(this@DetailDestinationActivity) { username ->
                    val isFavorite = username != null
                    updateFavoriteButtonIcon(isFavorite)
                    binding.btnFavorite.setOnClickListener {
                        lifecycleScope.launch {
                            if (isFavorite) {
                                mainViewModel.delete(Wishlist(response.id!!, response.name, response.location!!, response.image!!))
                            } else {
                                mainViewModel.insert(Wishlist(response.id!!, response.name, response.location!!, response.image!!))
                            }
                        }
                    }
                }
            }
        }
    }

    private fun updateFavoriteButtonIcon(isFavorite: Boolean) {
        val iconRes = if (isFavorite) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24
        binding.btnFavorite.setImageDrawable(ContextCompat.getDrawable(this, iconRes))
    }

    private fun setDetailData(response: Data) {
        Glide.with(binding.imgHeaderBackground)
            .load(response?.image)
            .into(binding.imgHeaderBackground)
        binding.namePlaceTv.text = response.name
        binding.tvDescriptionFull.text = response.description
        binding.locationTv.text = response.location
    }

    private fun obtainViewModel(activity: AppCompatActivity): DetailViewModel{
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(DetailViewModel::class.java)
    }
    companion object {
        var EXTRA_ID = "USERID"
    }
}