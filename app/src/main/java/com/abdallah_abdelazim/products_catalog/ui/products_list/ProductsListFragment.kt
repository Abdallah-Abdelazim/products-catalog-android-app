package com.abdallah_abdelazim.products_catalog.ui.products_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.abdallah_abdelazim.products_catalog.R
import com.abdallah_abdelazim.products_catalog.databinding.FragmentProductsListBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ProductsListFragment : Fragment() {

    private val viewModel: ProductsListViewModel by viewModel()

    private var _binding: FragmentProductsListBinding? = null
    private val binding get() = _binding!!

    private lateinit var productsAdapter: ProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_products_list, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupProductsRv()
        observeData()
    }

    private fun setupProductsRv() {
        productsAdapter = ProductsAdapter(::onProductClicked)
        binding.rvProducts.setHasFixedSize(true)
        binding.rvProducts.adapter = productsAdapter
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collectLatest { uiState ->
                    renderUiState(uiState)
                }
        }

    }

    private fun onProductClicked(product: ProductUiModel) {
        findNavController().navigate(
            ProductsListFragmentDirections.actionProductsListFragmentToProductDetailsFragment()
        )
    }

    private fun renderUiState(uiState: ProductsListUiState) {
        binding.uiState = uiState
        productsAdapter.submitList(uiState.products)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}