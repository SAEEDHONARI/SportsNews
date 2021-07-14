package com.example.baloot_saeedhonari.ui.fragments.personal.bottom_sheet

import android.app.Dialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baloot_saeedhonari.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FragmentSheetDialog : BottomSheetDialogFragment() {

    companion object {
        fun newInstance() = FragmentSheetDialog()
    }
    private lateinit var behavior: BottomSheetBehavior<View>
    private lateinit var dialog: BottomSheetDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sheet_dialog_fragment, container, false)
    }



    override fun setupDialog(dialog: Dialog, style: Int) {
        dialog.apply {
            setContentView(R.layout.fragment_sheet_dialog_fragment)
        }
    }


}