package com.example.aerotech.pages.bars.bottomBarSections

import android.graphics.drawable.Icon
import android.media.Image
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.aerotech.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.aerotech.ui.theme.TabFont
import com.example.aerotech.ui.theme.unSelected

@Composable
fun ElementsSection(id:Int,name:String, icon: ImageVector,selectedId: Int, onSelect: (Int) -> Unit, route:String, navController: NavController){

    val isSelected = selectedId == id

    val animatedColor by animateColorAsState(if (isSelected) Color.White else unSelected)

    Column(
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        onSelect(id)
                        navController.navigate(route)
                    }
                )
            }
    ){
        Icon(
            imageVector = icon,
            contentDescription = name,
            tint = animatedColor,
            modifier = Modifier.size(24.dp).align(Alignment.CenterHorizontally)
        )
        Text(text = name,
            modifier = Modifier.fillMaxWidth(),
            fontFamily = TabFont,
            fontSize = 10.sp,
            color = animatedColor,
            textAlign = TextAlign.Center
        )
    }
}