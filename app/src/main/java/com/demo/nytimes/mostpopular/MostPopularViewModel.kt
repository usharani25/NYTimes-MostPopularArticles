package com.demo.nytimes.mostpopular

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.demo.nytimes.data.RetrofitClient
import com.demo.nytimes.models.AllSectionModel
import com.demo.nytimes.models.CustomArticleModel
import com.demo.nytimes.util.NonNullMediatorLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MostPopularViewModel: ViewModel() {



    private val _articles = NonNullMediatorLiveData<List<CustomArticleModel>>()
    private val _errors = NonNullMediatorLiveData<Throwable>()

    val articles: LiveData<List<CustomArticleModel>> = _articles
    val errors: LiveData<Throwable> = _errors

    private var moviesCall: Call<AllSectionModel>? = null

    fun makeGetMovieCall() {
        moviesCall = RetrofitClient.getApiService()
            .getPopular(RetrofitClient.api_key)
        moviesCall?.enqueue(object : Callback<AllSectionModel> {
            override fun onFailure(call: Call<AllSectionModel>, t: Throwable) {
                _errors.value = t
            }

            override fun onResponse(call: Call<AllSectionModel>, response: Response<AllSectionModel>) {
                val body: AllSectionModel? = response.body()!!
                if (response.isSuccessful && body != null) {
                    val tempMovies = mutableListOf<CustomArticleModel>()
                    val size = body.results.size-1
                    for (i in 0..size)
                        tempMovies.add(CustomArticleModel(body.results[i].title,body.results[i].byline,body.results[i].media[0].mediaMetadata[0].url,body.results[i].publishedDate))
                    _articles.value = tempMovies
                }
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        moviesCall?.cancel()
    }
}