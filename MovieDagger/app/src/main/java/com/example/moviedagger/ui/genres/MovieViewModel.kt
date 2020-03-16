package com.example.moviedagger.ui.genres

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedagger.common.OutCome
import com.example.moviedagger.model.entitiy.MovieGenesList
import com.example.moviedagger.model.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel@Inject constructor(private val dataRepository: DataRepository):ViewModel(){

    private val _movieGenesLiveData = MutableLiveData<OutCome<MovieGenesList>>()
    val movieGenesLiveData: LiveData<OutCome<MovieGenesList>>
        get() = _movieGenesLiveData

    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean>
        get() = _showProgress


    fun getMovieGenderList() {
        viewModelScope.launch(Dispatchers.IO) {
            _showProgress.postValue(true)
            val resultItem = dataRepository.fetchMovieGenderList()
            _movieGenesLiveData.postValue(resultItem)
            _showProgress.postValue(false)
        }
    }



}