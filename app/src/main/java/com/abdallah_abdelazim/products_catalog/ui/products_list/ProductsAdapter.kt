package com.abdallah_abdelazim.products_catalog.ui.products_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.abdallah_abdelazim.products_catalog.databinding.ItemProductBinding
import com.abdallah_abdelazim.products_catalog.ui.products_list.ProductsAdapter.ProductViewHolder

class ProductsAdapter(private val onItemClick: (ProductUiModel) -> Unit) :
    ListAdapter<ProductUiModel, ProductViewHolder>(PRODUCT_DIFF_CALLBACK) {

    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        if (!::inflater.isInitialized) inflater = LayoutInflater.from(parent.context)

        val binding = ItemProductBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(
            getItem(position),
            onItemClick
        )
    }

    class ProductViewHolder(private val binding: ItemProductBinding) : ViewHolder(binding.root) {

        fun bind(
            productUiModel: ProductUiModel,
            onItemClick: (ProductUiModel) -> Unit
        ) {
            binding.product = productUiModel
            binding.root.setOnClickListener {
                onItemClick(productUiModel)
            }
        }
    }

    companion object {

        val PRODUCT_DIFF_CALLBACK: DiffUtil.ItemCallback<ProductUiModel> =
            object : DiffUtil.ItemCallback<ProductUiModel>() {
                override fun areItemsTheSame(
                    oldItem: ProductUiModel,
                    newItem: ProductUiModel
                ) = oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: ProductUiModel,
                    newItem: ProductUiModel
                ) = oldItem == newItem
            }

    }

}