package com.example.myanimelist

import Anime
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    companion object {
        const val key_anime = "key_anime"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val dataAnime = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Anime>(key_anime)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Anime>(key_anime)
        }

        if (dataAnime != null) {
            val tvDetailName = findViewById<TextView>(R.id.tv_detail_name)
            val tvDetailDescription = findViewById<TextView>(R.id.tv_detail_description)
            val ivDetailPhoto = findViewById<ImageView>(R.id.iv_detail_photo)

            tvDetailName.text = dataAnime.name
            tvDetailDescription.text = dataAnime.description

            // Load gambar menggunakan Glide
            Glide.with(this)
                .load(dataAnime.photo)
                .into(ivDetailPhoto)
        }
    }
}