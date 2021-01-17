package com.android.camerax.images

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.camerax.R
import com.android.camerax.MainActivityViewModel
import com.android.camerax.MainActivityViewModelFactory
import com.android.camerax.databinding.ActivityImagesBinding
import com.android.camerax.db.AlbumDatabase
import com.android.camerax.db.ImageRepository

class ImagesActivity : AppCompatActivity() {
    private lateinit var adapter: ImageAdapter
    private lateinit var binding: ActivityImagesBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_images)
        val tag = intent.getStringExtra("tag")!!

        val dao = AlbumDatabase.getInstance(application).imageDao
        val repository = ImageRepository(dao,tag)
        val factory = MainActivityViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(MainActivityViewModel::class.java)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ImageAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.images.observe(this, {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })


    }
}