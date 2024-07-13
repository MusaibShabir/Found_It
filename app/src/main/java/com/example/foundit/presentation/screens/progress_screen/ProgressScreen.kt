package com.example.foundit.presentation.screens.progress_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.foundit.R
import com.example.foundit.presentation.screens.progress_screen.components.FinishedProcessCardList
import com.example.foundit.presentation.screens.progress_screen.components.InProcessCardList
import com.example.foundit.presentation.screens.progress_screen.components.ProcessCardItem
import com.example.foundit.data.FinishedProcessData
import com.example.foundit.data.InProcessData
import com.example.foundit.presentation.common.TheTopAppBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProcessScreen(modifier: Modifier, navController: NavHostController) {
    val pagerState = rememberPagerState(
        pageCount = { 2 }
    )

    val coroutineScope = rememberCoroutineScope()

    val inProcessItems = InProcessData.map { ProcessCardItem.InProcess(it) }

    val finishedItems = FinishedProcessData.map { ProcessCardItem.Finished(it)}

    Scaffold(
        modifier = modifier,
        topBar = { TheTopAppBar(title = "Progress", navController = navController)}
    ){paddingValues ->
        Column(modifier = modifier.padding(paddingValues)) {
            TabRow(selectedTabIndex = pagerState.currentPage) {
                Tab(
                    selected = pagerState.currentPage == 0,
                    text = { Text(text = stringResource(id = R.string.left_tab)) },
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(0)
                        }
                    })

                Tab(
                    selected = pagerState.currentPage == 1,
                    text = { Text(text = stringResource(id = R.string.right_tab)) },
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(1)
                        }
                    }
                )
            }


            HorizontalPager(
                state = pagerState,
                userScrollEnabled = true
            ) { page ->
                when (page) {
                    0 -> {
                        InProcessCardList(modifier = modifier, cardData = inProcessItems)
                    }
                    1 -> {
                        FinishedProcessCardList(modifier = modifier, cardData =finishedItems)
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = false)
fun PreviewProcessScreen() {
    ProcessScreen(modifier = Modifier, navController = NavHostController(LocalContext.current))
}