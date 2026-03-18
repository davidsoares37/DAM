package com.example.lifecyclemonitor

import android.content.Intent
import android.widget.Button

class SecondActivity : BaseActivity() {

    override val activityName: String = "SecondActivity"
    override val activityColorRes: Int = R.color.activity_second_bg

    override fun setupNavigationButton(button: Button) {
        button.text = "Go to ThirdActivity"
        button.setOnClickListener {
            startActivity(Intent(this, ThirdActivity::class.java))
        }
    }
}
