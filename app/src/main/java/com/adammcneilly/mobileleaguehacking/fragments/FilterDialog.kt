package com.adammcneilly.mobileleaguehacking.fragments

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adammcneilly.mobileleaguehacking.R

/**
 * Dialog fragment with filter options for the hackathon list.
 *
 * Created by adam.mcneilly on 1/22/17.
 */
open class FilterDialog: DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.dialog_filter, container, false)

        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setTitle("Filter Hackathons")
        return dialog
    }
}