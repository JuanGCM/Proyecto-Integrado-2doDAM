package com.triana.virtual_gaming.ui.Splash

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.triana.virtual_gaming.R
import com.triana.virtual_gaming.ui.login.LoginActivity


class SplashActivity : AppCompatActivity() {

    private lateinit var container: ImageView
    private lateinit var animationDrawable: AnimationDrawable
    lateinit var ctx: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        //setActivityBackgroundColor()

        ctx = this
        container = findViewById(R.id.iv_icons)

        container.setBackgroundResource(R.drawable.splash_animation)
        animationDrawable = container.background as AnimationDrawable

    }

    override fun onResume() {
        super.onResume()
        animationDrawable.start()
        checkAnimationStatus(50, animationDrawable)
    }


    /**
     * check the animation status recursively, keep the animation until it reach the last frame.
     *
     * @param time              period of animation
     * @param animationDrawable animation list
     */
    private fun checkAnimationStatus(
        time: Long,
        animationDrawable: AnimationDrawable
    ) {
        val handler = Handler()
        handler.postDelayed(Runnable {
            if (animationDrawable.current !== animationDrawable.getFrame(animationDrawable.numberOfFrames - 1)) checkAnimationStatus(
                2000,
                animationDrawable
            ) else{
                val intell = Intent(ctx, LoginActivity::class.java)
                startActivity(intell)
            }
        }, 2000)
    }

    fun setActivityBackgroundColor() {
        val view = this.window.decorView
        view.setBackgroundColor(Color.argb(0, 0, 0,5))
    }
}