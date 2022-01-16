package com.example.spot.quotmotiv.ui.Favorite

import android.app.AlertDialog
import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.spot.quotmotiv.DataBase.QuotesFav
import com.example.spot.quotmotiv.DataBase.QuotesFavDataBase
import com.example.spot.quotmotiv.R
import com.example.spot.quotmotiv.databinding.FragmentFavoriteBinding
import com.example.spot.quotmotiv.ui.Favorite.RecyclerViewFavorite.RecyclerViewFavoriteAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoriteFragment : Fragment(), RecyclerViewFavoriteAdapter.listner {

    private var _binding: FragmentFavoriteBinding? = null
    private lateinit var database: QuotesFavDataBase
    private lateinit var adapter: RecyclerViewFavoriteAdapter
    private lateinit var favoriteRecyclerView: RecyclerView

    // This property is only valid between onCreateView and
    // onDestroyView
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteRecyclerView = binding.rvFavorite
        favoriteRecyclerView.layoutManager =
            LinearLayoutManager(
                requireActivity(),
                LinearLayoutManager.VERTICAL, false
            )

        //initializing  data from the database
        database = Room.databaseBuilder(
            requireContext().applicationContext,
            QuotesFavDataBase::class.java, "quotesFavDB"
        ).build()

        //getting data
        getData(view)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getData(view: View) {
        database.quotesFavDAO().getAllQuotes().observe(requireActivity(), {
            adapter = RecyclerViewFavoriteAdapter(it, this@FavoriteFragment)
            favoriteRecyclerView.adapter = adapter
        })

    }


    override fun showBottomDialog(view: View, quotes: String, quotesAuthor: String) {
        val dialog = Dialog(view.context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.fav_bottom_popup)
        val backArrow = dialog.findViewById<ImageView>(R.id.fav_backArrowPopup)
        val quotesD = dialog.findViewById<TextView>(R.id.tv_favQuotesPopup)
        val authorD = dialog.findViewById<TextView>(R.id.tv_favQuotesAuthorPopup)
        val copy = dialog.findViewById<ImageView>(R.id.iv_dialogCopyFav)
        val share = dialog.findViewById<ImageView>(R.id.iv_dialogShareFav)

        quotesD.text = quotes
        authorD.text = quotesAuthor
        val textToCopy =
            "${quotesD.text}\n:-${authorD.text}\n\nDownload Quotmotiv App On PlayStore For More Exciting Quotes"

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

    override fun deleteClicked(deleteQuote: QuotesFav) {
        deleteWarningPopup(deleteQuote)
    }

    private fun setClipboard(context: Context, text: String) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            val clipboard =
                context.getSystemService(Context.CLIPBOARD_SERVICE) as android.text.ClipboardManager
            clipboard.text = text
        } else {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
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

    private fun deleteWarningPopup(deleteQuote: QuotesFav) {
        val customDialog = Dialog(requireContext())
        customDialog.setCancelable(false)
        customDialog.setContentView(R.layout.delete_warning_layout)
        val yes = customDialog.findViewById<Button>(R.id.tvYes)
        val no = customDialog.findViewById<Button>(R.id.tvNo)
        yes.setOnClickListener {
            deleteFavQuotes(deleteQuote)
            customDialog.dismiss() // Dialog will be dismissed
        }
        no.setOnClickListener {
            customDialog.dismiss()
        }

        customDialog.show()
    }

    //deleting from database
    private fun deleteFavQuotes(deleteQuote: QuotesFav) {
        GlobalScope.launch {
            database.quotesFavDAO().deleteQuotes(deleteQuote)
        }
    }
}