package com.shivam.youtubelikelayout.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shivam.youtubelikelayout.databinding.FragmentTrendingBinding

class TrendingFragment: Fragment() {

    private var binding: FragmentTrendingBinding? = null
    private val _binding get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrendingBinding.inflate(inflater,container,false)

        return _binding.root
    }
}