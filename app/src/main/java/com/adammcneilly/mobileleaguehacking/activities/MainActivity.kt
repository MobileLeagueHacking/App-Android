package com.adammcneilly.mobileleaguehacking.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import com.adammcneilly.mobileleaguehacking.R
import com.adammcneilly.mobileleaguehacking.adapters.HackathonAdapter
import com.adammcneilly.mobileleaguehacking.models.Hackathon
import com.adammcneilly.mobileleaguehacking.rest.MLHManager
import kotlinx.android.synthetic.main.content_main.*
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber

open class MainActivity : AppCompatActivity() {
    /**
     * An adapter used to display the list of Hackathons.
     */
    val adapter = HackathonAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        hackathon_list.adapter = adapter
        hackathon_list.layoutManager = layoutManager

        refresh_layout.setOnRefreshListener { getHackathons() }

        getHackathons()
    }

    /**
     * Retrieves and displays all hackathons.
     */
    private fun getHackathons() {
        // If this was called via the refresh layout, don't bother to show progress bar too
        if (!refresh_layout.isRefreshing) {
            progress_bar.visibility = View.VISIBLE
        }

        // Make call
        val api = MLHManager()
        api.getHackathons("na")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(object: Subscriber<List<Hackathon>>() {
                    override fun onError(e: Throwable?) {
                        Timber.e(e)
                        progress_bar.visibility = View.GONE
                        refresh_layout.isRefreshing = false
                    }

                    override fun onNext(t: List<Hackathon>?) {
                        if (t != null) adapter.swapItems(t)
                    }

                    override fun onCompleted() {
                        progress_bar.visibility = View.GONE
                        refresh_layout.isRefreshing = false
                    }
                })
    }
}
