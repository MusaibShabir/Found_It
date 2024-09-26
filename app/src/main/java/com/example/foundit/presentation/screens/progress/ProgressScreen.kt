package com.example.foundit.presentation.screens.progress

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.foundit.R
import com.example.foundit.presentation.common.TheTopAppBar
import com.example.foundit.presentation.screens.progress.components.ProcessCardList
import com.example.foundit.ui.theme.MainGreen
import kotlinx.coroutines.launch

@Composable
fun ProcessScreen(modifier: Modifier, navController: NavHostController) {
    val pagerState = rememberPagerState(
        initialPage = 1,
        pageCount = { 3 }
    )

    val coroutineScope = rememberCoroutineScope()

    val viewModel: ProgressViewModel = hiltViewModel()
    val haltedItems by viewModel.haltedItems.collectAsState()
    val inProcessItems by viewModel.inProcessItems.collectAsState()
    val finishedItems by viewModel.finishedItems.collectAsState()

//    val inProcessItems = InProcessData.map { ProcessCardItem.InProcess(it) }
//
//    val finishedItems = FinishedProcessData.map { ProcessCardItem.Finished(it)}

    Scaffold(
        modifier = modifier,
        topBar = { TheTopAppBar(title = "Progress", navController = navController, isNavigationIconVisible = false)}
    ){paddingValues ->
        Column(modifier = modifier.padding(paddingValues)) {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground,
                indicator = { tabPositions ->
                    SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                        color = MainGreen
                    )
                }
            ) {

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
                        ProcessCardList(
                            modifier = modifier,
                            cardData = haltedItems,
                            navController = navController
                        )
                    }
                    1 -> {
                        ProcessCardList(
                            modifier = modifier,
                            cardData = inProcessItems,
                            navController = navController
                        )
                    }
                    2 -> {
                        ProcessCardList(
                            modifier = modifier,
                            cardData =finishedItems,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
    BackHandler(
        enabled = true,
    ) {

    }
}

@Composable
@Preview(showBackground = true, showSystemUi = false)
fun PreviewProcessScreen() {
    ProcessScreen(modifier = Modifier, navController = NavHostController(LocalContext.current))
}