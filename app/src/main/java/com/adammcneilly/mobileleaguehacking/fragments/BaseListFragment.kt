package com.adammcneilly.mobileleaguehacking.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adammcneilly.mobileleaguehacking.DividerItemDecoration
import com.adammcneilly.mobileleaguehacking.R
import com.adammcneilly.mobileleaguehacking.models.BaseModel
import java.util.*

/**
 * Base class for displaying a list of BaseModels.
 *
 * Created by adam.mcneilly on 1/22/17.
 */
abstract class BaseListFragment<T: BaseModel>: Fragment() {
    protected var items: List<T> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        items = arguments.getParcelableArrayList(ITEMS)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_list, container, false)

        val adapter = getAdapter()
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val recyclerView = view?.findViewById(R.id.item_list) as RecyclerView
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        return view
    }

    abstract fun getAdapter(): RecyclerView.Adapter<*>

    companion object {
        val ITEMS = "Items"
    }
}