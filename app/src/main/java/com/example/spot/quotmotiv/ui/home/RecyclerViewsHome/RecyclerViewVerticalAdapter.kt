package com.example.spot.quotmotiv.ui.home.RecyclerViewsHome

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.spot.quotmotiv.Activities.AllQuotesActivity
import com.example.spot.quotmotiv.R
import com.example.spot.quotmotiv.ui.PressedAnimation

class RecyclerViewVerticalAdapter (private val rvLList: List<RecyclerViewHomeData>) : RecyclerView.Adapter<RecyclerViewVerticalAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_vertical_home_layout, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val verticalItemsViewModel = rvLList[position]

        // sets the image to the imageview from our itemHolder class
        holder.verticalImageView.setImageResource(verticalItemsViewModel.image)

        // sets the text to the textview from our itemHolder class
        holder.verticalTextView.text = verticalItemsViewModel.text
        holder.verticalCardView.setOnClickListener {
            startActivityOther(it,verticalItemsViewModel.text)
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return rvLList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(verticalItemView: View) : RecyclerView.ViewHolder(verticalItemView) {
        val verticalImageView: ImageView = verticalItemView.findViewById(R.id.iv_VerticalHome)
        val verticalTextView: TextView = verticalItemView.findViewById(R.id.tv_VerticalHome)
        val verticalCardView: CardView = verticalItemView.findViewById(R.id.cv_VerticalHome)

    }
    private fun startActivityOther(it:View,quotesTypeV:String)
    {
        val intent = Intent(it.context, AllQuotesActivity::class.java)
        intent.putExtra("VerticalQuotesName",quotesTypeV)
        ContextCompat.startActivity(it.context, intent, null)
    }
}