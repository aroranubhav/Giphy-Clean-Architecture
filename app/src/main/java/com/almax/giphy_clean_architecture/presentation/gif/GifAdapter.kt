package com.almax.giphy_clean_architecture.presentation.gif

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.almax.giphy_clean_architecture.domain.model.GifData
import com.almax.giphy_clean_architecture.databinding.ItemGifBinding
import com.bumptech.glide.Glide

class GifAdapter(
    private val gifs: ArrayList<GifData>
) : RecyclerView.Adapter<GifAdapter.GifViewHolder>() {

    inner class GifViewHolder(
        private val binding: ItemGifBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(gif: GifData) {
            binding.apply {
                Glide
                    .with(binding.root.context)
                    .load(gif.images.downsized.url)
                    .into(ivGif)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        return GifViewHolder(
            ItemGifBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun getItemCount(): Int =
        gifs.size

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        holder.onBind(gifs[position])
    }

    fun setData(gifs: List<GifData>) {
        this.gifs.clear()
        this.gifs.addAll(gifs)
        notifyDataSetChanged()
    }
}