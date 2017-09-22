package com.android.example.bootcamptask5

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

class MainAdapter(val activity: MainActivity) : RecyclerView.Adapter<MainAdapter.Holder>() {

    private var imageList: List<MyImage> = listOf(
            MyImage("https://unsplash.it/600/400?image=38", "Chmura"),
            MyImage("https://unsplash.it/600/400?image=10", "Las i jezioro"),
            MyImage("https://unsplash.it/600/400?image=11", "Widoczek"),
            MyImage("https://unsplash.it/600/400?image=12", "Kamienie"),
            MyImage("https://unsplash.it/600/400?image=13", "Plaża"),
            MyImage("https://unsplash.it/600/400?image=14", "Morze"),
            MyImage("https://unsplash.it/600/400?image=16", "Wodospad"),
            MyImage("https://unsplash.it/600/400?image=17", "Ocean"),
            MyImage("https://unsplash.it/600/400?image=18", "Droga"),
            MyImage("https://unsplash.it/600/400?image=19", "Trawa"),
            MyImage("https://unsplash.it/600/400?image=20", "Biurko"),
            MyImage("https://unsplash.it/600/400?image=30", "Kubek"),
            MyImage("https://unsplash.it/600/400?image=34", "Beczka"),
            MyImage("https://unsplash.it/600/400?image=32", "ławka")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            Holder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false), activity)

    override fun onBindViewHolder(holder: Holder, position: Int) =
            holder.bind(imageList[position])

    override fun getItemCount(): Int = imageList.size


    class Holder(mainView: View, val activity: MainActivity) : RecyclerView.ViewHolder(mainView) {

        fun bind(image: MyImage) {
            Log.d("holder_tag", image.imageAddress)
            Picasso.with(itemView.context).load(image.imageAddress).into(itemView.itemImage)
            itemView.itemDescription.text = image.imageDescription

            itemView.setOnClickListener { DetailsActivity.startActivity(activity, image, itemView.itemImage) }
        }
    }
}