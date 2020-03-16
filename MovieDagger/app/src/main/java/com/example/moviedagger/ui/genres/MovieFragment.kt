@file:Suppress("DEPRECATION")

package com.example.moviedagger.ui.genres

import android.animation.ArgbEvaluator
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.moviedagger.R
import com.example.moviedagger.common.OutCome
import com.example.moviedagger.ext.showAlert
import com.example.moviedagger.model.entitiy.MovieGenesList
import com.example.moviedagger.model.entitiy.MovieType
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_movie.*
import javax.inject.Inject


class MovieFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var movieViewModel : MovieViewModel

    private lateinit var movieGenesListData: List<MovieType>
    private lateinit var movieAdapter: MovieAdapter

    var argbEvaluator: ArgbEvaluator = ArgbEvaluator()

    companion object {
        fun newInstance() : MovieFragment {
            return MovieFragment()
        }
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieViewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel::class.java)

        movieAdapter = MovieAdapter(emptyList(), requireContext())
        viewPager.adapter = movieAdapter
        viewPager.setPadding(130, 0, 130, 0)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        movieViewModel.getMovieGenderList()
        movieViewModel.showProgress.observe(viewLifecycleOwner, Observer {
            it?.let {
                progressBar.isVisible = it
            }
        })
        movieViewModel.movieGenesLiveData.observe(viewLifecycleOwner, Observer { result->
            handleMovieListResult(result)
        })




        val colors_temp = arrayOf(
            ContextCompat.getColor(requireContext(),
                R.color.black),
            ContextCompat.getColor(requireContext(),
                R.color.yellow),
            ContextCompat.getColor(requireContext(),
                R.color.Red),
            ContextCompat.getColor(requireContext(),
                R.color.blue),
                    ContextCompat.getColor(requireContext(),
            R.color.green),
                            ContextCompat.getColor(requireContext(),
            R.color.TextOnFill)
        )


        viewPager.setOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                val colorPosition= position.rem(4)
                viewPager.setBackgroundColor(
                    (argbEvaluator.evaluate(
                        positionOffset,
                        colors_temp[colorPosition],
                        colors_temp[colorPosition+1]
                    ) as Int)
                )
            }

            override fun onPageSelected(position: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private fun handleMovieListResult(result: OutCome<MovieGenesList>) {
        when (result) {
            is OutCome.Success -> handleMovieListSuccessResult(result.value)
            is OutCome.Error -> {
                handleError(result.message)
            }
        }
    }

    private fun handleMovieListSuccessResult(movieGenesList: MovieGenesList) {
        movieGenesListData =movieGenesList.genres
        movieAdapter.updateDataSource(movieGenesListData)
    }

    private fun handleError(message: String) {
        activity?.showAlert(message)
    }
}