package com.example.testing.model

enum class TimeList(val values: List<Pair<String, String>>) {
    MAIN(
        listOf(
            "8:15" to "8:55",
            "9:05" to "9:45",
            "9:55" to "10:35",
            "10:50" to "11:30",
            "11:50" to "12:30",
            "13:00" to "13:40",
            "13:45" to "14:25",
            "14:30" to "15:10",
        )
    ),
    SATURDAY(
        listOf(
            "9:00" to "9:40",
            "9:45" to "10:25",
            "10:30" to "11:10",
            "11:20" to "12:00",
            "12:10" to "12:45",
            "12:55" to "13:30",
            "13:35" to "14:10"
        )
    )
}