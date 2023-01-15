package com.example.composenoteapp.domain.util

sealed class OrderType{
    object Ascending: OrderType()
    object Descending: OrderType()

}
