package ru.elvitalya.droiderhandbook.core.utils

sealed interface ViewState {
    object Loading : ViewState
    object Content : ViewState
    object Error : ViewState
    object Empty: ViewState
}