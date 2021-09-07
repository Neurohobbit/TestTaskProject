package com.testproject.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation

open class BaseFragment(layout: Int) : Fragment(layout) {

    protected open val statusBarColor: Int = android.R.color.white

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.run {
            window?.statusBarColor = getColor(statusBarColor)
        }
    }

    internal fun navigate(directions: NavDirections) =
        view?.let {
            Navigation.findNavController(it).navigate(directions)
        }
}
