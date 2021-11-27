package com.insiderser.kpi.android.lab4

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.media3.common.C.CONTENT_TYPE_MUSIC
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import com.insiderser.kpi.android.lab4.databinding.PlayerActivityBinding

@SuppressLint("UnsafeOptInUsageError")
class PlayerActivity : AppCompatActivity() {

    private val binding: PlayerActivityBinding by lazy {
        PlayerActivityBinding.inflate(layoutInflater)
    }

    private val player: Player by lazy {
        ExoPlayer.Builder(this)
            .setAudioAttributes(
                androidx.media3.common.AudioAttributes.Builder().setContentType(CONTENT_TYPE_MUSIC).build(),
                /*handleAudioFocus=*/ true
            )
            .setHandleAudioBecomingNoisy(true)
            .build()
    }

    private lateinit var session: MediaSession

    private val openVideoLauncher = registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        loadVideo(uri)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.playerView.player = player
        session = MediaSession.Builder(this, player).build()

        openVideoLauncher.launch(arrayOf("video/*"))
    }

    private fun loadVideo(uri: Uri?) {
        if (uri != null) {
            player.setMediaItem(MediaItem.fromUri(uri))
            player.prepare()
            player.play()

            switchToImmersiveMode()
        } else {
            finish()
        }
    }

    private fun switchToImmersiveMode() {
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        WindowInsetsControllerCompat(window, binding.root).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    override fun onStop() {
        super.onStop()
        player.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
        session.release()
    }
}
