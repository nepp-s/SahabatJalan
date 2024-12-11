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
import com.example.mycapstone.R
import com.example.mycapstone.database.AppDatabase
import com.example.mycapstone.database.WishlistDao
import com.example.mycapstone.databinding.ActivityDetailDestinationBinding
import com.example.mycapstone.response.CategoryId
import kotlinx.coroutines.launch

class DetailDestinationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailDestinationBinding
    private lateinit var db: AppDatabase
    private lateinit var wishlistDao: WishlistDao
    private lateinit var mainViewModel: DetailViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_destination)
        binding = ActivityDetailDestinationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = obtainViewModel(this@DetailDestinationActivity)

        val userId = intent.getIntExtra(EXTRA_ID, 0)
        mainViewModel.findEventActive(userId)

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "app-database").build()
        wishlistDao = db.destinationDao()

        mainViewModel.detailEvents.observe(this) { response ->
            if (response != null) {
                setDetailData(response)

                mainViewModel.findUsername(response.name!!).observe(this@DetailDestinationActivity) { username ->
                    val isFavorite = username != null
                    updateFavoriteButtonIcon(isFavorite)
                    binding.buttonFavorite.setOnClickListener {
                        lifecycleScope.launch {
                            if (isFavorite) {
                                mainViewModel.delete(Favorite(response.id!!, response.name, response.mediaCover, response.description))
                            } else {
                                mainViewModel.insert(Favorite(response.id!!, response.name, response.mediaCover, response.description))
                            }
                        }
                    }
                }
            }
        }


        mainViewModel.isLoading.observe(this@DetailDestinationActivity) {
            showLoading(it)
        }
    }

    private fun updateFavoriteButtonIcon(isFavorite: Boolean) {
        val iconRes = if (isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border
        binding.buttonFavorite.setImageDrawable(ContextCompat.getDrawable(this, iconRes))
    }

    private fun setDetailData(response: CategoryId) {
        Glide.with(binding.itemIv)
            .load(response?.image)
            .into(binding.itemIv)
        binding.nameEventTv.text = "nama Event : ${response?.name}"
        binding.ownerEventTv.text = "owner event: ${response?.ownerName}"
        val quota = response?.registrants?.let { response.quota?.minus(it) }
        binding.quotaEventTv.text = "Sisa Kuota : ${quota.toString()}"
        binding.descriptionEventTv.text = response?.description
        binding.descriptionEventTv.text = response?.description?.let {
            HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
        binding.scheduleEventTv.text = "Jadwal Event ${response?.beginTime}"
        binding.buttonOpenEvent.setOnClickListener {
            val eventLink = response?.link
            if (!eventLink.isNullOrEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(eventLink))
                startActivity(intent)
            } else {
                binding.buttonOpenEvent.isEnabled = false
            }
        }

    }

    private fun obtainViewModel(activity: AppCompatActivity): DetailViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(DetailViewModel::class.java)
    }
    companion object {
        var EXTRA_ID = "USERID"
    }

    private fun showLoading(isLoading: Boolean) {
        binding.pro.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}