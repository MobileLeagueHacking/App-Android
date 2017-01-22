package com.adammcneilly.mobileleaguehacking.fragments

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.adammcneilly.mobileleaguehacking.adapters.SponsorAdapter
import com.adammcneilly.mobileleaguehacking.models.Sponsor
import java.util.*

/**
 * Fragment that displays a list of sponsors in the app.
 *
 * Created by adam.mcneilly on 1/21/17.
 */
open class SponsorFragment: BaseListFragment<Sponsor>() {

    override fun getAdapter(): RecyclerView.Adapter<*> {
        return SponsorAdapter(items)
    }

    companion object {
        fun newInstance(sponsors: List<Sponsor>): SponsorFragment {
            val bundle = Bundle()
            bundle.putParcelableArrayList(ITEMS, ArrayList(sponsors))

            val fragment = SponsorFragment()
            fragment.arguments = bundle

            return fragment
        }
    }
}