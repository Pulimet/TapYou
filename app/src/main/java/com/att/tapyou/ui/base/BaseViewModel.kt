package com.att.tapyou.ui.base

import androidx.lifecycle.ViewModel
import com.att.tapyou.utils.logs.logI

abstract class BaseViewModel : ViewModel() {
    init {
        logI(this.javaClass.simpleName)
    }

    override fun onCleared() {
        super.onCleared()
        logI(this.javaClass.simpleName)
    }
}