package com.example.spot.quotmotiv.ui.home.RecyclerViewsHome

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.spot.quotmotiv.Activities.AllQuotesActivity
import com.example.spot.quotmotiv.R
import com.example.spot.quotmotiv.ui.PressedAnimation

class RecyclerViewHorizontalAdapter (private val rvHList: List<RecyclerViewHomeData>) : RecyclerView.Adapter<RecyclerViewHorizontalAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_horizontal_home_layout, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val horizontalItemsViewModel = rvHList[position]

        // sets the image to the imageview from our itemHolder class
        holder.horizontalImageView.setImageResource(horizontalItemsViewModel.image)

        // sets the text to the textview from our itemHolder class
        holder.horizontalTextView.text = horizontalItemsViewModel.text
        holder.horizontalCardView.setOnClickListener {
            startActivityOther(it,horizontalItemsViewModel.text)
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return rvHList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(horizontalItemView: View) : RecyclerView.ViewHolder(horizontalItemView) {
        val horizontalImageView: ImageView = horizontalItemView.findViewById(R.id.iv_horizontalHome)
        val horizontalTextView: TextView = horizontalItemView.findViewById(R.id.tv_horizontalHome)
        val horizontalCardView: CardView = horizontalItemView.findViewById(R.id.cv_horizontalHome)
    }

    private fun startActivityOther(it:View,quotesType: String)
    {
        val intent = Intent(it.context, AllQuotesActivity::class.java)
        intent.putExtra("QuotesName",quotesType)
        startActivity(it.context,intent,null)
    }
}