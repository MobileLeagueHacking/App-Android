package com.adammcneilly.mobileleaguehacking.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.adammcneilly.mobileleaguehacking.R
import com.adammcneilly.mobileleaguehacking.fragments.PrizeFragment
import com.adammcneilly.mobileleaguehacking.fragments.ScheduleFragment
import com.adammcneilly.mobileleaguehacking.fragments.SponsorFragment
import com.adammcneilly.mobileleaguehacking.models.Hackathon
import com.adammcneilly.mobileleaguehacking.models.HackathonTemplateResponse
import com.bumptech.glide.Glide
import java.util.*

open class HackathonEventActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var response: HackathonTemplateResponse = HackathonTemplateResponse()
    val menuItems: HashMap<String, Int> = HashMap()

    private var navigationView: NavigationView? = null

    init {
        //Default menu items to display.
        //TODO: Continue to hardcode or pull these?
        menuItems.put(SCHEDULE, R.drawable.ic_calendar_black_24dp)
        menuItems.put(PRIZES, R.drawable.ic_prizes_black_24dp)
        menuItems.put(SPONSORS, R.drawable.ic_sponsor_black_24dp)
        menuItems.put(MAP, R.drawable.ic_sponsor_black_24dp)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hackathon_event)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)


        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        navigationView = findViewById(R.id.nav_view) as? NavigationView
        navigationView?.setNavigationItemSelectedListener(this)

        // Get hackathon
        response = intent.getParcelableExtra(TEMPLATE)
        val hackathon: Hackathon? = response.hackathon
        supportActionBar?.title = hackathon?.name

        // Setup header
        val headerView = navigationView?.getHeaderView(0)
        val headerImage = headerView?.findViewById(R.id.hackathon_logo) as ImageView
        val headerTitle = headerView?.findViewById(R.id.hackathon_name) as TextView
        val headerDate = headerView?.findViewById(R.id.hackathon_date) as TextView

        Glide.with(this).load(hackathon?.logoURL).error(R.drawable.noise).into(headerImage)
        headerTitle.text = hackathon?.name
        headerDate.text = hackathon?.date

        // Setup menu
        val menu = navigationView?.menu
        menuItems.forEach { menu?.add(Menu.NONE, it.key.hashCode(), Menu.NONE, it.key)?.setIcon(it.value) }

        menu?.performIdentifierAction(SCHEDULE.hashCode(), 0)
    }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null

        when (item.title) {
            SPONSORS -> fragment = SponsorFragment.newInstance(response.sponsors)
            SCHEDULE -> fragment = ScheduleFragment.newInstance(response.schedule)
            PRIZES -> fragment = PrizeFragment.newInstance(response.prizes)
            MAP -> {
                val intent = Intent(this, WebviewActivity::class.java)
                intent.putExtra(WebviewActivity.URL, response.hackathon.mapUrl)
                startActivity(intent)
            }
        }

        if (fragment != null) {
            supportFragmentManager.beginTransaction().replace(R.id.content_hackathon_event, fragment).commit()
            supportActionBar?.title = response.hackathon.name + " " + item.title
        }

        setItemChecked(item)

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setItemChecked(item: MenuItem) {
        val menu = navigationView?.menu
        val size = menu?.size() ?: 0
        (0..size - 1).map { menu?.getItem(it) }.forEach { it?.isChecked = (it == item) }
    }

    companion object {
        val TEMPLATE = "Template"
        val SCHEDULE = "Schedule"
        val SPONSORS = "Sponsors"
        val PRIZES = "Prizes"
        val MAP = "Map"
    }
}
