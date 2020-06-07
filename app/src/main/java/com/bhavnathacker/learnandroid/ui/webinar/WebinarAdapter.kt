package com.bhavnathacker.learnandroid.ui.webinar


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
import kotlinx.android.synthetic.main.list_item_webinar.view.*


class WebinarAdapter(private var sessions: List<Webinar>) :
    RecyclerView.Adapter<WebinarAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_webinar, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(sessions[position])
    }

    override fun getItemCount() = sessions.size
    fun setItems(sessions: List<Webinar>) {
        this.sessions = sessions
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var webinar: Webinar

        fun bind(webinar: Webinar) {
            this.webinar = webinar
            itemView.webinarText.text = webinar.name
            itemView.webinarDuration.text = webinar.duration

            updateProgressStatus(webinar)

            val context = itemView.context
            itemView.webinarImage.setImageResource(
                context.resources.getIdentifier(
                    webinar.imageDrawable,
                    null,
                    context.packageName
                )
            )
            val backgroundColor: Int = AppUtils.getRandomColor()
            itemView.webinarContainer.setBackgroundColor(backgroundColor)
            itemView.animation =
                AnimationUtils.loadAnimation(itemView.context, R.anim.bottom_up_slide)

            itemView.setOnClickListener {
                try {
                    val browserIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(webinar.link)
                    )
                    itemView.context.startActivity(browserIntent)
                } catch (e: ActivityNotFoundException) {
                }
            }

            itemView.webinarStatus.setOnClickListener {
                showAlertDialog(itemView.context!!)
            }
        }

        private fun showAlertDialog(context: Context) {
            val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
            alertDialog.setTitle(context.getString(R.string.update_status_to))
            val items = AppUtils.progressItems
            val currentStatus = PrefUtils.getPreference(webinar.id, AppUtils.progressDefault)
            val checkedItem = items.indexOf(currentStatus)
            alertDialog.setSingleChoiceItems(
                items,
                checkedItem
            ) { dialogInterface, which ->
                PrefUtils.savePreference(webinar.id, AppUtils.progressItems[which])
                updateProgressStatus(webinar)
                dialogInterface.dismiss()
            }
            val alert: AlertDialog = alertDialog.create()
            alert.setCanceledOnTouchOutside(true)
            alert.show()
        }

        private fun updateProgressStatus(webinar: Webinar) {
            val progressStatus =
                PrefUtils.getPreference(webinar.id, AppUtils.progressDefault)
            progressStatus?.let {
                itemView.webinarStatus.text = progressStatus
            }
        }
    }


}