package com.adammcneilly.mobileleaguehacking.activities

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.adammcneilly.mobileleaguehacking.R
import com.adammcneilly.mobileleaguehacking.fragments.SponsorFragment
import com.adammcneilly.mobileleaguehacking.models.Hackathon
import com.adammcneilly.mobileleaguehacking.models.HackathonTemplateResponse
import com.bumptech.glide.Glide
import java.util.*

open class HackathonEventActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var response: HackathonTemplateResponse = HackathonTemplateResponse()
    val menuItems: HashMap<String, Int> = HashMap()

    init {
        //Default menu items to display.
        //TODO: Continue to hardcode or pull these?
        menuItems.put("Schedule", R.drawable.ic_calendar_black_24dp)
        menuItems.put("Prizes", R.drawable.ic_prizes_black_24dp)
        menuItems.put("Sponsors", R.drawable.ic_sponsor_black_24dp)
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

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)

        // Get hackathon
        response = intent.getParcelableExtra(TEMPLATE)
        val hackathon: Hackathon? = response.hackathon
        supportActionBar?.title = hackathon?.name

        // Setup header
        val headerView = navigationView.getHeaderView(0)
        val headerImage = headerView.findViewById(R.id.hackathon_logo) as ImageView
        val headerTitle = headerView.findViewById(R.id.hackathon_name) as TextView
        val headerDate = headerView.findViewById(R.id.hackathon_date) as TextView

        Glide.with(this).load(hackathon?.logoURL).error(R.drawable.noise).into(headerImage)
        headerTitle.text = hackathon?.name
        headerDate.text = hackathon?.date

        // Setup menu
        val menu = navigationView.menu
        menuItems.forEach { menu.add(it.key).setIcon(it.value) }
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
                "Sponsors" -> fragment = SponsorFragment.newInstance(response.sponsors)
        }

        if (fragment != null) {
            supportFragmentManager.beginTransaction().replace(R.id.content_hackathon_event, fragment).commit()
        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    companion object {
        val TEMPLATE = "Template"
    }
}
