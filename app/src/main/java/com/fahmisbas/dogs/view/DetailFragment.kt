package com.fahmisbas.dogs.view

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

import com.fahmisbas.dogs.R
import com.fahmisbas.dogs.databinding.FragmentDetailBinding
import com.fahmisbas.dogs.model.DogPalette
import com.fahmisbas.dogs.util.getProgressDrawable
import com.fahmisbas.dogs.util.loadImage
import com.fahmisbas.dogs.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment() : Fragment() {

    private var dogUuid = 0
    private lateinit var viewModel: DetailViewModel

    private lateinit var dataBinding: FragmentDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            dogUuid = DetailFragmentArgs.fromBundle(it).dogUuid
        }

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.fetch(dogUuid)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.dogs.observe(this, Observer { dogs ->
            dogs?.let {it ->
                dataBinding.dog = dogs

                it.imageUrl?.let {
                    setupBackgroundColor(it)
                }
            }
        })
    }

    private fun setupBackgroundColor(url : String) {
        Glide.with(this)
            .asBitmap()
            .load(url)
            .into(object : CustomTarget<Bitmap>(){
                override fun onLoadCleared(placeholder: Drawable?) {}
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    Palette.from(resource)
                        .generate {
                            val intColor = it?.vibrantSwatch?.rgb ?: 0
                            val myPalette = DogPalette(intColor)
                            dataBinding.palette = myPalette
                        }
                }

            })
    }

}
