package com.fahmisbas.dogs.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.fahmisbas.dogs.R
import com.fahmisbas.dogs.util.getProgressDrawable
import com.fahmisbas.dogs.util.loadImage
import com.fahmisbas.dogs.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private var dogUuid = 0
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
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
                dogName.text = dogs.dogBreed
                dogPurpose.text = dogs.bredFor
                dogTemprament.text = dogs.temperament
                dogLifespan.text = dogs.lifeSpan

                context?.let {
                    dogImage.loadImage(dogs.imageUrl, getProgressDrawable(it))
                }
            }
        })
    }

}
