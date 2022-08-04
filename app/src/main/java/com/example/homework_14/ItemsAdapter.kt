package com.example.homework_14

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework_14.databinding.ListItemBinding

class ItemsAdapter : ListAdapter<Items.Content, ItemsAdapter.ItemViewHolder>(ItemCallBack()) {


    private class ItemCallBack: DiffUtil.ItemCallback<Items.Content>() {
        override fun areItemsTheSame(oldItem: Items.Content, newItem: Items.Content): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Items.Content,
            newItem: Items.Content
        ): Boolean {
            return oldItem == newItem
        }
    }

    inner class ItemViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = getItem(adapterPosition)
            binding.apply {
                Glide.with(this.imageView)
                    .load(item.cover)
                    .into(imageView)
                tvTitle.text = item.titleKA
                tvDesk.text = item.descriptionKA
                tvDate.text = item.publish_date
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
      holder.bind()
    }


}