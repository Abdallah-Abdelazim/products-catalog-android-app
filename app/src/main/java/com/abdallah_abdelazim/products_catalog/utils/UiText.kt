package com.abdallah_abdelazim.products_catalog.utils

import android.content.Context
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

sealed class UiText {

    data class DynamicString(val value: String) : UiText()

    data class StringResource(
        @StringRes val resId: Int,
        val args: List<Any> = emptyList()
    ) : UiText()

    data class QuantityStringResource(
        @PluralsRes val resId: Int,
        val quantity: Int,
        val args: List<Any> = emptyList()
    ) : UiText()

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> context.getString(resId, *args.toTypedArray())
            is QuantityStringResource -> context.resources.getQuantityString(
                resId,
                quantity,
                *args.toTypedArray()
            )
        }
    }

}