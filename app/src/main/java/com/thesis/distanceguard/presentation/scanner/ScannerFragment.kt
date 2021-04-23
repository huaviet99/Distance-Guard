package com.thesis.distanceguard.presentation.scanner

import android.view.View
import com.thesis.distanceguard.R
import com.thesis.distanceguard.presentation.base.BaseFragment
import timber.log.Timber

/**
 * Created by Viet Hua on 04/10/2021.
 */

class ScannerFragment : BaseFragment() {
    override fun getResLayoutId(): Int {
        return R.layout.fragment_scanner
    }

    override fun onMyViewCreated(view: View) {
        Timber.d("onMyViewCreated")

    }
}