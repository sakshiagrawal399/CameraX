package com.android.camerax.images

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.camerax.R
import com.android.camerax.databinding.ListItemBinding
import com.android.camerax.db.Image

class ImageAdapter : RecyclerView.Adapter<MyViewHolder>() {
    private val list = ArrayList<Image>()

    fun setList(images: List<Image>) {
        list.clear()
        list.addAll(images)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.list_item,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }
}


class MyViewHolder(val binding: ListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(image: Image) {
        val uri = Uri.parse(image.imagePath)
        binding.imageView.setImageURI(uri)

    }

}