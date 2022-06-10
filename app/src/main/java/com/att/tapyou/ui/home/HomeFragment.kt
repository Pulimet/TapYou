package com.att.tapyou.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.att.tapyou.R
import com.att.tapyou.databinding.FragmentHomeBinding
import com.att.tapyou.utils.binding.FragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by FragmentBinding(FragmentHomeBinding::bind)
    private val viewModel by viewModel<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnTest.setOnClickListener {
            viewModel.testIt()
        }
    }
}