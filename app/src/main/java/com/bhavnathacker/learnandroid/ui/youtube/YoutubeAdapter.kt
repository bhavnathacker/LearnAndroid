package com.bhavnathacker.learnandroid.ui.youtube

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bhavnathacker.learnandroid.R
import com.bhavnathacker.learnandroid.utils.AppUtils
import com.bhavnathacker.learnandroid.utils.PrefUtils
import kotlinx.android.synthetic.main.list_item_youtube.view.*

class YoutubeAdapter(private var channels: List<YoutubeChannel>) :
    RecyclerView.Adapter<YoutubeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_youtube, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(channels[position])
    }

    override fun getItemCount() = channels.size
    fun setItems(channels: List<YoutubeChannel>) {
        this.channels = channels
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var channel: YoutubeChannel

        fun bind(channel: YoutubeChannel) {
            this.channel = channel
            itemView.youtubeText.text = channel.name

            updateSubscribeStatus(channel)

            val context = itemView.context
            itemView.youtubeImage.setImageResource(
                context.resources.getIdentifier(
                    channel.imageDrawable,
                    null,
                    context.packageName
                )
            )
            val backgroundColor: Int = AppUtils.getRandomColor()
            itemView.youtubeContainer.setBackgroundColor(backgroundColor)
            itemView.animation =
                AnimationUtils.loadAnimation(itemView.context, R.anim.top_bottom_slide)

            itemView.setOnClickListener {
                try {
                    val browserIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(channel.link)
                    )
                    itemView.context.startActivity(browserIntent)
                } catch (e: ActivityNotFoundException) {
                }
            }

            itemView.youtubeStatus.setOnClickListener {
                showAlertDialog(itemView.context!!)
            }
        }

        private fun showAlertDialog(context: Context) {
            val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
            alertDialog.setTitle(context.getString(R.string.update_status_to))
            val items = AppUtils.subscribeItems
            val currentStatus =
                PrefUtils.getPreference(channel.id, AppUtils.subscribeDefault)
            val checkedItem = items.indexOf(currentStatus)
            alertDialog.setSingleChoiceItems(
                items,
                checkedItem
            ) { dialogInterface, which ->
                PrefUtils.savePreference(channel.id, AppUtils.subscribeItems[which])
                updateSubscribeStatus(channel)
                dialogInterface.dismiss()
            }

            val alert: AlertDialog = alertDialog.create()
            alert.setCanceledOnTouchOutside(true)
            alert.show()
        }

        private fun updateSubscribeStatus(channel: YoutubeChannel) {
            val subscribeStatus =
                PrefUtils.getPreference(channel.id, AppUtils.subscribeDefault)
            subscribeStatus?.let {
                itemView.youtubeStatus.text = subscribeStatus
            }
        }
    }
}