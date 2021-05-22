package com.shivam.youtubelikelayout.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shivam.youtubelikelayout.databinding.ListItemSmallBinding

class VideoPlayerAdapter(private val listener: VideoItemInterface,private val list:List<Video>) : RecyclerView.Adapter<VideoPlayerAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ListItemSmallBinding, listener: VideoItemInterface) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.run {
                this.listener = listener
            }
        }
        fun bind(video: Video){
            binding.run {
                this.video = video
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ListItemSmallBinding.inflate(LayoutInflater.from(parent.context),parent,false),listener)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}