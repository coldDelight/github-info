package com.colddelight.github_info.presentaion

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.colddelight.domain.model.DomainRepo
import com.colddelight.github_info.databinding.ItemRecyclerRepoBinding

class RepoRecyclerAdapter : RecyclerView.Adapter<RepoRecyclerAdapter.ViewHolder>() {
    private var items: List<DomainRepo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecyclerRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setItem(items[position])
    }
    inner class ViewHolder(private val binding: ItemRecyclerRepoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setItem(item: DomainRepo){
            binding.tvRepoName.text =  item.name
            binding.tvLang.text =  item.language
        }
    }
    override fun getItemViewType(position: Int): Int {
        return position
    }
    @SuppressLint("NotifyDataSetChanged")
    internal fun setData(newItems: List<DomainRepo>) {
        this.items = newItems
        notifyDataSetChanged()
    }

    override fun getItemCount():Int{
        return items.size
    }
}