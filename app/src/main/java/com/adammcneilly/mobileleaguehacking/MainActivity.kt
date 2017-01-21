package com.adammcneilly.mobileleaguehacking

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.adammcneilly.mobileleaguehacking.adapters.HackathonAdapter
import com.adammcneilly.mobileleaguehacking.models.Hackathon
import com.adammcneilly.mobileleaguehacking.rest.MLHManager
import kotlinx.android.synthetic.main.content_main.*
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber

class MainActivity : AppCompatActivity() {
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

        // Make call
        val api = MLHManager()
        api.getHackathons()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(object: Subscriber<List<Hackathon>>() {
                    override fun onError(e: Throwable?) {
                        Timber.e(e)
                    }

                    override fun onNext(t: List<Hackathon>?) {
                        if (t != null) {
                            adapter.swapItems(t)
                        }
                    }

                    override fun onCompleted() {
                        //TODO: Add a progress bar and remove it here.
                    }
                })
    }
}
