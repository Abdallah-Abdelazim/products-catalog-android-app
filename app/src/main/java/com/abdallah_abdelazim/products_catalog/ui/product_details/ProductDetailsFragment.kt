package com.abdallah_abdelazim.products_catalog.ui.product_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.abdallah_abdelazim.products_catalog.R
import com.abdallah_abdelazim.products_catalog.databinding.FragmentProductDetailsBinding
import com.abdallah_abdelazim.products_catalog.ui.products_list.ProductUiModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ProductDetailsFragment : Fragment() {

    private val viewModel: ProductDetailsViewModel by viewModel()

    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var product: ProductUiModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args: ProductDetailsFragmentArgs =
            ProductDetailsFragmentArgs.fromBundle(requireArguments())
        product = args.product
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_product_details, container, false
        )
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.product = product
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}