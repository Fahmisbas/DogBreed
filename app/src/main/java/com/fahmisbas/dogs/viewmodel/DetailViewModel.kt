package com.fahmisbas.dogs.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.fahmisbas.dogs.model.DogBreed
import com.fahmisbas.dogs.model.DogDatabase
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : BaseViewModel(application){

    val dogs = MutableLiveData<DogBreed>()

    fun fetch(uuid:Int) {
        launch {
            val dog = DogDatabase(getApplication()).dogDao().getDog(uuid)
            dogs.value = dog
        }
   }
}