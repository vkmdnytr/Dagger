package com.example.moviedagger.ui.genres

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.viewpager.widget.PagerAdapter
import com.example.moviedagger.R
import com.example.moviedagger.model.entitiy.MovieType


class MovieAdapter(models: List<MovieType>, context: Context) :
    PagerAdapter() {
    private var models: List<MovieType> = models
    private lateinit var layoutInflater: LayoutInflater
    private val context: Context = context
    override fun getCount(): Int {
        return models.size
    }


    fun updateDataSource(models:List<MovieType>){
        this.models = models
        notifyDataSetChanged()
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(context)
        val view: View =layoutInflater.inflate(R.layout.item_genre, container, false)
        val imageView: ImageView
        val title: TextView
        val desc: TextView
        imageView = view.findViewById(R.id.image)
        title = view.findViewById(R.id.title)
        desc = view.findViewById(R.id.desc)
        title.text= models[position].name
        view.setOnClickListener {

            val goToMoviList =  MovieFragmentDirections.actionMovieFragmentToMovieListFragment(models[position]?.id)
            view.findNavController().navigate(goToMoviList)

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