package com.example.spot.quotmotiv.ui.home

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spot.quotmotiv.R
import com.example.spot.quotmotiv.databinding.FragmentHomeBinding
import com.example.spot.quotmotiv.ui.PressedAnimation
import com.example.spot.quotmotiv.ui.home.RecyclerViewsHome.CommonData
import com.example.spot.quotmotiv.ui.home.RecyclerViewsHome.RecyclerViewHorizontalAdapter
import com.example.spot.quotmotiv.ui.home.RecyclerViewsHome.RecyclerViewVerticalAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.naniagtionBarCopyright.setOnClickListener {
            PressedAnimation.addAnimation(it)
            showBottomDialog(root,)
        }
        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //horizontal RecyclerView
        val horizontalRecyclerView = binding.rvHorizontalHome
        horizontalRecyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        val horizontalRecyclerViewData = CommonData.getPopularData()
        val horizontalAdapter = RecyclerViewHorizontalAdapter(horizontalRecyclerViewData)
        horizontalRecyclerView.adapter = horizontalAdapter

        //vertical RecyclerView
        val verticalRecyclerView = binding.rvVerticalHome
        verticalRecyclerView.layoutManager = GridLayoutManager(requireActivity(), 2)
        val verticalRecyclerViewData = CommonData.getCategoryData()

        val verticalAdapter = RecyclerViewVerticalAdapter(verticalRecyclerViewData)
        verticalRecyclerView.adapter = verticalAdapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

     private fun showBottomDialog(view: View) {
        val dialog = Dialog(view.context,R.style.DialogAnimation)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.home_top_popup)
        val backArrow = dialog.findViewById<ImageView>(R.id.backArrowPopupHome)

        backArrow.setOnClickListener {
            PressedAnimation.addAnimation(view)
            dialog.dismiss()
        }
        dialog.show()
         //it is used for setting the dialog length and breath
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog.window!!.getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.window!!.setGravity(Gravity.BOTTOM);
    }

}