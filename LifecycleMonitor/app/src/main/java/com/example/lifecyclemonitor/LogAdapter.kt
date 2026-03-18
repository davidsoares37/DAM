package com.example.lifecyclemonitor

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lifecyclemonitor.databinding.ItemLogBinding
import com.example.lifecyclemonitor.databinding.ItemSeparatorBinding
import com.example.lifecyclemonitor.model.EntryType
import com.example.lifecyclemonitor.model.LogEntry
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LogAdapter : ListAdapter<LogEntry, RecyclerView.ViewHolder>(LogDiffCallback()) {

    private val timeFormatter = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            EntryType.LOG.ordinal -> {
                LogViewHolder(ItemLogBinding.inflate(layoutInflater, parent, false))
            }
            EntryType.SEPARATOR.ordinal -> {
                SeparatorViewHolder(ItemSeparatorBinding.inflate(layoutInflater, parent, false))
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is LogViewHolder -> holder.bind(item)
            is SeparatorViewHolder -> {} // No binding needed for separator
        }
    }

    inner class LogViewHolder(private val binding: ItemLogBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(entry: LogEntry) {
            val context = binding.root.context
            binding.tvActivityName.text = entry.activityName
            binding.tvEventName.text = entry.eventName
            binding.tvTimestamp.text = timeFormatter.format(Date(entry.timestamp))

            entry.colorRes?.let { colorResId ->
                val bgTint = ContextCompat.getColor(context, colorResId)
                binding.cardActivityBadge.setCardBackgroundColor(bgTint)
                
                // Assuming text color is linked to bg color by a known mapping in colors.xml
                // We'll set a default black if mapping not provided for simplicity
                val textColorResId = when (colorResId) {
                    R.color.activity_main_bg -> R.color.activity_main_text
                    R.color.activity_second_bg -> R.color.activity_second_text
                    R.color.activity_third_bg -> R.color.activity_third_text
                    else -> R.color.black
                }
                binding.tvActivityName.setTextColor(ContextCompat.getColor(context, textColorResId))
                binding.cardActivityBadge.strokeColor = ContextCompat.getColor(context, textColorResId)
                binding.cardActivityBadge.strokeWidth = 2
            }

            entry.iconRes?.let {
                binding.ivEventIcon.setImageResource(it)
                binding.ivEventIcon.visibility = android.view.View.VISIBLE
            } ?: run {
                binding.ivEventIcon.visibility = android.view.View.GONE
            }

            entry.eventColorRes?.let {
                binding.cardStateBadge.setCardBackgroundColor(ContextCompat.getColor(context, it))
                binding.tvEventName.setTextColor(ContextCompat.getColor(context, it))
                binding.ivEventIcon.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(context, it))
            }
        }
    }

    inner class SeparatorViewHolder(binding: ItemSeparatorBinding) :
        RecyclerView.ViewHolder(binding.root)

    class LogDiffCallback : DiffUtil.ItemCallback<LogEntry>() {
        override fun areItemsTheSame(oldItem: LogEntry, newItem: LogEntry): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LogEntry, newItem: LogEntry): Boolean {
            return oldItem == newItem
        }
    }
}
