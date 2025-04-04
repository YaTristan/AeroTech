package com.example.aerotech.pages.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aerotech.R
import com.example.aerotech.ui.theme.Background
import com.example.aerotech.ui.theme.BackgroundCard
import com.example.aerotech.ui.theme.TabFont

@Composable
fun SettingsItemPage(){
    Column {
        BoxForSettings(title = "Share app", icon = R.drawable.share)
        BoxForSettings(title = "Usage Policy", icon = R.drawable.usage)
        BoxForSettings(title = "Rate app", icon = R.drawable.start)
    }
}

@Composable
fun BoxForSettings(title:String, icon:Int){
    Box(
        modifier = Modifier
            .padding(top = 24.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(BackgroundCard)
            .border(width = 1.dp, color = Color.White, RoundedCornerShape(15.dp)),
        contentAlignment = Alignment.Center
    ) {
        Row() {
            Box(
                modifier = Modifier.weight(0.4f).padding(start = 20.dp)
            ) {
                Image(painter = painterResource(id = icon), contentDescription = null)
            }
            Box(
                modifier = Modifier.weight(2f).padding(end = 20.dp,top = 8.dp,start = 8.dp),
            ) {
                Text(
                    text = title,
                    fontFamily = TabFont,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Box(
                modifier = Modifier.weight(0.4f).padding(end = 10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ellipsises),
                    contentDescription = null
                )
            }
        }

    }
}
