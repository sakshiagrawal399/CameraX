package com.android.camerax.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.camerax.R
import com.android.camerax.databinding.ListAlbumBinding

class AlbumAdapter(private val clickListener: (String) -> Unit) :
    RecyclerView.Adapter<ViewHolder>() {
    private val tags = ArrayList<String>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListAlbumBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_album, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tags[position], clickListener)
    }

    override fun getItemCount(): Int {
        return tags.size

    }

    fun setList(subscriberList: List<String>) {
        tags.clear()
        tags.addAll(subscriberList)
    }

}

class ViewHolder(val binding: ListAlbumBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(s: String, clickListener: (String) -> Unit) {
        binding.textView.text = "Album Id: " + s
        binding.linear.setOnClickListener {
            clickListener(s)
        }
    }
}