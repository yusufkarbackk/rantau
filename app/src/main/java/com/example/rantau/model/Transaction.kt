package com.example.rantau.model

data class Transaction(
    val amount: Int,
    val category: String,
    val isIncome: Boolean,
    val userId: String
)
