package ru.elvitalya.droiderhandbook.features.auth.ui.select_method

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.elvitalya.droiderhandbook.core.theme.accent
import ru.elvitalya.droiderhandbook.core.theme.inActive
import ru.elvitalya.droiderhandbook.core.theme.white
import ru.elvitalya.droiderhandbook.features.R

@Composable
fun SelectMethodUi(
    component: SelectMethodComponent
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(white),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = stringResource(R.string.auth_title),
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth(0.8f)
                .clip(RoundedCornerShape(16.dp))
                .background(accent)
                .padding(vertical = 8.dp)
                .align(Alignment.TopCenter),
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold
        )


        Column(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .background(inActive, RoundedCornerShape(16.dp))
                .padding(vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(R.string.auth_sign_in),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable(onClick = component::onLoginClick)
                    .background(accent)
                    .padding(vertical = 8.dp),
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(24.dp))


            Text(
                text = stringResource(R.string.auth_registration),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable(onClick = component::onRegistrationClick)
                    .background(accent)
                    .padding(vertical = 8.dp),
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}