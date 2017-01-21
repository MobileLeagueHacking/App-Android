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
        grizzHacks.mainImageUrl = "http://i.imgur.com/1DONLl1.png"
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
