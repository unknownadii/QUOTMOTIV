package com.example.spot.quotmotiv.Activities.RecyclerViewAllQuotes


import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.example.spot.quotmotiv.R


import android.content.Context.CLIPBOARD_SERVICE
import android.os.Build

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.room.Room
import com.example.spot.quotmotiv.Activities.AllQuotesActivity
import com.example.spot.quotmotiv.Context.ApplicationContext
import com.example.spot.quotmotiv.DataBase.QuotesFav
import com.example.spot.quotmotiv.DataBase.QuotesFavDataBase
import com.example.spot.quotmotiv.ui.PressedAnimation
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class RecyclerViewAllQuotesAdapter(
    private val rvAllQuotesList: List<RecyclerViewAllQuotesData>,
    private val show: dialogWork
) :
    RecyclerView.Adapter<RecyclerViewAllQuotesAdapter.ViewHolder>() {

    lateinit var context: Context


    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_all_quotes_layout, parent, false)

        context = parent.context
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val allQuotesItemsViewModel = rvAllQuotesList[position]

        // sets the text to the textview from our itemHolder class

        if (position % 2 == 0) {
            holder.allQuotesLinearLayout.background =
                ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border)
            holder.allQuotesHLinearLayout.background =
                ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border)
            holder.allQuotesTextViewNumber.background =
                ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border_with_solid)

        } else {
            holder.allQuotesLinearLayout.background =
                ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border_with_solid)
            holder.allQuotesHLinearLayout.background =
                ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border_with_solid)
            holder.allQuotesTextViewNumber.background =
                ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border)

        }

        holder.allQuotesTextViewNumber.text = allQuotesItemsViewModel.count
        holder.allQuotesTextView.text = allQuotesItemsViewModel.quotes
        holder.allQuotesTextViewAuthor.text = allQuotesItemsViewModel.quotesAuthor

        holder.allQuotesLinearLayout.setOnClickListener {
            PressedAnimation.addAnimation(it)
            show.showingDialog(
                context, allQuotesItemsViewModel.quotes,
                allQuotesItemsViewModel.quotesAuthor
            )
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return rvAllQuotesList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(allQuotesItemView: View) : RecyclerView.ViewHolder(allQuotesItemView) {
        val allQuotesTextView: TextView = allQuotesItemView.findViewById(R.id.tv_allQuotes)
        val allQuotesLinearLayout: LinearLayout =
            allQuotesItemView.findViewById(R.id.ll_H_allQuotes)
        val allQuotesHLinearLayout: LinearLayout = allQuotesItemView.findViewById(R.id.ll_allQuotes)
        val allQuotesTextViewAuthor: TextView =
            allQuotesItemView.findViewById(R.id.tv_allQuotesAuthor)
        val allQuotesTextViewNumber: TextView =
            allQuotesItemView.findViewById(R.id.tv_number_allQuotes)
    }


    interface dialogWork {
        fun showingDialog(context: Context, quotes: String, author: String)
    }
}

