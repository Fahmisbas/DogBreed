package com.fahmisbas.dogs.model

import io.reactivex.Single
import retrofit2.http.GET

interface DogsApi {

    @GET("DevTides/DogsApi/master/dogs.json")
    fun getDog() : Single<List<DogBreed>>

}