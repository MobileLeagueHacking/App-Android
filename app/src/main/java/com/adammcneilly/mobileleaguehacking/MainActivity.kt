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
import java.util.*

class MainActivity : AppCompatActivity() {
    val adapter = HackathonAdapter()
    val testHackathons: MutableList<Hackathon> = ArrayList()

    init {
        val grizzHacks = Hackathon()
        grizzHacks.name = "GrizzHacks"
        grizzHacks.date = "March 14TH - 15TH"
        grizzHacks.location = "Rochester, MI"
        grizzHacks.imageURL = "https://s3.amazonaws.com/assets.mlh.io/events/splashes/000/000/392/thumb/930adc5ed398-hackmtyMLH_300x300.png?1467906271"
        grizzHacks.logoURL = "https://s3.amazonaws.com/assets.mlh.io/events/logos/000/000/392/thumb/e722cf7c0b4d-hackmtyMLH_100x100.png?1467906270"
        testHackathons.add(grizzHacks)

        adapter.swapItems(testHackathons)
    }

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
                        //TODO:
                    }
                })
    }
}
