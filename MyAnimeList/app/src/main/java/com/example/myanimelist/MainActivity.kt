package com.example.myanimelist

import Anime
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvAnime: RecyclerView
    private val list = ArrayList<Anime>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvAnime = findViewById(R.id.rv_Anime)
        rvAnime.setHasFixedSize(true)

        list.addAll(getListAnime())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                val about = Intent(this@MainActivity, About::class.java)
                startActivity(about)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListAnime(): ArrayList<Anime> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listAnime = ArrayList<Anime>()
        for (i in dataName.indices) {
            val hero = Anime(dataName[i], dataDescription[i], dataPhoto[i])
            listAnime.add(hero)
        }
        return listAnime
    }

    private fun showRecyclerList() {
        rvAnime.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListAdapter(list)
        rvAnime.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : ListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Anime) {
                showSelectedHero(data)
            }
        })
    }

    private fun showSelectedHero(anime: Anime) {
        Toast.makeText(this, "Kamu memilih " + anime.name, Toast.LENGTH_SHORT).show()
    }
}