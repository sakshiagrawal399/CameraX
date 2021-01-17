package com.android.camerax.album

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.camerax.MainActivityViewModel
import com.android.camerax.MainActivityViewModelFactory
import com.android.camerax.R
import com.android.camerax.camera.CameraActivity
import com.android.camerax.databinding.ActivityMainBinding
import com.android.camerax.db.AlbumDatabase
import com.android.camerax.db.ImageRepository
import com.android.camerax.images.ImagesActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: AlbumAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dao = AlbumDatabase.getInstance(application).imageDao
        val repository = ImageRepository(dao, "")
        val factory = MainActivityViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(MainActivityViewModel::class.java)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AlbumAdapter { selectedItem: String -> listItemClick(selectedItem) }
        binding.recyclerView.adapter = adapter

        button.setOnClickListener {
            intent = Intent(applicationContext, CameraActivity::class.java)
            startActivity(intent)
        }

        viewModel.albums.observe(this, {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })

    }

    private fun listItemClick(tag: String) {
        intent = Intent(applicationContext, ImagesActivity::class.java)
        intent.putExtra("tag", tag)
        startActivity(intent)
    }
}