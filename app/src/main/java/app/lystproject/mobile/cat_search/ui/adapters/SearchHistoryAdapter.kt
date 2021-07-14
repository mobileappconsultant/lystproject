package app.lystproject.mobile.cat_search.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.lystproject.mobile.R
import app.lystproject.mobile.cat_search.model.CatModel
import app.lystproject.mobile.core.extension.inflate
import app.lystproject.mobile.databinding.SearchHistoryBinding

typealias RecentSearchClickListener = (CatModel) -> Unit

class SearchHistoryAdapter(private val onClick: RecentSearchClickListener) :
    ListAdapter<CatModel, SearchHistoryAdapter.SearchHistoryViewHolder>(diffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryViewHolder {
        return SearchHistoryViewHolder(SearchHistoryBinding.bind(parent.inflate(R.layout.search_history)))
    }

    override fun onBindViewHolder(holder: SearchHistoryViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

    class SearchHistoryViewHolder(private val binding: SearchHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cat: CatModel, clickListener: RecentSearchClickListener) {
            binding.name.text = cat.name
            binding.name.setOnClickListener {
                clickListener(cat)
            }
        }
    }

    companion object {
        val diffUtilCallback: DiffUtil.ItemCallback<CatModel>
            get() = object : DiffUtil.ItemCallback<CatModel>() {
                override fun areItemsTheSame(
                    oldItem: CatModel,
                    newItem: CatModel
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: CatModel,
                    newItem: CatModel
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}
