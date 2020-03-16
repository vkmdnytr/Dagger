package com.example.moviedagger.ui.movielist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.viewpager.widget.PagerAdapter
import com.example.moviedagger.R
import com.example.moviedagger.common.listener.OnAdapterItemListener
import com.example.moviedagger.model.entitiy.Result
import com.example.moviedagger.ui.genres.MovieFragmentDirections

class MovieListAdapter(models: List<Result>, context: Context,var  onAdapterItemListener: OnAdapterItemListener) :
    PagerAdapter()  {
    private var models: List<Result> = models
    private lateinit var layoutInflater: LayoutInflater
    private val context: Context = context

    override fun getCount(): Int {
        return models.size
    }


    fun updateDataSource(models:List<Result>){
        this.models = models
        notifyDataSetChanged()
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(context)
        val view: View =layoutInflater.inflate(R.layout.item_movie_detail, container, false)

//        val tv_details_amount: TextView= view.findViewById(R.id.tv_details_amount)
        val tv_details_title: TextView=view.findViewById(R.id.tv_details_title)
        val tv_details_subtitle: TextView=view.findViewById(R.id.tv_details_subtitle)
        tv_details_title.text= models[position].original_title
        tv_details_subtitle.text= models[position].overview
//        tv_details_amount.text= models[position].vote_average.toString()
        view.setOnClickListener {
            onAdapterItemListener.onAdapterItemClick(models[position])
        }
        container.addView(view, 0)
        return view
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view.equals(`object`)
    }

    override fun destroyItem(
        container: ViewGroup,
        position: Int,
        `object`: Any
    ) {
        container.removeView(`object` as View)
    }

}