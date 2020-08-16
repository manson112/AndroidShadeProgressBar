package me.manson112.custom.shadeprogressbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
//            fill_image.startProgress()
            fill_image.startFillingAnimation()
        }
        btn2.setOnClickListener {
//            fill_image.stopProgress()
            fill_image.stopFillingAnimation()
        }

    }

}