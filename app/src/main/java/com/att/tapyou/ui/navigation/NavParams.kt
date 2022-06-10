package com.att.tapyou.ui.navigation

import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigator

data class NavParams(
    val navDirections: NavDirections? = null,
    val extras: FragmentNavigator.Extras? = null,
    val navOptions: NavOptions? = null
)