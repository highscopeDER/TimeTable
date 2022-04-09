package com.example.testing.daysFragment

enum class DaysEnum (val previous: String, val current: String, val next: String) {
    MONDAY("Суббота", "Понедельник", "Вторник"),
    TUESDAY("Понедельник", "Вторник", "Среда"),
    WEDNESDAY("Вторник", "Среда", "Четверг"),
    THURSDAY("Среда", "Четверг", "Пятница"),
    FRIDAY("Четверг", "Пятница", "Суббота"),
    SATURDAY("Пятница", "Суббота", "Понедельник");

    fun moveBack(day: DaysEnum): DaysEnum{
        return when (day) {
            MONDAY -> SATURDAY
            TUESDAY -> MONDAY
            WEDNESDAY -> TUESDAY
            THURSDAY -> WEDNESDAY
            FRIDAY -> THURSDAY
            SATURDAY -> FRIDAY
        }
    }

    fun moveForward(day: DaysEnum): DaysEnum{
        return when (day) {
            MONDAY -> TUESDAY
            TUESDAY -> WEDNESDAY
            WEDNESDAY -> THURSDAY
            THURSDAY -> FRIDAY
            FRIDAY -> SATURDAY
            SATURDAY -> MONDAY
        }
    }

}