package com.thesis.distanceguard.presentation.team

import android.view.View
import com.thesis.distanceguard.R
import com.thesis.distanceguard.presentation.base.BaseFragment
import timber.log.Timber

/**
 * Created by Viet Hua on 04/10/2021.
 */

class TeamFragment : BaseFragment() {
    override fun getResLayoutId(): Int {
        return R.layout.fragment_team
    }

    override fun onMyViewCreated(view: View) {
        Timber.d("onMyViewCreated")
    }
}