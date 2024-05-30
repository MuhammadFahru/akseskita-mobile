package com.upi.akseskita.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.upi.akseskita.R
import com.upi.akseskita.ui.component.ButtonFill
import com.upi.akseskita.ui.component.ButtonOutline
import com.upi.akseskita.ui.component.PagerIndicator
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Onboarding() {
    val onboardingContentList = listOf(
        OnboardingModel(
            title = "Selamat Datang!",
            content = "Mari bersama membuka dunia inklusi dengan teman digital cerdas berbasis AI untuk penyandang disabilitas.",
            icon = R.drawable.ic_welcome,
            iconDesc = "Icon Selamat Datang",
            index = 0,
            backgroundColor = Color.White
        ),
        OnboardingModel(
            title = "Kemudahan Akses di Genggaman Anda",
            content = "Dapatkan informasi tentang fasilitas publik dan layanan yang ramah disabilitas. Temukan solusi sehari-hari yang dirancang khusus untuk kebutuhan Anda.",
            icon = R.drawable.ic_disable,
            iconDesc = "Icon Disabilitas",
            index = 1,
            backgroundColor = colorResource(id = R.color.primary)
        ),
        OnboardingModel(
            title = "Komunitas dan Dukungan",
            content = "Temukan dukungan, dan bagikan pengalaman Anda. Bersama, kita bisa saling membantu dan memberdayakan.",
            icon = R.drawable.ic_community,
            iconDesc = "Icon Komunitas",
            index = 2,
            backgroundColor = colorResource(id = R.color.tertiary)
        ),
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        onboardingContentList.size
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(5000L)
            val nextPage = if (pagerState.currentPage < pagerState.pageCount - 1) {
                pagerState.currentPage + 1
            } else {
                0
            }
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(state = pagerState) { page ->
            OnboardingContent(data = onboardingContentList[page])
        }

        PagerIndicator(
            contentList = onboardingContentList,
            pagerState = pagerState,
            modifier = Modifier
                .padding(bottom = 14.dp)
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun OnboardingContent(
    data: OnboardingModel
) {
    Box(
        modifier = Modifier
            .background(data.backgroundColor)
            .padding(22.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = data.title,
                fontSize = 36.sp,
                fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                fontWeight = FontWeight.ExtraBold,
                lineHeight = 40.sp,
                modifier = Modifier.height(142.dp)
            )
            Spacer(modifier = Modifier.height(27.dp))
            Image(
                painter = painterResource(data.icon),
                contentDescription = data.iconDesc,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(206.dp),
            )
            Spacer(modifier = Modifier.height(27.dp))
            Image(
                painter = painterResource(R.drawable.ic_quote),
                contentDescription = data.iconDesc,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .height(21.dp),
            )
            Text(
                text = data.content,
                fontSize = 24.sp,
                lineHeight = 26.sp,
                fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
            )

            if (data.index == 2) {
                Spacer(modifier = Modifier.height(27.dp))
                ButtonFill(text = "Kenali Diriku") {

                }
                Spacer(modifier = Modifier.height(8.dp))
                ButtonOutline(text = "Telusuri Sekarang") {

                }
            }
        }
    }
}

data class OnboardingModel(
    val title: String,
    val content: String,
    val icon: Int,
    val iconDesc: String,
    val index: Int,
    val backgroundColor: Color
)

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    Onboarding()
}