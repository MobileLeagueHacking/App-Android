package com.adammcneilly.mobileleaguehacking.fragments

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import com.adammcneilly.mobileleaguehacking.R
import com.adammcneilly.mobileleaguehacking.enums.Region

/**
 * Dialog fragment with filter options for the hackathon list.
 *
 * Created by adam.mcneilly on 1/22/17.
 */
open class FilterDialog: DialogFragment() {

    private var result = FilterDialogResult()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.dialog_filter, container, false)

        val northAmericaButton = view?.findViewById(R.id.north_america) as RadioButton
        northAmericaButton.setOnCheckedChangeListener { button, checked ->
            result.region = if (checked) Region.NORTH_AMERICA else Region.EUROPE
        }

        val submitButton = view?.findViewById(R.id.submit) as Button
        submitButton.setOnClickListener {
            (activity as? OnFilteredListener)?.filtered(result)
            dismiss()
        }

        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setTitle("Filter Hackathons")
        return dialog
    }

    open class FilterDialogResult {
        var region: Region = Region.NORTH_AMERICA
    }

    interface OnFilteredListener {
        fun filtered(result: FilterDialogResult)
    }
}