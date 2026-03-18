package com.example.lifecyclemonitor

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lifecyclemonitor.databinding.ActivityBaseBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding
    private lateinit var logAdapter: LogAdapter

    abstract val activityName: String
    @get:ColorRes
    abstract val activityColorRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupUI()
        observeLogs()
        
        logLifecycleEvent("onCreate", R.color.state_create, android.R.drawable.ic_menu_add)
    }

    private fun setupRecyclerView() {
        logAdapter = LogAdapter()
        binding.recyclerViewLogs.apply {
            layoutManager = LinearLayoutManager(this@BaseActivity)
            adapter = logAdapter
            itemAnimator = null // Prevent flash on layout updates
        }
    }

    private fun setupUI() {
        // Setup Toolbar
        setSupportActionBar(binding.toolbar)
        if (this !is MainActivity) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            binding.toolbar.setNavigationOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
        }

        binding.tvCurrentActivityLabel.text = "Current State: $activityName"
        
        // Auto scroll toggle
        binding.btnAutoScroll.setOnClickListener {
            LifecycleLogger.toggleAutoScroll()
        }

        binding.btnAddSeparator.setOnClickListener {
            LifecycleLogger.addSeparator()
            scrollToBottomIfNeeded()
        }

        binding.btnClearLogs.setOnClickListener {
            LifecycleLogger.clearLogs()
        }
        
        setupNavigationButton(binding.btnNextActivity)
    }

    protected open fun setupNavigationButton(button: Button) {
        button.text = "Finish"
        button.setOnClickListener { finish() }
    }

    private fun observeLogs() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    LifecycleLogger.logs.collectLatest { logs ->
                        logAdapter.submitList(logs) {
                            scrollToBottomIfNeeded()
                        }
                    }
                }
                launch {
                    LifecycleLogger.autoScroll.collectLatest { autoScrollEnabled ->
                        binding.btnAutoScroll.setIconResource(
                            if (autoScrollEnabled) android.R.drawable.checkbox_on_background 
                            else android.R.drawable.checkbox_off_background
                        )
                    }
                }
            }
        }
    }

    private fun scrollToBottomIfNeeded() {
        if (LifecycleLogger.autoScroll.value && logAdapter.itemCount > 0) {
            binding.recyclerViewLogs.scrollToPosition(logAdapter.itemCount - 1)
        }
    }

    private fun logLifecycleEvent(eventName: String, colorRes: Int, iconRes: Int) {
        LifecycleLogger.logEvent(
            activityName = activityName,
            eventName = eventName,
            colorRes = activityColorRes,
            iconRes = iconRes,
            eventColorRes = colorRes
        )
    }

    override fun onStart() {
        super.onStart()
        logLifecycleEvent("onStart", R.color.state_start, android.R.drawable.ic_media_play)
    }

    override fun onResume() {
        super.onResume()
        logLifecycleEvent("onResume", R.color.state_resume, android.R.drawable.ic_media_ff)
    }

    override fun onPause() {
        super.onPause()
        logLifecycleEvent("onPause", R.color.state_pause, android.R.drawable.ic_media_pause)
    }

    override fun onStop() {
        super.onStop()
        logLifecycleEvent("onStop", R.color.state_stop, android.R.drawable.ic_delete)
    }

    override fun onDestroy() {
        super.onDestroy()
        logLifecycleEvent("onDestroy", R.color.state_destroy, android.R.drawable.ic_menu_close_clear_cancel)
    }

    override fun onRestart() {
        super.onRestart()
        logLifecycleEvent("onRestart", R.color.state_restart, android.R.drawable.ic_menu_rotate)
    }
}
