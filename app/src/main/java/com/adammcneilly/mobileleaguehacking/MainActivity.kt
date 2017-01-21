package com.adammcneilly.mobileleaguehacking

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.adammcneilly.mobileleaguehacking.adapters.HackathonAdapter
import com.adammcneilly.mobileleaguehacking.models.Hackathon
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    val testHackathons: MutableList<Hackathon> = ArrayList()

    init {
        val grizzHacks = Hackathon()
        grizzHacks.eventName = "GrizzHacks"
        grizzHacks.date = "March 14TH - 15TH"
        grizzHacks.location = "Rochester, MI"
        grizzHacks.mainImageUrl = "https://s3.amazonaws.com/assets.mlh.io/events/splashes/000/000/392/thumb/930adc5ed398-hackmtyMLH_300x300.png?1467906271"
        grizzHacks.secondaryImageUrl = "https://s3.amazonaws.com/assets.mlh.io/events/logos/000/000/392/thumb/e722cf7c0b4d-hackmtyMLH_100x100.png?1467906270"
        testHackathons.add(grizzHacks)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = HackathonAdapter(testHackathons)
        hackathon_list.adapter = adapter
        hackathon_list.layoutManager = layoutManager
    }
}
