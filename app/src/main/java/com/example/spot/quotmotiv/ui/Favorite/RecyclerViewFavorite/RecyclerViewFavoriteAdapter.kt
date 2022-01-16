package com.example.spot.quotmotiv.ui.Favorite.RecyclerViewFavorite

import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.spot.quotmotiv.DataBase.QuotesFav
import com.example.spot.quotmotiv.R
import com.example.spot.quotmotiv.ui.PressedAnimation
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RecyclerViewFavoriteAdapter(private val rvFavQuotesList: List<QuotesFav>,
                                  private val click : listner) :
    RecyclerView.Adapter<RecyclerViewFavoriteAdapter.ViewHolder>() {

    lateinit var context: Context

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_favorite_layout, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val allFavItemsViewModel = rvFavQuotesList[position]

        // sets the text to the textview from our itemHolder class

        if (position % 2 == 0) {
            holder.favQuotesLinearLayout.background =
                ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border)
            holder.favQuotesHLinearLayout.background =
                ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border)
            holder.favQuotesTextViewNumber.background =
                ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border_with_solid)

        } else {
            holder.favQuotesLinearLayout.background =
                ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border_with_solid)
            holder.favQuotesHLinearLayout.background =
                ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border_with_solid)
           holder.favQuotesTextViewNumber.background =
                ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border)

        }

        holder.favQuotesTextViewNumber.text = allFavItemsViewModel.id.toString()
        holder.favQuotesTextView.text = allFavItemsViewModel.quotes
        holder.favQuotesTextViewAuthor.text = allFavItemsViewModel.author

        holder.favQuotesLinearLayout.setOnClickListener {
            PressedAnimation.addAnimation(it)
          click.showBottomDialog(it,allFavItemsViewModel.quotes, allFavItemsViewModel.author)
        }
        holder.favQuotesTextViewDelete.setOnClickListener {
            PressedAnimation.addAnimation(it)
            click.deleteClicked(allFavItemsViewModel)
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return rvFavQuotesList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(allFavItemView: View) : RecyclerView.ViewHolder(allFavItemView) {
        val favQuotesTextView: TextView = allFavItemView.findViewById(R.id.tv_FavQuotes)
        val favQuotesLinearLayout: LinearLayout = allFavItemView.findViewById(R.id.ll_H_Fav)
        val favQuotesHLinearLayout: LinearLayout = allFavItemView.findViewById(R.id.ll_Fav)
        val favQuotesTextViewAuthor: TextView = allFavItemView.findViewById(R.id.tv_FavQuotesAuthor)
        val favQuotesTextViewNumber: TextView = allFavItemView.findViewById(R.id.tv_number_Fav)
        val favQuotesTextViewDelete: ImageView = allFavItemView.findViewById(R.id.delete_Fav)
    }



    interface listner
    {
        fun showBottomDialog(view: View, quotes: String, quotesAuthor: String)
        fun deleteClicked(deletedQuotes : QuotesFav)
    }

}

