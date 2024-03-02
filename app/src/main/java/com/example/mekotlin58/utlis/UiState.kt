package com.example.mekotlin58.utlis

data class UiState <T> (
    val isLoading: Boolean = true,
    val error:String? = null,
    val success: T? = null
)