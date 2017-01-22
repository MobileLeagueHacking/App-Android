package com.adammcneilly.mobileleaguehacking.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.adammcneilly.mobileleaguehacking.R
import com.adammcneilly.mobileleaguehacking.models.Prize

/**
 * Displays a list of prizes.
 *
 * Created by adam.mcneilly on 1/22/17.
 */
open class PrizeAdapter: BaseAdapter<Prize, PrizeAdapter.PrizeViewHolder> {

    constructor(): super()

    constructor(items: List<Prize>): super(items)

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PrizeViewHolder {
        val context = parent?.context
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_prize, parent, false)
        return PrizeViewHolder(view)
    }

    open class PrizeViewHolder(view: View): BaseAdapter.BaseViewHolder<Prize>(view) {
        private var prizeName: TextView? = null

        init {
            prizeName = view.findViewById(R.id.prize_name) as? TextView
        }

        override fun bindItem(item: Prize) {
            prizeName?.text = item.description
        }
    }
}