package com.valentinerutto.mycictest.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen( uiState: LoginViewmodel.UiState,
) {
    val context = LocalContext.current

    if (uiState.loading) {
        LoadingView()
    }

    if (uiState.error.isNullOrBlank().not()) {
        ErrorScreen(uiState, Modifier.fillMaxSize())
    }

    if (!uiState.user.isNullOrEmpty()) {
        Toast.makeText(context,"Login Success",Toast.LENGTH_LONG).show()

    }

}

@Composable
fun LoadingView() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = lightColorScheme().tertiary,
            trackColor = darkColorScheme().tertiary,
        )
    }
}

@Composable
fun ErrorScreen(uiState: LoginViewmodel.UiState, modifier: Modifier) {

    val vm = koinViewModel<LoginViewmodel>()

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

        Text(
            text = uiState.error,
            textAlign = TextAlign.Center,
            modifier = modifier,
            style = MaterialTheme.typography.bodyMedium
        )

        }

}
