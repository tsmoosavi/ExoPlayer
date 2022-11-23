package com.example.exoplayer

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView
import com.google.common.collect.ImmutableList


class MainActivity : AppCompatActivity() {
    private lateinit var player: ExoPlayer
    private lateinit var playerView: PlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var aB = findViewById<Button>(R.id.actionButton)
        setupPlayer()
        addMP3()
        addMP4Files()
        player.play()
        aB.setOnClickListener {
//            val mediaItem = MediaItem.fromUri("https://storage.googleapis.com/exoplayer-test-media-0/play.mp3")
//            player.setMediaItem(mediaItem)
            player.setPlaybackSpeed(2f)
            player.prepare()


            Log.d("dvdsfs","speed")
        }


        // restore playstate on Rotation
        if (savedInstanceState != null) {
            if (savedInstanceState.getInt("mediaItem") != 0) {
                val restoredMediaItem = savedInstanceState.getInt("mediaItem")
                val seekTime = savedInstanceState.getLong("SeekTime")
                player.seekTo(restoredMediaItem, seekTime)
//                player.play()
            }
        }
    }

    private fun speed() {
        var aB = findViewById<Button>(R.id.actionButton)
        aB.setOnClickListener {
            player.setPlaybackSpeed(2f)
        player.prepare()}
        Log.d("dvdsfs","speed")

    }

    private fun addMP4Files() {
        val mediaItem = MediaItem.fromUri("https://caspian1.cdn.asset.aparat.com/aparat-video/d22d493d6bc1d0757a7770c6cf2bcd4643501631-144p.mp4?wmsAuthSign=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbiI6IjE3YjllODRiNzFmZDY1ODI0MDQ3NDJmZjFjMTFkMWEwIiwiZXhwIjoxNjUyMDk5NzgxLCJpc3MiOiJTYWJhIElkZWEgR1NJRyJ9.i-FCwiR0N9q_4YiCVCIsn8cEsb18zTf6lq0jWTaF_Jk")
       //"https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4"
        val newItems: List<MediaItem> = ImmutableList.of(
            mediaItem
        )
        player.addMediaItems(newItems)
        player.prepare()
    }

    private fun setupPlayer() {
        player = ExoPlayer.Builder(this).build()
        playerView = findViewById(R.id.video_view)
        playerView.player = player
//        player.addListener(this)
        playerView.useController = false
        Log.d("dvdsfs","speed")


    }

    private fun addMP3() {
        // Build the media item.
        val mediaItem = MediaItem.fromUri("https://storage.googleapis.com/exoplayer-test-media-0/play.mp3")
        player.setMediaItem(mediaItem)
        // Set the media item to be played.
//        player.setMediaItem(mediaItem)
        // Prepare the player.
        player.prepare()
//        player.play()
//        player.setPlaybackSpeed(2f)
    }


    override fun onStop() {
        super.onStop()
        player.release()
    }

    override fun onResume() {
        super.onResume()
        setupPlayer()
        addMP3()
        addMP4Files()
    }

    // handle loading


    //get Title from metadata

    // save details if Activity is destroyed
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState: " + player.currentPosition)
        // current play position
        outState.putLong("SeekTime", player.currentPosition)
        // current mediaItem
        outState.putInt("mediaItem", player.currentMediaItemIndex)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onSaveInstanceState: " + player.currentPosition)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}