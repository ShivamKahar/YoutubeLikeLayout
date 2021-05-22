package com.shivam.youtubelikelayout.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import com.google.android.material.transition.MaterialContainerTransform
import com.shivam.youtubelikelayout.MainActivity
import com.shivam.youtubelikelayout.R
import com.shivam.youtubelikelayout.databinding.FragmentHomeBinding
import com.shivam.youtubelikelayout.ui.adapters.StaticData
import com.shivam.youtubelikelayout.ui.adapters.Video
import com.shivam.youtubelikelayout.ui.adapters.VideoAdapter
import com.shivam.youtubelikelayout.ui.adapters.VideoItemInterface

class HomeFragment : Fragment(),VideoItemInterface {

    private var binding: FragmentHomeBinding? = null
    private val _binding get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mActivity = activity as MainActivity

        val adapter = VideoAdapter(this,StaticData.getAllData())
        _binding.videoListContainer.apply {
            this.layoutManager = LinearLayoutManager(mActivity)
            this.adapter = adapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onItemClick(view: View, video: Video) {

        val bundle = Bundle().also {
            it.putString("img",video.img)
            it.putString("title",video.title)
        }

        val fragment = VideoFragment()
        fragment.arguments = bundle

        val transform = MaterialContainerTransform().apply {
            startView = view
            endView = requireActivity().findViewById(R.id.video_container)
            addTarget(view.id)
            scrimColor = Color.TRANSPARENT
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
        TransitionManager.beginDelayedTransition(_binding.root,transform)

        requireActivity().supportFragmentManager
            .beginTransaction()
            .addSharedElement(view,getString(R.string.email_card_detail_transition_name))
            .replace(R.id.video_container,fragment,"Video").commit()
    }
}