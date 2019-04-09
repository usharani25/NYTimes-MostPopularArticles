package com.demo.nytimes.mostpopular

import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.nytimes.R
import com.demo.nytimes.adapter.MovieAdapter
import com.demo.nytimes.util.nonNull
import com.demo.nytimes.util.observe
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_most_popular.*
import kotlinx.android.synthetic.main.app_bar_most_popular.*
import kotlinx.android.synthetic.main.content_most_popular.*
import androidx.lifecycle.ViewModelProviders
import com.demo.nytimes.models.CustomArticleModel


class MostPopularActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var viewModel: MostPopularViewModel
    private val movies = mutableListOf<CustomArticleModel>()

    private val picasso = lazy {
        Picasso.with(this)
    }

    private val movieAdapter = lazy {
        MovieAdapter(this, movies, picasso.value)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_most_popular)
        setSupportActionBar(toolbar)
        viewModel = ViewModelProviders.of(this).get(MostPopularViewModel::class.java)
        viewModel.makeGetMovieCall()
        rv_most_popular.layoutManager = LinearLayoutManager(this)
        rv_most_popular.setHasFixedSize(true)
        rv_most_popular.adapter = movieAdapter.value
        startListenToMovies()
        startListenToErrors()
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)


    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.most_popular, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onResume() {
        super.onResume()
        parentShimmerLayout.startShimmerAnimation()
    }

    private fun startListenToMovies() {
        viewModel.articles
            .nonNull()
            .observe(this) {
                parentShimmerLayout.visibility = View.GONE
                parentShimmerLayout.stopShimmerAnimation()
                movies.addAll(it)
                movieAdapter.value.notifyDataSetChanged()
            }
    }

    private fun startListenToErrors() {
        viewModel.errors
            .nonNull()
            .observe(this) {
                parentShimmerLayout.stopShimmerAnimation()
                Toast.makeText(this, "Error occurred in fetching movies!!", Toast.LENGTH_LONG).show()
            }
    }

    override fun onStop() {
        super.onStop()
        parentShimmerLayout.stopShimmerAnimation()
    }
}
