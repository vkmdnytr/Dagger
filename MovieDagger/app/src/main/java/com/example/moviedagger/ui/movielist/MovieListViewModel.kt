package com.example.moviedagger.ui.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedagger.common.OutCome
import com.example.moviedagger.model.entitiy.Page
import com.example.moviedagger.model.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListViewModel  @Inject constructor(private val dataRepository: DataRepository):ViewModel(){

    private val _movieLiveData = MutableLiveData<OutCome<Page>>()
    val movieLiveData: LiveData<OutCome<Page>>
        get() = _movieLiveData

    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean>
        get() = _showProgress


    fun getMovieList(genres:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _showProgress.postValue(true)
            val resultItem = dataRepository.fetchGenderList(genres = genres)
            _movieLiveData.postValue(resultItem)
            _showProgress.postValue(false)
        }
    }

}