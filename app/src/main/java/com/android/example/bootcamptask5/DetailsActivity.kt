package com.android.example.bootcamptask5

import android.app.ActivityOptions
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    companion object {
        fun startActivity(activity: AppCompatActivity, image: MyImage, view: View) {
            val intent = Intent(activity, DetailsActivity::class.java)
            val options = ActivityOptions.makeSceneTransitionAnimation(activity, view, "image").toBundle()
            intent.putExtra("imageAddress", image.imageAddress)
            intent.putExtra("imageDescription", image.imageDescription)
            activity.startActivity(intent, options)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(toolbar)

        Picasso.with(this)
                .load(intent.getStringExtra("imageAddress"))
                .into(itemDetailsImage)

        collapsingToolbarLayout.title = intent.getStringExtra("imageDescription")
    }
}
