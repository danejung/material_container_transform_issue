package com.example.materialcontainertransformissue

import android.graphics.Color

data class RowData(val name: String, val color: Int)

/**
 * God class for example implementation simplicity
 */
object ExampleGodClass {
    val rows = listOf(
        RowData(
            "Hammer",
            Color.BLACK
        ),
        RowData("Wrench", Color.RED),
        RowData("Saw", Color.BLUE),
        RowData(
            "Screwdriver",
            Color.GREEN
        )
    )
    const val TRANSITION_TIME_MS = 500L

    fun getTransitionName(row: Int): String {
        return "transition-$row"
    }
}