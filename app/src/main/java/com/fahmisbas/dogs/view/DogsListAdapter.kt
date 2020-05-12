package com.fahmisbas.dogs.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.fahmisbas.dogs.R
import com.fahmisbas.dogs.model.DogBreed
import com.fahmisbas.dogs.util.getProgressDrawable
import com.fahmisbas.dogs.util.loadImage
import kotlinx.android.synthetic.main.item_dog.view.*

class DogsListAdapter(private val dogsList: ArrayList<DogBreed>) : RecyclerView.Adapter<DogsListAdapter.DogViewHolder>() {


    fun updateDogList(newDogsList: List<DogBreed>) {
        dogsList.clear()
        dogsList.addAll(newDogsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsListAdapter.DogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dog, parent, false)
        return DogViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dogsList.size
    }

    override fun onBindViewHolder(holder: DogsListAdapter.DogViewHolder, position: Int) {

        holder.itemView.name.text = dogsList[position].dogBreed
        holder.itemView.lifespan.text = dogsList[position].lifeSpan

        holder.itemView.setOnClickListener(View.OnClickListener {
            val action = ListFragmentDirections.actionDetailFragment()
            action.dogUuid = dogsList[position].uuid
            Navigation.findNavController(it).navigate(action)
        })

        holder.itemView.imageView.loadImage(dogsList[position].imageUrl, getProgressDrawable(holder.itemView.imageView.context))
    }

    class DogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}