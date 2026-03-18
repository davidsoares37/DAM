package com.example.lifecyclemonitor

import android.content.Intent
import android.widget.Button

class MainActivity : BaseActivity() {

    override val activityName: String = "MainActivity"
    override val activityColorRes: Int = R.color.activity_main_bg

    override fun setupNavigationButton(button: Button) {
        button.text = "Go to SecondActivity"
        button.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}
