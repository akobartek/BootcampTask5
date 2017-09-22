package com.android.example.bootcamptask5

import android.app.ActivityOptions
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.transition.TransitionManager
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    private lateinit var author: Pair<View, View>
    private lateinit var data: Pair<View, View>
    private lateinit var category: Pair<View, View>
    private lateinit var description: Pair<View, View>
    private lateinit var list: MutableList<Pair<View, View>>

    private lateinit var first: Pair<View, View>

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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        Picasso.with(this)
                .load(intent.getStringExtra("imageAddress"))
                .into(itemDetailsImage)

        collapsingToolbarLayout.title = intent.getStringExtra("imageDescription")

        author = Pair(authorLabel, authorText)
        data = Pair(dataLabel, dataText)
        category = Pair(categoryLabel, categoryText)
        description = Pair(descriptionLabel, descriptionText)
        list = mutableListOf(author, data, category, description)

        first = author

        authorArrow.setOnClickListener { moveUp(author) }
        dataArrow.setOnClickListener { moveUp(data) }
        categoryArrow.setOnClickListener { moveUp(category) }
        descriptionArrow.setOnClickListener { moveUp(description) }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun moveUp(pair: Pair<View, View>) {
        if (pair == first)
            return

        val constraintSet = ConstraintSet()
        constraintSet.clone(constraintLayout)
        val position = list.indexOf(pair)
        constraintSet.connect(pair.first.id, ConstraintSet.TOP, R.id.personIcon, ConstraintSet.BOTTOM)
        constraintSet.connect(list[0].first.id, ConstraintSet.TOP, pair.second.id, ConstraintSet.BOTTOM)
        list.removeAt(position)
        list.add(0, pair)
        if (position < list.size - 1) {
            constraintSet.connect(list[position + 1].first.id, ConstraintSet.TOP, list[position].second.id, ConstraintSet.BOTTOM)
        }
        TransitionManager.beginDelayedTransition(coordinatorLayout)
        constraintSet.applyTo(constraintLayout)

        first = pair
    }
}
