package com.example.aerotech.pages.aircraft.aircraftItemPage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.aerotech.ui.theme.Background
import com.example.aerotech.ui.theme.TabFont
import com.example.aerotech.ui.theme.TopBarBack

@Composable
fun DeleteAircraftItemDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .width(270.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(Background) // Установка фона диалога
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Deletion", color = Color.White, fontSize = 17.sp, fontFamily = TabFont, modifier = Modifier.padding(top =16.dp))
                Text(text = "Do you really want to delete it?", color = Color.White,fontSize = 13.sp, fontFamily = TabFont, modifier = Modifier.padding(top = 8.dp))
                Row(
                    modifier = Modifier.padding(top = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(modifier = Modifier.padding(end = 48.dp)) {
                        TextButton(onClick = onDismiss) {
                            Text(text = "No", color = Color.White)
                        }
                    }
                    Box() {
                        TextButton(onClick = onConfirm) {
                            Text(text = "Yes", color = TopBarBack)
                        }
                    }
                }
            }
        }
    }
}