package dev.susu.personalassistant.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.susu.personalassistant.feature.home.domain.FakeData
import dev.susu.personalassistant.feature.home.ui.list.TaskItem
import dev.susu.personalassistant.feature.home.ui.value.HomeScreenValue
import dev.susu.personalassistant.home.R
import dev.susu.personalassistant.theme.BackgroundGradient
import dev.susu.personalassistant.theme.GreyTextColor

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    val state = viewModel.screenState.collectAsStateWithLifecycle()

    when (val value = state.value) {
        HomeScreenState.Error -> TODO()
        HomeScreenState.Loading -> TODO()
        is HomeScreenState.Success -> HomeScreen(value.data, viewModel)
    }
}

@Composable
internal fun HomeScreen(
    homeScreenValue: HomeScreenValue,
    viewModel: HomeViewModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGradient)
    ) {

        item {
            HomeToolbar(homeScreenValue.userName, homeScreenValue.date)
        }
        item {
            SummaryContent(homeScreenValue.assignedTasks, homeScreenValue.completedTasks)
        }

        item {
            TaskInfo()
            Spacer(modifier = Modifier.height(12.dp))
        }
        if (homeScreenValue.tasks.isNotEmpty()) {
            items(homeScreenValue.tasks) {
                TaskItem(task = it) {
                    viewModel.onAction(HomeScreenAction.OnTaskDetails(it.id))
                }
            }
        } else {
            TODO("implementation layout 'not tasks'")
        }
    }

}

@Composable
internal fun HomeToolbar(name: String, date: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = String.format(
                    stringResource(id = R.string.feature_home_user_name_title),
                    name
                ), color = GreyTextColor
            )
            Text(
                modifier = Modifier.padding(vertical = 4.dp),
                text = date,
                fontSize = 16.sp,
                color = Color(0xFF0D101C),
                fontWeight = FontWeight.Medium,
            )
        }

        Icon(imageVector = Icons.Default.Notifications, contentDescription = null)
    }
}

@Composable
internal fun SummaryContent(assignedTasks: String, completedTasks: String) {
    Column(modifier = Modifier.padding(all = 20.dp)) {
        Text(text = "Summary", fontWeight = FontWeight.Medium, fontSize = 20.sp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CardSummaryItem(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(end = 8.dp),
                title = "Назначено",
                count = assignedTasks
            )
            CardSummaryItem(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                title = "Выполнено",
                count = completedTasks
            )
        }
    }
}


@Composable
internal fun CardSummaryItem(
    modifier: Modifier,
    title: String,
    count: String
) {
    Column(
        modifier = Modifier
            .padding(top = 12.dp)
            .then(modifier)
            .border(1.dp, Color(0xFFDCE1EF), RoundedCornerShape(12.dp))
            .background(Color.White, shape = RoundedCornerShape(12.dp))
    ) {
        Text(
            text = title,
            color = GreyTextColor,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(top = 12.dp, start = 12.dp, end = 12.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = count,
            Modifier.padding(top = 8.dp, start = 12.dp, end = 12.dp, bottom = 12.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
internal fun TaskInfo() {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = "Tasks",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )

    }
}