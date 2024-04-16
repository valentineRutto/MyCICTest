package com.valentinerutto.mycictest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valentinerutto.mycictest.data.LoginRepository
import com.valentinerutto.mycictest.data.Resource
import com.valentinerutto.mycictest.data.remote.UserPostData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewmodel(private val repository: LoginRepository) : ViewModel() {
    private val _state = MutableStateFlow(UiState(loading = true))
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun postUser(userPostData: UserPostData) = viewModelScope.launch(Dispatchers.IO) {

        repository.postUser(userPostData).collect {

            when (it) {
                is Resource.Loading -> {

                    setState {
                        copy(loading = true)
                    }
                }

                is Resource.Success -> {
                    setState {
                        it.data.firstName?.let { it1 -> copy(loading = false, user = it1) }!!
                    }
                }

                is Resource.Error -> {
                    setState {
                        copy(loading = false, error = it.errorMessage)
                    }
                }

                else -> {

                }
            }
        }
    }

    private fun setState(stateReducer: UiState.() -> UiState) {
        viewModelScope.launch {
            _state.emit(stateReducer(state.value))
        }
    }

    data class UiState(
        val loading: Boolean = false, val user: String = "", val error: String = ""
    )
}