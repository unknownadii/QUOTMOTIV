package com.example.spot.quotmotiv.ui.AddOwn.OwnQuotes


import android.os.Bundle
import android.view.View

import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.spot.quotmotiv.DataBase.QuotesFavDataBase
import com.example.spot.quotmotiv.DataBase.WriteQuotes

import com.example.spot.quotmotiv.ui.PressedAnimation
import com.example.spot.quotmotiv.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class WriteOwnQuotesFragment : DialogFragment(R.layout.fragment_write_own_quotes) {

    private lateinit var dataBase: QuotesFavDataBase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initializing  data from the database
        dataBase = Room.databaseBuilder(
            requireContext().applicationContext,
            QuotesFavDataBase::class.java, "quotesFavDB"
        ).build()


        val backArrow = view.findViewById<ImageView>(R.id.backArrowWrite)
        val quotesSubmit = view.findViewById<Button>(R.id.submitBtnWrite)

        //Adding Data to dataBase
        quotesSubmit.setOnClickListener {
            addingQuotes(view)
        }

        backArrow.setOnClickListener {
            PressedAnimation.addAnimation(it)
            // how to naviagte from one fragment to another fragement
             findNavController().navigate(R.id.navigation_add)
            //how to dismiss the fragment dialog
            dismiss()
        }

    }

    private fun addingQuotes(view: View) {
        val quotes = view.findViewById<TextView>(R.id.writeQuotes)
        val quotesAuthor = view.findViewById<TextView>(R.id.writeQuotesAuthor)
        val quotesSubmit = view.findViewById<Button>(R.id.submitBtnWrite)

        //Adding Data to dataBase
            if (quotes.text.isNotEmpty() && quotesAuthor.text.isNotEmpty()) {
                GlobalScope.launch {
                    dataBase.quotesFavDAO().insertWriteQuotes(
                        WriteQuotes(
                            0, quotes.text.toString(), quotesAuthor.text.toString()
                        )
                    )
                }
                Toast.makeText(view.context, "Successfully Added", Toast.LENGTH_SHORT).show()
                quotes.text = ""
                quotesAuthor.text = ""
                //moving from one fragment to another
                findNavController().navigate(R.id.navigation_add)
                dismiss()
            } else if (quotes.text.isEmpty()) {
                Toast.makeText(view.context, "Please Write Some Quote To Add", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(view.context, "Please Provide Author ", Toast.LENGTH_SHORT).show()
            }
    }
}