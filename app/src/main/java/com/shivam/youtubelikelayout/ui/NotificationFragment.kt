package com.shivam.youtubelikelayout.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shivam.youtubelikelayout.databinding.FragmentNotificationBinding

class NotificationFragment: Fragment() {
    private var binding: FragmentNotificationBinding? = null
    private val _binding get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationBinding.inflate(inflater,container,false)

        return _binding.root
    }
}