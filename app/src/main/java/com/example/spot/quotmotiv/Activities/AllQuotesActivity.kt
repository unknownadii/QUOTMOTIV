package com.example.spot.quotmotiv.Activities

import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.spot.quotmotiv.Activities.RecyclerViewAllQuotes.AllTypeOfQuotes
import com.example.spot.quotmotiv.Activities.RecyclerViewAllQuotes.RecyclerViewAllQuotesAdapter
import com.example.spot.quotmotiv.Activities.RecyclerViewAllQuotes.RecyclerViewAllQuotesData
import com.example.spot.quotmotiv.DataBase.QuotesFav
import com.example.spot.quotmotiv.DataBase.QuotesFavDataBase
import com.example.spot.quotmotiv.R
import com.example.spot.quotmotiv.ui.PressedAnimation
import com.example.spot.quotmotiv.ui.home.RecyclerViewsHome.CommonData
import com.example.spot.quotmotiv.ui.home.RecyclerViewsHome.RecyclerViewHorizontalAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AllQuotesActivity : AppCompatActivity(), RecyclerViewAllQuotesAdapter.dialogWork {
    lateinit var dialog: Dialog
    lateinit var titleQuotesType: String
    private lateinit var database: QuotesFavDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_quotes)

        val toolbarallQuotesactivity =
            findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_allQuotes_activity)
        setSupportActionBar(toolbarallQuotesactivity)

        database = Room.databaseBuilder(
            applicationContext,
            QuotesFavDataBase::class.java, "quotesFavDB"
        ).build()

        val actionTitle = intent.getStringExtra("QuotesName")
        val verticalActionTitle = intent.getStringExtra("VerticalQuotesName")
        val actionbar = supportActionBar //actionbar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)  //set back button
            if (actionTitle != null) {
                actionbar.title = actionTitle
                titleQuotesType = actionTitle
            } else {
                if (verticalActionTitle != null) {
                    actionbar.title = verticalActionTitle
                    titleQuotesType = verticalActionTitle
                }
            }
        }

        toolbarallQuotesactivity.setNavigationOnClickListener {
            onBackPressed()
        }

        if (actionTitle != null) {
            showDialog()
            Handler().postDelayed({
                getQuotes(actionTitle)
            }, 1000)
        } else {
            if (verticalActionTitle != null) {
                showDialog()
                Handler().postDelayed({
                    getQuotes(verticalActionTitle)
                }, 1000)
            }
        }

    }

    private fun getQuotes(quotesType: String) {
        when (quotesType) {
            "Time Quotes" -> writeDataInRecyclerView(AllTypeOfQuotes.getTimeQuotes())
            "Positive Quotes" -> writeDataInRecyclerView(AllTypeOfQuotes.getPositiveQuotes())
            "Inspiration Quotes" -> writeDataInRecyclerView(AllTypeOfQuotes.getInspirationQuotes())
            "Best Life Quotes" -> writeDataInRecyclerView(AllTypeOfQuotes.getBestLifeQuotes())
            "Success Quotes" -> writeDataInRecyclerView(AllTypeOfQuotes.getSuccessQuotes())
            "Wisdom Quotes" -> writeDataInRecyclerView(AllTypeOfQuotes.getWisdomQuotes())
            "Relationship Quotes" -> writeDataInRecyclerView(AllTypeOfQuotes.getRelationshipQuotes())
            "Nature Quotes" -> writeDataInRecyclerView(AllTypeOfQuotes.getNatureQuotes())
            "Love Quotes" -> writeDataInRecyclerView(AllTypeOfQuotes.getLoveQuotes())
            "Angry Quotes" -> writeDataInRecyclerView(AllTypeOfQuotes.getAngryQuotes())
            "Motivational Quotes" -> writeDataInRecyclerView(AllTypeOfQuotes.getMotivationalQuotes())
            "Sad Quotes" -> writeDataInRecyclerView(AllTypeOfQuotes.getSadQuotes())
            "Alone Quotes" -> writeDataInRecyclerView(AllTypeOfQuotes.getAloneQuotes())
            "Life Quotes" -> writeDataInRecyclerView(AllTypeOfQuotes.getLifeQuotes())
            "Attitude Quotes" -> writeDataInRecyclerView(AllTypeOfQuotes.getAttitudeQuotes())
            "Trust Quotes" -> writeDataInRecyclerView(AllTypeOfQuotes.getTrustQuotes())
            "Friendship Quotes" -> writeDataInRecyclerView(AllTypeOfQuotes.getFriendshipQuotes())
            "Leadership Quotes" -> writeDataInRecyclerView((AllTypeOfQuotes.getLeadershipQuotes()))
            "Happiness Quotes" -> writeDataInRecyclerView(AllTypeOfQuotes.getHappinessQuotes())
            "Smiling Quotes" -> writeDataInRecyclerView(AllTypeOfQuotes.getSmilingQuotes())

        }
    }

    private fun showDialog() {
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_wait)
        dialog.setCancelable(false)
        dialog.show()
    }

    private fun hideDialog() {
        dialog.dismiss()
    }

    private fun writeDataInRecyclerView(objectFunction: ArrayList<RecyclerViewAllQuotesData>) {
        val allQuotesRecyclerView = findViewById<RecyclerView>(R.id.rv_allQuotes)
        allQuotesRecyclerView.layoutManager =
            LinearLayoutManager(this)
        val allQuotesRecyclerViewData = objectFunction
        val allQuotesAdapter =
            RecyclerViewAllQuotesAdapter(allQuotesRecyclerViewData, this@AllQuotesActivity)
        allQuotesRecyclerView.adapter = allQuotesAdapter
        hideDialog()
    }

    override fun showingDialog(context: Context, quotes: String, author: String) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottom_popup)
        val backArrow = dialog.findViewById<ImageView>(R.id.backArrowPopup)
        val quotesD = dialog.findViewById<TextView>(R.id.tv_allQuotesPopup)
        val authorD = dialog.findViewById<TextView>(R.id.tv_allQuotesAuthorPopup)
        val copy = dialog.findViewById<ImageView>(R.id.iv_dialogCopy)
        val share = dialog.findViewById<ImageView>(R.id.iv_dialogShare)
        val favorite = dialog.findViewById<ImageView>(R.id.iv_dialogFavorite)

        quotesD.text = quotes
        authorD.text = author
        val textToCopy =
            "${quotesD.text}\n:-${authorD.text}\n\nDownload Quotmotiv App On PlayStore For More Exciting Quotes"

        favorite.setOnClickListener {
            //Adding Data to dataBase
            GlobalScope.launch {
                database.quotesFavDAO().insertQuotes(QuotesFav(0, quotes, author))
            }
            Toast.makeText(it.context, "Added to Favorite", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        share.setOnClickListener {
            shareQuotes(it.context, textToCopy)
        }
        copy.setOnClickListener {
            setClipboard(it.context, textToCopy)
            dialog.dismiss()
        }
        backArrow.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show();
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog.window!!.getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.window!!.setGravity(Gravity.BOTTOM);
    }


    private fun setClipboard(context: Context, text: String) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            val clipboard =
                context.getSystemService(CLIPBOARD_SERVICE) as android.text.ClipboardManager
            clipboard.text = text
        } else {
            val clipboard = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Copied Text", text)
            clipboard.setPrimaryClip(clip)
        }
        Toast.makeText(context, "Quotes Copied", Toast.LENGTH_SHORT).show()
    }

    private fun shareQuotes(context: Context, text: String) {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        val intent = Intent.createChooser(shareIntent, text)
        ContextCompat.startActivity(context, intent, null)
    }
}