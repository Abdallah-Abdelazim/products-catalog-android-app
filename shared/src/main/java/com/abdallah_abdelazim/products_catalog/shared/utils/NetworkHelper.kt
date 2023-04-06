package com.abdallah_abdelazim.products_catalog.shared.utils

import com.blankj.utilcode.util.NetworkUtils

open class NetworkHelper {

    open fun isConnected() = NetworkUtils.isConnected()

}