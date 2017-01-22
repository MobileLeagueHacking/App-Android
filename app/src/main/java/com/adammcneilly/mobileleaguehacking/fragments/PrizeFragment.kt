package com.adammcneilly.mobileleaguehacking.fragments

import android.os.Bundle
import com.adammcneilly.mobileleaguehacking.adapters.BaseAdapter
import com.adammcneilly.mobileleaguehacking.adapters.PrizeAdapter
import com.adammcneilly.mobileleaguehacking.models.Prize
import java.util.*

/**
 * Displays prizes for an event.
 *
 * Created by adam.mcneilly on 1/22/17.
 */
open class PrizeFragment: BaseListFragment<Prize>() {

    override fun getAdapter(): BaseAdapter<Prize, *> {
        return PrizeAdapter(items)
    }

    companion object {
        fun newInstance(prizes: List<Prize>): PrizeFragment {
            val bundle = Bundle()
            bundle.putParcelableArrayList(ITEMS, ArrayList(prizes))

            val fragment = PrizeFragment()
            fragment.arguments = bundle

            return fragment
        }
    }
}