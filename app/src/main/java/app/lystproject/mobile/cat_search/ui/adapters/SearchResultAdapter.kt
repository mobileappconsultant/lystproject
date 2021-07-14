package app.lystproject.mobile.cat_search.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.lystproject.mobile.R
import app.lystproject.mobile.cat_search.model.CatModel
import app.lystproject.mobile.cat_search.ui.adapters.SearchResultAdapter.SearchResultViewHolder
import app.lystproject.mobile.core.extension.inflate
import app.lystproject.mobile.databinding.SearchResultBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

typealias SearchResultClickListener = (CatModel) -> Unit

class SearchResultAdapter(private val onClick: SearchResultClickListener) :
    ListAdapter<CatModel, SearchResultViewHolder>(diffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        return SearchResultViewHolder(SearchResultBinding.bind(parent.inflate(R.layout.search_result)))
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

    class SearchResultViewHolder(private val binding: SearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cat: CatModel, onClick: SearchResultClickListener) {
            binding.txtCat.text = cat.name
            binding.root.setOnClickListener {
                onClick(cat)
            }
            binding.txtCountry.text = cat.origin
            Glide.with(binding.root)
                .load(cat.imageUrl)
                .thumbnail(0.2F)
                .apply(RequestOptions().centerCrop())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.imgCat)
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
