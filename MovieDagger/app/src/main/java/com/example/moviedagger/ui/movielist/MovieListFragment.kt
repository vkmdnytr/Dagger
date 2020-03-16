package com.example.moviedagger.ui.movielist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviedagger.R
import com.example.moviedagger.common.OutCome
import com.example.moviedagger.common.listener.OnAdapterItemListener
import com.example.moviedagger.ext.showAlert
import com.example.moviedagger.model.IMAGE_URL
import com.example.moviedagger.model.entitiy.Page
import com.example.moviedagger.model.entitiy.Result
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.item_movie.*
import kotlinx.android.synthetic.main.movie_list_fragment.*
import javax.inject.Inject


class MovieListFragment : Fragment(), OnAdapterItemListener {


    private val movieListArgs: MovieListFragmentArgs? by navArgs()
    @Inject
    lateinit var viewMovieListFactory: ViewModelProvider.Factory

    companion object {
        fun newInstance() =
            MovieListFragment()
    }

    private lateinit var movieListViewModel: MovieListViewModel
    private lateinit var moviePageData:  List<Result>
    private lateinit var movieAdapter: MovieListAdapter


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        @Suppress("DEPRECATION")
        movieListViewModel = ViewModelProviders.of(this, viewMovieListFactory).get(MovieListViewModel::class.java)

        toolBarBackButton.setOnClickListener {
            view.findNavController().navigateUp()
        }

        movieAdapter = MovieListAdapter(emptyList(), requireContext(),this)
        viewPagerMovie.adapter = movieAdapter
        viewPagerMovie.setPadding(130, 0, 130, 0)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        movieListViewModel.showProgress.observe(viewLifecycleOwner, Observer {
//            it?.let {
//                progressBar.isVisible = it
//            }
//        })
        movieListViewModel.movieLiveData.observe(viewLifecycleOwner, Observer { result->
            handleMovieListResult(result)
        })

        movieListViewModel.getMovieList(movieListArgs?.gender?:0)

        viewPagerMovie.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                val item=moviePageData[position]
                var imgUri= IMAGE_URL +item.poster_path
                if (imgUri.startsWith("http://"))
                    imgUri = imgUri.replace("http://", "https://")
                val requestOptions = RequestOptions()
                requestOptions.placeholder(R.drawable.ic_launcher_background)
                requestOptions.error(R.drawable.icon_popcorn_3)
                Log.d("IMAGE1","$imgUri")

                Glide.with(requireActivity())
                    .load(imgUri)
                    .into(imgBackground)

                var imgUrl= IMAGE_URL +item.backdrop_path
                if (imgUrl.startsWith("http://"))
                    imgUrl = imgUrl.replace("http://", "https://")

                requestOptions.placeholder(R.drawable.ic_launcher_background)
                requestOptions.error(R.drawable.icon_popcorn_3)
                Glide.with(requireActivity())
                    .load(imgUrl)
                    .circleCrop()
                    .into(img_card)

                Log.d("IMAGE2","$imgUri")
                tv_title.text=item.title
                tv_date.text=item.release_date
                tv_amount.text=item.vote_average.toString()
            }

            override fun onPageSelected(position: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
        })


    }

    private fun handleMovieListResult(result: OutCome<Page>) {
        when (result) {
            is OutCome.Success -> handleMovieListSuccessResult(result.value)
            is OutCome.Error -> {
                handleError(result.message)
            }
        }
    }

    private fun handleMovieListSuccessResult(page: Page) {
        val total=page.total_pages
        val totalResult=page.total_results
        val pages= page.page

        moviePageData =page.results
        movieAdapter.updateDataSource(moviePageData)

    }

    private fun handleError(message: String) {
        activity?.showAlert(message)
    }

    override fun onAdapterItemClick(item: Result) {




    }

}