package com.tina.lollipopcodingtest.newslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tina.lollipopcodingtest.data.News
import com.tina.lollipopcodingtest.databinding.ItemLargeImageBinding
import com.tina.lollipopcodingtest.databinding.ItemSmallImageBinding

private const val VIEW_TYPE_SMALL = 0
private const val VIEW_TYPE_LARGE = 1

class NewsListAdapter  (private val viewModel: NewsListViewModel) : ListAdapter<News, RecyclerView.ViewHolder>(DiffCallback) {

    class SmallImageViewHolder(private var binding: ItemSmallImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News) {
            binding.news = news
            binding.executePendingBindings()
        }
    }

    class LargeImageViewHolder(private var binding: ItemLargeImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News) {
            binding.news = news
            binding.executePendingBindings()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_SMALL -> SmallImageViewHolder(
                ItemSmallImageBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            VIEW_TYPE_LARGE -> LargeImageViewHolder(
                ItemLargeImageBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            when (holder) {
                is SmallImageViewHolder -> { holder.bind(item) }
                is LargeImageViewHolder -> { holder.bind(item) }
            }
        }

        if (itemCount - position == 1 ) {
            viewModel.getNewsList(item.name)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(position % 2){
            0 -> VIEW_TYPE_SMALL
            else -> VIEW_TYPE_LARGE
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.created_utc == newItem.created_utc
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.id == newItem.id && oldItem.name == newItem.name
        }
    }
}