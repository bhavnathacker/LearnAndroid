package com.bhavnathacker.learnandroid.ui.facebook

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
import kotlinx.android.synthetic.main.list_item_facebook.view.*

class FacebookAdapter(private var entries: List<Facebook>) :
    RecyclerView.Adapter<FacebookAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_facebook, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(entries[position])
    }

    override fun getItemCount() = entries.size
    fun setItems(entries: List<Facebook>) {
        this.entries = entries
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var entry: Facebook

        fun bind(entry: Facebook) {
            this.entry = entry
            itemView.facebookText.text = entry.name

            updateFollowStatus(entry)

            val context = itemView.context
            itemView.facebookImage.setImageResource(
                context.resources.getIdentifier(
                    entry.imageDrawable,
                    null,
                    context.packageName
                )
            )
            val backgroundColor: Int = AppUtils.getRandomColor()
            itemView.facebookContainer.setBackgroundColor(backgroundColor)
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

            itemView.facebookStatus.setOnClickListener {
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

        private fun updateFollowStatus(entry: Facebook) {
            val followStatus =
                PrefUtils.getPreference(entry.id, AppUtils.followDefault)
            followStatus?.let {
                itemView.facebookStatus.text = followStatus
            }
        }
    }
}