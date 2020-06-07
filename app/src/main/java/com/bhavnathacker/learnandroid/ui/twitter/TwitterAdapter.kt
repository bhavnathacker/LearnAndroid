package com.bhavnathacker.learnandroid.ui.twitter

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
import kotlinx.android.synthetic.main.list_item_twitter.view.*

class TwitterAdapter(private var entries: List<Twitter>) :
    RecyclerView.Adapter<TwitterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_twitter, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(entries[position])
    }

    override fun getItemCount() = entries.size
    fun setItems(entries: List<Twitter>) {
        this.entries = entries
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var entry: Twitter

        fun bind(entry: Twitter) {
            this.entry = entry
            itemView.twitterText.text = entry.name

            updateFollowStatus(entry)

            val context = itemView.context
            itemView.twitterImage.setImageResource(
                context.resources.getIdentifier(
                    entry.imageDrawable,
                    null,
                    context.packageName
                )
            )
            val backgroundColor: Int = AppUtils.getRandomColor()
            itemView.twitterContainer.setBackgroundColor(backgroundColor)
            itemView.animation =
                AnimationUtils.loadAnimation(itemView.context, R.anim.top_bottom_slide)

            itemView.setOnClickListener {
                try {
                    val browserIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(entry.link)
                    )
                    itemView.context.startActivity(browserIntent)
                } catch (e: ActivityNotFoundException) {
                }
            }

            itemView.twitterStatus.setOnClickListener {
                showAlertDialog(itemView.context!!)
            }
        }

        private fun showAlertDialog(context: Context) {
            val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
            alertDialog.setTitle(context.getString(R.string.update_status_to))
            val items = AppUtils.followItems
            val currentStatus =
                PrefUtils.getPreference(entry.id, AppUtils.followDefault)
            val checkedItem = items.indexOf(currentStatus)
            alertDialog.setSingleChoiceItems(
                items,
                checkedItem
            ) { dialogInterface, which ->
                PrefUtils.savePreference(entry.id, AppUtils.followItems[which])
                updateFollowStatus(entry)
                dialogInterface.dismiss()
            }

            val alert: AlertDialog = alertDialog.create()
            alert.setCanceledOnTouchOutside(true)
            alert.show()
        }

        private fun updateFollowStatus(entry: Twitter) {
            val followStatus =
                PrefUtils.getPreference(entry.id, AppUtils.followDefault)
            followStatus?.let {
                itemView.twitterStatus.text = followStatus
            }
        }
    }
}