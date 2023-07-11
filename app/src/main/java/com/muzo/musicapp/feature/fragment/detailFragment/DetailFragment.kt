package com.muzo.musicapp.feature.fragment.detailFragment

import android.annotation.SuppressLint
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.HandlerCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.muzo.musicapp.R
import com.muzo.musicapp.databinding.FragmentDetailBinding
import com.muzo.musicapp.feature.fragment.BaseFragment
import com.muzo.musicapp.feature.fragment.section2.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch



@AndroidEntryPoint
class DetailFragment : BaseFragment() {
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: HomeViewModel by viewModels()
    private var mediaPlayer: MediaPlayer? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)


        heartClicked()
        loadData()
        clickListener()
        return binding.root
    }

    private fun loadData() {
        lifecycleScope.launch {
            viewModel._uiState.collect { uiState ->

                when {
                    uiState.loading -> {
                        binding.apply {
                            progressBar.visibility = View.VISIBLE
                            trackName.visibility = View.GONE
                            signerName.visibility = View.GONE
                            collectionName.visibility = View.GONE
                            trackPrice.visibility = View.GONE
                            releaseDate.visibility = View.GONE
                        }
                    }

                    uiState.musicList != null -> {

                        binding.apply {
                            getData()
                            progressBar.visibility = View.GONE
                            trackName.visibility = View.VISIBLE
                            signerName.visibility = View.VISIBLE
                            collectionName.visibility = View.VISIBLE
                            trackPrice.visibility = View.VISIBLE
                            releaseDate.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }

    }

    private fun getData() {
        val Ivurl = arguments?.getString("artworkUrl100")
        val trackName = arguments?.getString("trackName")
        val signerName = arguments?.getString("artistName")
        val collectionName = arguments?.getString("collectionName")
        val trackPrice = arguments?.getString("trackPrice")
        val releaseDate = arguments?.getString("releaseDate")


//        binding.IvSigner.load(Ivurl) {
//            crossfade(true)
//            crossfade(1000)
//        }
        binding.trackName.text = trackName
        binding.signerName.text = signerName
        binding.collectionName.text = collectionName
        binding.trackPrice.text = trackPrice
        binding.releaseDate.text = releaseDate
    }

    private fun playMusic() {
        val trackUrl = arguments?.getString("trackUrl")

        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer()
            mediaPlayer?.setAudioAttributes(
                AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build()
            )
            mediaPlayer?.setDataSource(trackUrl)
            mediaPlayer?.prepare()
        }

        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.pause()
        } else {
            mediaPlayer?.start()
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) mediaPlayer?.seekTo(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        seekBar()
    }


    private fun pauseMusic() {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.pause()
        }
    }

    private fun stopMusic() {
        mediaPlayer?.apply {
            stop()
            reset()
            release()
        }
        mediaPlayer = null
    }



    private fun clickListener() {
        binding.play.setOnClickListener { playMusic() }
        binding.pause.setOnClickListener { pauseMusic() }
        binding.stop.setOnClickListener { stopMusic() }
    }

    private fun seekBar() {
        binding.seekBar.max = mediaPlayer!!.duration

        val handler = HandlerCompat.createAsync(requireContext().mainLooper)
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    binding.seekBar.progress = mediaPlayer!!.currentPosition
                    handler.postDelayed(this, 1000)
                } catch (e: Exception) {
                    binding.seekBar.progress = 0
                }
            }
        }, 1000)
    }
    override fun onPause() {
        super.onPause()
        pauseMusic()
        binding.seekBar.setProgress(0,true)
    }

    private fun heartClicked() {
        val emptyHeartDrawableName = "ic_fav"
        val filledHeartDrawableName = "ic_full_fav"

        binding.heartIv.tag = emptyHeartDrawableName

        binding.heartIv.setOnClickListener {
            val currentDrawableName = binding.heartIv.tag as? String

            if (currentDrawableName == emptyHeartDrawableName) {
                binding.heartIv.setImageResource(resources.getIdentifier(filledHeartDrawableName, "drawable", requireContext().packageName))
                binding.heartIv.setTag(filledHeartDrawableName)

                toastMessage("This song added fav")


            } else {
                // Boş kalp görselini yükleyin
                binding.heartIv.setImageResource(resources.getIdentifier(emptyHeartDrawableName, "drawable", requireContext().packageName))
                binding.heartIv.setTag(emptyHeartDrawableName)

                toastMessage("This song distract fav")
            }
        }
    }
    private fun toastMessage(message:String){

        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()

    }






}