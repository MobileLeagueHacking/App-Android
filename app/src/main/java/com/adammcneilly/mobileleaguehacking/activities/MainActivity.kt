package com.adammcneilly.mobileleaguehacking.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.adammcneilly.mobileleaguehacking.R
import com.adammcneilly.mobileleaguehacking.adapters.HackathonAdapter
import com.adammcneilly.mobileleaguehacking.enums.Region
import com.adammcneilly.mobileleaguehacking.fragments.FilterDialog
import com.adammcneilly.mobileleaguehacking.models.Hackathon
import com.adammcneilly.mobileleaguehacking.rest.MLHManager
import kotlinx.android.synthetic.main.content_main.*
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber
import java.util.*

open class MainActivity : AppCompatActivity(), FilterDialog.OnFilteredListener {
    /**
     * An adapter used to display the list of Hackathons.
     */
    val adapter = HackathonAdapter()

    /**
     * A list of all hackathons pulled from the server, that can be filtered.
     */
    var allHackathons: List<Hackathon> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        hackathon_list.adapter = adapter
        hackathon_list.layoutManager = layoutManager

        refresh_layout.setOnRefreshListener { getHackathons(Region.NORTH_AMERICA) }

        getHackathons(Region.NORTH_AMERICA)
    }

    /**
     * Retrieves and displays all hackathons for a region
     */
    private fun getHackathons(region: Region) {
        // If this was called via the refresh layout, don't bother to show progress bar too
        if (!refresh_layout.isRefreshing) {
            progress_bar.visibility = View.VISIBLE
        }

        // Make call
        val api = MLHManager()
        api.getHackathons(region.code)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(object: Subscriber<List<Hackathon>>() {
                    override fun onError(e: Throwable?) {
                        Timber.e(e)
                        progress_bar.visibility = View.GONE
                        refresh_layout.isRefreshing = false
                    }

                    override fun onNext(t: List<Hackathon>?) {
                        if (t != null) {
                            allHackathons = t
                            adapter.swapItems(allHackathons)
                        }
                    }

                    override fun onCompleted() {
                        progress_bar.visibility = View.GONE
                        refresh_layout.isRefreshing = false
                    }
                })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_filter -> {
                FilterDialog().show(supportFragmentManager, "Filter")
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun filtered(result: FilterDialog.FilterDialogResult) {
        getHackathons(result.region)
    }
}
