package com.example.spot.quotmotiv.ui.home.RecyclerViewsHome

import com.example.spot.quotmotiv.Activities.RecyclerViewAllQuotes.RecyclerViewAllQuotesAdapter
import com.example.spot.quotmotiv.Activities.RecyclerViewAllQuotes.RecyclerViewAllQuotesData
import com.example.spot.quotmotiv.R

object CommonData {

    fun getPopularData() : ArrayList<RecyclerViewHomeData>
    {
        val popularDataList = ArrayList<RecyclerViewHomeData>()

        popularDataList.add(RecyclerViewHomeData(R.drawable.love_image,"Love Quotes"))
        popularDataList.add(RecyclerViewHomeData(R.drawable.angry_image,"Angry Quotes"))
        popularDataList.add(RecyclerViewHomeData(R.drawable.motivational_image,"Motivational Quotes"))
        popularDataList.add(RecyclerViewHomeData(R.drawable.sad_image,"Sad Quotes"))
        popularDataList.add(RecyclerViewHomeData(R.drawable.alone_image,"Alone Quotes"))
        popularDataList.add(RecyclerViewHomeData(R.drawable.life_image,"Life Quotes"))
        return popularDataList
    }
    fun getCategoryData() : ArrayList<RecyclerViewHomeData>
    {

        val categoryDataList = ArrayList<RecyclerViewHomeData>()
        categoryDataList.add(RecyclerViewHomeData(R.drawable.time_image,"Time Quotes"))
        categoryDataList.add(RecyclerViewHomeData(R.drawable.positive_image,"Positive Quotes"))
        categoryDataList.add(RecyclerViewHomeData(R.drawable.inspiration_image,"Inspiration Quotes"))
        categoryDataList.add(RecyclerViewHomeData(R.drawable.best_life_image,"Best Life Quotes"))
        categoryDataList.add(RecyclerViewHomeData(R.drawable.success_image,"Success Quotes"))
        categoryDataList.add(RecyclerViewHomeData(R.drawable.wisdom_image,"Wisdom Quotes"))
        categoryDataList.add(RecyclerViewHomeData(R.drawable.relationship_image,"Relationship Quotes"))
        categoryDataList.add(RecyclerViewHomeData(R.drawable.nature_image,"Nature Quotes"))
        categoryDataList.add(RecyclerViewHomeData(R.drawable.love_image,"Love Quotes"))
        categoryDataList.add(RecyclerViewHomeData(R.drawable.angry_image,"Angry Quotes"))
        categoryDataList.add(RecyclerViewHomeData(R.drawable.motivational_image,"Motivational Quotes"))
        categoryDataList.add(RecyclerViewHomeData(R.drawable.sad_image,"Sad Quotes"))
        categoryDataList.add(RecyclerViewHomeData(R.drawable.alone_image,"Alone Quotes"))
        categoryDataList.add(RecyclerViewHomeData(R.drawable.life_image,"Life Quotes"))
        categoryDataList.add(RecyclerViewHomeData(R.drawable.attitude_image,"Attitude Quotes"))
        categoryDataList.add(RecyclerViewHomeData(R.drawable.trust_image,"Trust Quotes"))
        categoryDataList.add(RecyclerViewHomeData(R.drawable.friendship_image,"Friendship Quotes"))
        categoryDataList.add(RecyclerViewHomeData(R.drawable.leadership_image,"Leadership Quotes"))
        categoryDataList.add(RecyclerViewHomeData(R.drawable.happyness_image,"Happiness Quotes"))
        categoryDataList.add(RecyclerViewHomeData(R.drawable.smile_image,"Smiling Quotes"))

        return categoryDataList
    }

}