package com.appdev.uidesign.presentation.Screen

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.appdev.uidesign.R
import com.appdev.uidesign.data.dataItem
import com.appdev.uidesign.ui.theme.UIDesignTheme


@Composable
fun Screen() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottieanim))
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
                    description = "Indulge in the crisp and invigorating flavor of pure apple juice, crafted from ripe, hand-picked apples for a naturally sweet and refreshing taste. Loved by both kids and adults, this wholesome beverage is an excellent source of hydration and essential nutrients, making it the perfect choice for any occasion.",
                    isChecked = false
                )
            )
        )
    }
    BoxWithConstraints {
        val maxWidth = this@BoxWithConstraints.maxWidth
        val maxHeight = this@BoxWithConstraints.maxHeight

        Column(
            modifier = Modifier
                .background(Color(0xffE3EDF7)).fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth() // Adjust width based on screen size
                    .height(minOf(maxHeight * 0.8f, 300.dp))// Adjust max height
                    .background(Color(0xff0D55C0)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LottieAnimation(
                    composition = composition,
                    modifier = Modifier.size(minOf(maxWidth * 0.3f, 200.dp))
                )
                Text(
                    text = "Please place your cups on the bottom tray to refill",
                    fontSize = if (maxWidth > 800.dp) 30.sp else 20.sp,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(if (maxWidth > 800.dp) 20.dp else 16.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.W600,
                    lineHeight = if (maxWidth > 800.dp) (30 * 1.2).sp else (24 * 1.2).sp
                )
            }
            ListPreview(listOfData = dataList)

        }
    }


}

@Composable
fun ListPreview(listOfData: List<dataItem>) {
    BoxWithConstraints {
        val maxWidth = this@BoxWithConstraints.maxWidth
        val maxHeight = this@BoxWithConstraints.maxHeight

        LazyColumn(modifier = Modifier.padding(horizontal = 15.dp, vertical = 14.dp)) {
            items(listOfData) { item ->
                SingleItem(dataItem = item, maxWidth = maxWidth, maxHeight = maxHeight)
            }
            item{
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    RoundedButtonWithGradientBackground(
                        onClick = { /*TODO*/ }, buttonText = "Refill", modifier = Modifier
                            .padding(top = 20.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun SingleItem(dataItem: dataItem, maxHeight: Dp, maxWidth: Dp) {
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
                .padding(horizontal = if (maxWidth > 300.dp) 2.dp else 8.dp)
        )

        Image(
            painter = painterResource(id = dataItem.imageId),
            contentDescription = "",
            modifier = Modifier
                .size(minOf(maxWidth * 0.3f, 130.dp))
        )
        Column(
            modifier = Modifier
                .padding(vertical = 5.dp, horizontal = 14.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = dataItem.juiceName,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontSize = if (maxWidth > 600.dp) 18.sp else 16.sp
            )
            Text(
                text = dataItem.description,
                color = Color.Gray,
                fontSize = if (maxWidth > 600.dp) 16.sp else 13.sp,
                lineHeight = if (maxWidth > 600.dp) (20 * 1.2).sp else (14 * 1.2).sp
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Select Options, Add-ons and Add Special Instructions",
                    color = Color(0xff0D55C0),
                    fontSize = if (maxWidth > 600.dp) 16.sp else 13.sp,
                    lineHeight = if (maxWidth > 600.dp) (20 * 1.2).sp else (14 * 1.2).sp
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
    BoxWithConstraints {
        val maxWidth = this@BoxWithConstraints.maxWidth
        val maxHeight = this@BoxWithConstraints.maxHeight
        Card(
            modifier = modifier
                .padding(16.dp)
                .height(minOf(51.dp, maxHeight * 0.14f))
                .fillMaxWidth(if (maxWidth > 800.dp) 0.2f else 0.38f),
            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent,
                contentColor = Color.White
            )
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

}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun GreetingPreview() {
//    UIDesignTheme {
//        Screen()
//    }
//}

