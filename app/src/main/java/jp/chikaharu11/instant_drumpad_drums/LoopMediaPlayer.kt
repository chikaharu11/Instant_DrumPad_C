package jp.chikaharu11.instant_drumpad_drums

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.net.Uri
import android.util.Log


class LoopMediaPlayer(context: Context, resId: Uri) {
    private var mContext: Context? = null
    private var mResId: Uri? = null
    private var mCounter = 1
    private var mCurrentPlayer: MediaPlayer? = null
    private var mNextPlayer: MediaPlayer? = null
    private var count = 0.5f
    private fun createNextMediaPlayer() {
        mNextPlayer = MediaPlayer.create(mContext, mResId)
        mCurrentPlayer!!.setNextMediaPlayer(mNextPlayer)
        mCurrentPlayer!!.setOnCompletionListener(onCompletionListener)
        setVolume(count,count)
    }

    private val onCompletionListener =
        OnCompletionListener { mediaPlayer ->
            mediaPlayer.release()
            mCurrentPlayer = mNextPlayer
            createNextMediaPlayer()
            Log.d(TAG, String.format("Loop #%d", ++mCounter))
        }

    @get:Throws(IllegalStateException::class)
    val isPlaying: Boolean
        get() = mCurrentPlayer!!.isPlaying

    private fun setVolume(leftVolume: Float, rightVolume: Float) {
        mCurrentPlayer!!.setVolume(leftVolume, rightVolume)
    }

    fun volumePlus() {
        if (count <= 1.0f) {
            count += 0.1f
        }
        setVolume(count,count)
        println(count)
    }

    fun volumeMinus() {
        if (count >= 0.1f) {
            count -= 0.1f
        }
        setVolume(count,count)
        println(count)
    }

    @Throws(IllegalStateException::class)
    fun start() {
        mCurrentPlayer!!.start()
    }

    @Throws(IllegalStateException::class)
    fun stop() {
        mCurrentPlayer!!.stop()
        mCurrentPlayer!!.prepare()
    }

    @Throws(IllegalStateException::class)
    fun pause() {
        mCurrentPlayer!!.pause()
    }

    fun release() {
        mCurrentPlayer!!.release()
        mNextPlayer!!.release()
    }

    fun reset() {
        mCurrentPlayer!!.reset()
        mNextPlayer!!.reset()
    }

    companion object {
        val TAG = LoopMediaPlayer::class.java.simpleName
        fun create(context: Context, resId: Uri): LoopMediaPlayer {
            return LoopMediaPlayer(context, resId)
        }
    }

    init {
        mContext = context
        mResId = resId
        mCurrentPlayer = MediaPlayer.create(mContext, mResId)
        createNextMediaPlayer()
    }
}