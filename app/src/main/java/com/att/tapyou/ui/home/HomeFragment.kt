package com.att.tapyou.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.att.tapyou.R
import com.att.tapyou.databinding.FragmentHomeBinding
import com.att.tapyou.utils.binding.FragmentBinding
import com.att.tapyou.utils.extensions.Extensions.collectIt
import com.att.tapyou.utils.logs.logD
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by FragmentBinding(FragmentHomeBinding::bind)
    private val viewModel by viewModel<HomeViewModel>()
    private var linearLayoutManager: LinearLayoutManager? = null
    private var videosAdapter: VideosAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewCreated()
        setRecyclerView()
        observeViewModels()
    }

    private fun setRecyclerView() {
        binding.homeRecyclerView.apply {
            layoutManager = LinearLayoutManager(context).apply { linearLayoutManager = this }
            adapter = VideosAdapter().apply { videosAdapter = this }
        }
    }

    private fun observeViewModels() {
        viewModel.apply {
            videoIdsList.collectIt(viewLifecycleOwner) {
                logD("List: $it")
                videosAdapter?.submitList(it)
            }
        }
    }
}