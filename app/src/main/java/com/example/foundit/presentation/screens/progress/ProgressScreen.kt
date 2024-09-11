package com.example.foundit.presentation.screens.progress

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
import com.example.foundit.presentation.common.TheTopAppBar
import com.example.foundit.presentation.data.FinishedProcessData
import com.example.foundit.presentation.data.InProcessData
import com.example.foundit.presentation.data.navigation.NavRoutes
import com.example.foundit.presentation.screens.progress.components.FinishedProcessCardList
import com.example.foundit.presentation.screens.progress.components.HaltedProcessCardList
import com.example.foundit.presentation.screens.progress.components.InProcessCardList
import com.example.foundit.presentation.screens.progress.components.ProcessCardItem
import kotlinx.coroutines.launch

@Composable
fun ProcessScreen(modifier: Modifier, navController: NavHostController) {
    val pagerState = rememberPagerState(
        initialPage = 1,
        pageCount = { 3 }
    )

    val coroutineScope = rememberCoroutineScope()

    val inProcessItems = InProcessData.map { ProcessCardItem.InProcess(it) }

    val finishedItems = FinishedProcessData.map { ProcessCardItem.Finished(it)}

    Scaffold(
        modifier = modifier,
        topBar = { TheTopAppBar(title = "Progress", navController = navController, backRoute = NavRoutes.HOME)}
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
                    }
                )

                Tab(
                    selected = pagerState.currentPage == 1,
                    text = { Text(text = stringResource(id = R.string.center_tab)) },
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(1)
                        }
                    }
                )

                Tab(
                    selected = pagerState.currentPage == 2,
                    text = { Text(text = stringResource(id = R.string.right_tab)) },
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(2)
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
                        HaltedProcessCardList(modifier = modifier)
                    }
                    1 -> {
                        InProcessCardList(modifier = modifier, cardData = inProcessItems)
                    }
                    2 -> {
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