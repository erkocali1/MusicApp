package com.muzo.musicapp.feature.fragment.detailFragment

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.os.HandlerCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.muzo.musicapp.core.data.local.room.modelclass.FavLocalData
import com.muzo.musicapp.databinding.FragmentDetailBinding
import com.muzo.musicapp.feature.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailFragment : BaseFragment() {
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    private var mediaPlayer: MediaPlayer? = null
    private var isFav: Boolean = false
    private var list: List<FavLocalData>? = null



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
                            card.visibility = View.GONE
                            constant1.visibility = View.GONE
                            constant2.visibility = View.GONE
                            constant3.visibility = View.GONE
                        }
                    }
                    uiState.favLocalData !=null->{

                        list=uiState.favLocalData
                        isFavCheck()


                        binding.apply {
                            getData()
                            progressBar.visibility = View.GONE
                            trackName.visibility = View.VISIBLE
                            signerName.visibility = View.VISIBLE
                            collectionName.visibility = View.VISIBLE
                            trackPrice.visibility = View.VISIBLE
                            releaseDate.visibility = View.VISIBLE
                            card.visibility = View.VISIBLE
                            constant1.visibility = View.VISIBLE
                            constant2.visibility = View.VISIBLE
                            constant3.visibility = View.VISIBLE
                        }
                    }

                    else -> {


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
        binding.seekBar.setProgress(0, true)
    }

    private fun heartClicked() {
        val emptyHeartDrawableName = "ic_fav"
        val filledHeartDrawableName = "ic_full_fav"

        binding.heartIv.tag = emptyHeartDrawableName

        binding.heartIv.setOnClickListener {
            val currentDrawableName = binding.heartIv.tag as? String

            if (currentDrawableName == emptyHeartDrawableName) {
                binding.heartIv.setImageResource(
                    resources.getIdentifier(
                        filledHeartDrawableName, "drawable", requireContext().packageName
                    )
                )
                binding.heartIv.setTag(filledHeartDrawableName)

                toastMessage("This song added fav")
                addFav()


            } else {
                // Boş kalp görselini yükleyin
                binding.heartIv.setImageResource(
                    resources.getIdentifier(
                        emptyHeartDrawableName, "drawable", requireContext().packageName
                    )
                )
                binding.heartIv.setTag(emptyHeartDrawableName)
                distractFav()
                toastMessage("This song distract fav")
            }
        }
    }

    private fun toastMessage(message: String) {

        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

    }

    private fun addFav() {

        val listFav = arrayListOf<FavLocalData>()
        val artworkUrl100 = arguments?.getString("artworkUrl100")
        val trackUrl = arguments?.getString("trackUrl")
        val trackName = arguments?.getString("trackName")
        val signerName = arguments?.getString("artistName")
        val collectionName = arguments?.getString("collectionName")
        val trackPrice = arguments?.getString("trackPrice")
        val releaseDate = arguments?.getString("releaseDate")


        val favLocalData = FavLocalData(

            artistName = signerName!!,
            trackName = trackName!!,
            releaseDate = releaseDate!!,
            trackPrice = trackPrice!!,
            artworkUrl100 = artworkUrl100!!,
            collectionName = collectionName!!,
            previewUrl = trackUrl!!,
            uid = 0,
        )

        listFav.add(favLocalData)
        lifecycleScope.launch {
            viewModel.saveFavList(listFav as List<FavLocalData>)
        }


    }

    private fun distractFav() {

        val trackName = arguments?.getString("trackName")


        lifecycleScope.launch {
            viewModel.deleteFavList(trackName!!)
        }
    }

    private fun isFavCheck() {
        val trackName = arguments?.getString("trackName")

        // Check if the trackName exists in the list of favLocalData
        val isFav = list!!.any { it.trackName == trackName }

        // Update the heart icon based on the isFav status
        if (isFav) {
            binding.heartIv.setImageResource(
                resources.getIdentifier(
                    "ic_full_fav", "drawable", requireContext().packageName
                )
            )
            binding.heartIv.tag = "ic_full_fav"
        } else {
            binding.heartIv.setImageResource(
                resources.getIdentifier(
                    "ic_fav", "drawable", requireContext().packageName
                )
            )
            binding.heartIv.tag = "ic_fav"
        }
    }



}