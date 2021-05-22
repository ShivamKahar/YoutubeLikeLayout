package com.shivam.youtubelikelayout.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.core.content.res.use
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.shivam.youtubelikelayout.MainActivity
import com.shivam.youtubelikelayout.R
import com.shivam.youtubelikelayout.databinding.FragmentVideoBinding
import com.shivam.youtubelikelayout.ui.adapters.StaticData
import com.shivam.youtubelikelayout.ui.adapters.Video
import com.shivam.youtubelikelayout.ui.adapters.VideoItemInterface
import com.shivam.youtubelikelayout.ui.adapters.VideoPlayerAdapter
import java.lang.StringBuilder
import kotlin.math.abs
import kotlin.math.round

private const val TAG = "VideoFragment"

class VideoFragment : Fragment(), VideoItemInterface {

    private var binding: FragmentVideoBinding? = null
    val _binding get() = binding!!

    private var intProgress: Float = 0f

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideoBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mActivity = activity as MainActivity

        arguments?.let {
            val title = it.getString("title")
            val imgUrl = it.getString("img")

            Glide.with(mActivity).load(imgUrl).into(_binding.imageView2)
            _binding.textView.text = title
        }

        val adapter = VideoPlayerAdapter(this,StaticData.getAllData())
        _binding.recyclerView.apply {
            this.layoutManager = LinearLayoutManager(mActivity)
            this.adapter = adapter
        }
//        val width = StringBuilder().append("${_binding.imageView2.measuredWidth}\n${_binding.videoContainer.measuredWidth}")
//        Log.d(TAG, "onViewCreated: $width")

        mActivity.binding.mainMotionLayout.transitionToEnd()
        _binding.mainVideoMotionLayout.addTransitionListener(object :
            MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
            }
            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                if (_binding.mainVideoMotionLayout.endState == R.id.end) {
                    intProgress = 1 - p3
                    mActivity.binding.mainMotionLayout.progress = abs(intProgress)
                }
            }
            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                when(_binding.mainVideoMotionLayout.currentState){
                    R.id.end -> mActivity.binding.mainMotionLayout.progress = round(intProgress)
                    R.id.finish -> destroyCurrentFragment()
                }
            }
            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
            }
        })

        _binding.imageView.setOnClickListener {
            destroyCurrentFragment()
        }
    }

    private fun destroyCurrentFragment() {
        val frag = requireActivity().supportFragmentManager.findFragmentByTag("Video")
        requireActivity().supportFragmentManager.beginTransaction().remove(frag!!).commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onItemClick(view: View, video: Video) {
        //To Be Implemented
    }
}