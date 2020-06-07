package com.bhavnathacker.learnandroid.ui.codelabs

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
import com.bhavnathacker.learnandroid.utils.AppUtils.progressDefault
import com.bhavnathacker.learnandroid.utils.AppUtils.progressItems
import com.bhavnathacker.learnandroid.utils.PrefUtils
import kotlinx.android.synthetic.main.list_item_codelab.view.*


class CodelabAdapter(private var levels: List<CodelabLevel>) :
    RecyclerView.Adapter<CodelabAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_codelab, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(levels[position])
    }

    override fun getItemCount() = levels.size
    fun setItems(levels: List<CodelabLevel>) {
        this.levels = levels
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var codelabLevel: CodelabLevel

        fun bind(codelabLevel: CodelabLevel) {
            this.codelabLevel = codelabLevel
            itemView.codelabText.text = codelabLevel.name

            updateProgressStatus(codelabLevel)

            val context = itemView.context
            itemView.codelabImage.setImageResource(
                context.resources.getIdentifier(
                    codelabLevel.imageDrawable,
                    null,
                    context.packageName
                )
            )

            val backgroundColor: Int = AppUtils.getRandomColor()
            itemView.codelabContainer.setBackgroundColor(backgroundColor)
            itemView.animation =
                AnimationUtils.loadAnimation(itemView.context, R.anim.scale_plus_alpha)

            itemView.setOnClickListener {
                try {
                    val browserIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(codelabLevel.link)
                    )
                    itemView.context.startActivity(browserIntent)
                } catch (e: ActivityNotFoundException) {
                }
            }

            itemView.codelabStatus.setOnClickListener {
                showAlertDialog(itemView.context!!)
            }
        }

        private fun showAlertDialog(context: Context) {
            val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
            alertDialog.setTitle(context.getString(R.string.update_status_to))
            val items = progressItems
            val currentStatus = PrefUtils.getPreference(codelabLevel.id, progressDefault)
            val checkedItem = items.indexOf(currentStatus)
            alertDialog.setSingleChoiceItems(
                items,
                checkedItem
            ) { dialogInterface, which ->
                PrefUtils.savePreference(codelabLevel.id, progressItems[which])
                updateProgressStatus(codelabLevel)
                dialogInterface.dismiss()
            }
            val alert: AlertDialog = alertDialog.create()
            alert.setCanceledOnTouchOutside(true)
            alert.show()
        }

        private fun updateProgressStatus(codelabLevel: CodelabLevel) {
            val progressStatus =
                PrefUtils.getPreference(codelabLevel.id, progressDefault)
            progressStatus?.let {
                itemView.codelabStatus.text = progressStatus
            }
        }
    }
}