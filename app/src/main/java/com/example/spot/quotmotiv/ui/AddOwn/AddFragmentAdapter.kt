package com.example.spot.quotmotiv.ui.AddOwn

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.spot.quotmotiv.DataBase.WriteQuotes
import com.example.spot.quotmotiv.R
import com.example.spot.quotmotiv.ui.PressedAnimation


class AddFragmentAdapter(private val addedQuotesList: List<WriteQuotes>,private val addListner : addFragmentListner) :
    RecyclerView.Adapter<AddFragmentAdapter.ViewHolder>() {

    private lateinit var context: Context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AddFragmentAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_add_quotes_layout, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddFragmentAdapter.ViewHolder, position: Int) {
        val addQuotesItemsViewModel = addedQuotesList[position]

        if (position % 2 == 0) {
            holder.addQuotesLinearLayout.background =
                ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border)
            holder.addQuotesHLinearLayout.background =
                ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border)

        } else {
            holder.addQuotesLinearLayout.background =
                ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border_with_solid)
            holder.addQuotesHLinearLayout.background =
                ContextCompat.getDrawable(context, R.drawable.ic_cv_shape_border_with_solid)
        }

        holder.addQuotesTextView.text = addQuotesItemsViewModel.quotesWrite
        holder.addQuotesTextViewAuthor.text = addQuotesItemsViewModel.authorWrite

        holder.addQuotesLinearLayout.setOnClickListener {
            PressedAnimation.addAnimation(it)
            addListner.showDialogAdd(it,addQuotesItemsViewModel.quotesWrite, addQuotesItemsViewModel.authorWrite)
        }
        holder.addQuotesTextViewDelete.setOnClickListener {
            PressedAnimation.addAnimation(it)
            addListner.deleteQuoteAdd(addQuotesItemsViewModel)
        }
    }

    override fun getItemCount(): Int {
        return addedQuotesList.size
    }

    class ViewHolder(addQuotesItemView: View) : RecyclerView.ViewHolder(addQuotesItemView) {
        val addQuotesTextView: TextView = addQuotesItemView.findViewById(R.id.tv_AddQuotes)
        val addQuotesLinearLayout: LinearLayout =
            addQuotesItemView.findViewById(R.id.ll_H_AddQuotes)
        val addQuotesHLinearLayout: LinearLayout = addQuotesItemView.findViewById(R.id.ll_AddQuotes)
        val addQuotesTextViewAuthor: TextView =
            addQuotesItemView.findViewById(R.id.tv_AddQuotesAuthor)
        val addQuotesTextViewDelete: ImageView = addQuotesItemView.findViewById(R.id.delete_AddQuotes)


    }

    interface addFragmentListner
    {
        fun showDialogAdd(view: View, quotes: String, quotesAuthor: String)
        fun deleteQuoteAdd(writeQuotes: WriteQuotes)
    }
}