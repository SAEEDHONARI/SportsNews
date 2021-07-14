package com.example.baloot_saeedhonari.ui.fragments.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.baloot_saeedhonari.R
import com.example.baloot_saeedhonari.ui.fragments.personal.bottom_sheet.FragmentSheetDialog
import com.example.baloot_saeedhonari.util.GITHUB_LINK
import com.example.baloot_saeedhonari.util.GMAIL_LINK
import com.example.baloot_saeedhonari.util.INSTAGRAM_LINK
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.personal_info_fragment.*


@AndroidEntryPoint
class PersonalInfoFragment : Fragment() {
    private val viewModel: PersonalInfoViewModel by viewModels()

    companion object {
        fun newInstance() = PersonalInfoFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.personal_info_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Ic_github.setOnClickListener {
            viewModel.OpenLink(GITHUB_LINK)
        }
        Ic_instagram.setOnClickListener {
            viewModel.OpenLink(INSTAGRAM_LINK)
        }
        Ic_gmail.setOnClickListener {
            viewModel.SendEmail(GMAIL_LINK)
        }

        viewModel.activityToStart.observe(requireActivity(), Observer {
            requireActivity().startActivity(it)
        })

        btn_about_me.setOnClickListener {
            val bottomSheetFragment = FragmentSheetDialog()
            bottomSheetFragment.show(requireActivity().supportFragmentManager, bottomSheetFragment.tag)
        }
    }


}