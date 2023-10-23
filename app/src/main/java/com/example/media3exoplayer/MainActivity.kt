package com.example.media3exoplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.exoplayer.DefaultRenderersFactory
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.RenderersFactory
import androidx.media3.ui.PlayerView
import com.example.media3exoplayer.ui.theme.Media3ExoplayerTheme


class MainActivity : ComponentActivity() {
    private lateinit var exoPlayer: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val youtubeUrl = "https://www.youtube.com/watch?v=dv13gl0a-FA"
//        val youtubeUrl = "https://www.youtube.com/embed/dv13gl0a-FA"
//        val youtubeUrl = "https://dai.ly/x8oh7bo"

        val youtubeUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"

        val renderersFactory: RenderersFactory = DefaultRenderersFactory(this)

        exoPlayer = ExoPlayer.Builder(this, renderersFactory).build()

        val mediaItem = MediaItem.Builder()
            .setUri(youtubeUrl)
            .setMimeType(MimeTypes.VIDEO_MP4)
//            .setMimeType(MimeTypes.APPLICATION_MPD)
            .build()

        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.play()

        setContent {
            Media3ExoplayerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AndroidView(factory = { context ->
                        PlayerView(context).apply {
                            player = exoPlayer
                        }
                    })
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer.release()
    }
}