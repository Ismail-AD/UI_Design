package com.appdev.uidesign.presentation.Screen

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.appdev.uidesign.R
import com.appdev.uidesign.data.dataItem


@Composable
fun Screen() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottieanim))
    val progress by animateLottieCompositionAsState(composition)
    val dataList by remember {
        mutableStateOf(
            listOf(
                dataItem(
                    imageId = R.drawable.p1,
                    juiceName = "Orange Juice",
                    description = "Enjoy the tangy and refreshing taste of freshly squeezed orange juice, bursting with natural sweetness and packed with essential Vitamin C to boost your immunity and keep you energized throughout the day.",
                    isChecked = true
                ),
                dataItem(
                    imageId = R.drawable.p2,
                    juiceName = "Apple Juice",
                    description = " Indulge in the crisp and invigorating flavor of pure apple juice, crafted from ripe, hand-picked apples for a naturally sweet and refreshing taste. Loved by both kids and adults, this wholesome beverage is an excellent source of hydration and essential nutrients, making it the perfect choice for any occasion.",
                    isChecked = false
                )
            )
        )
    }
    Column(
        modifier = Modifier
            .background(Color(0xffE3EDF7))
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .background(Color(0xff0D55C0)), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier.size(200.dp)
            )
            Text(
                text ="Please place your cups on the bottom tray to refill", fontSize = 30.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 20.dp), textAlign = TextAlign.Center, fontWeight = FontWeight.W600, lineHeight = 35.sp
            )
        }
        ListPreview(listOfData = dataList)
        RoundedButtonWithGradientBackground(
            onClick = { /*TODO*/ }, buttonText = "Refill", modifier = Modifier
                .padding(top = 20.dp)
                .align(Alignment.CenterHorizontally)
        )
    }


}

@Composable
fun ListPreview(listOfData: List<dataItem>) {
    LazyColumn(modifier = Modifier.padding(horizontal = 15.dp, vertical = 14.dp)) {
        items(listOfData) { item ->
            SingleItem(dataItem = item)
        }
    }
}


@Composable
fun SingleItem(dataItem: dataItem) {
    Spacer(modifier = Modifier.height(10.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
    ) {
        Checkbox(
            checked = dataItem.isChecked,
            onCheckedChange = {

            },
            modifier = Modifier
                .padding(horizontal = 8.dp)
        )
        Image(
            painter = painterResource(id = dataItem.imageId),
            contentDescription = "",
            modifier = Modifier
                .size(130.dp).clip(
                    RoundedCornerShape(24.dp)
                )
        )
        Column(
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 14.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = dataItem.juiceName,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
            Text(
                text = dataItem.description,
                color = Color.Gray
            )
            Row {
                Text(
                    text = "Select Options, Add-ons and Add Special Instructions",
                    color = Color(0xff0D55C0)
                )
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "",
                    tint = Color(0xff0D55C0)
                )
            }

        }
    }
}

@Composable
fun RoundedButtonWithGradientBackground(
    onClick: () -> Unit,
    buttonText: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .height(64.dp)
            .fillMaxWidth(0.2f),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent, contentColor = Color.White)
    ) {
        Box(
            modifier = Modifier
                .clickable(onClick = onClick)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF1B8DE9), Color(0xFF0D55C0)),
                        startY = 0f,
                        endY = 64f
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(4.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = buttonText,
                color = Color.White, fontSize = 16.sp
            )
        }
    }

}

