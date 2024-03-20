package dev.susu.personalassistant.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavOptionsBuilder
import dev.susu.personalassistant.core.navigator.PerAssNavigator
import dev.susu.personalassistant.core.navigator.PerAssNavigatorEvent
import dev.susu.personalassistant.core.ui.theme.BackgroundGradient
import dev.susu.personalassistant.core.ui.theme.GreyTextColor
import dev.susu.personalassistant.core.ui.theme.YellowColor
import dev.susu.personalassistant.feature.home.ui.list.TaskItem
import dev.susu.personalassistant.feature.home.ui.value.HomeScreenValue
import dev.susu.personalassistant.home.R
import kotlinx.coroutines.flow.Flow
import dev.susu.personalassistant.core.ui.R as CoreUiR


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
            item {
                NoTaskContent(modifier = Modifier.fillMaxSize()) {
                    viewModel.onAction(HomeScreenAction.OnTaskDetails())
                }
            }
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

@Composable
internal fun NoTaskContent(modifier: Modifier, onClick: () -> Unit) {

    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .then(modifier)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(id = CoreUiR.drawable.baseline_pan_tool_alt_24),
            contentDescription = "",
            tint = YellowColor,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 24.dp)
                .size(96.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))
        Card(
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = YellowColor,
            ), shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(start = 48.dp, end = 48.dp)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp),
                text = "Щелкните здесь, чтобы создать свою первую задачу"
            )
        }
    }
}


@Composable
@Preview
fun NoTaskContentPreview() {
    NoTaskContent(Modifier) {}
}

@Composable
@Preview
fun HomeSuccessPreview() {
    Surface {
        HomeScreen(
            HomeScreenValue(0, "Robert", "21.03.2003", "0", "0", emptyList()),
            HomeViewModel(object : PerAssNavigator {
                override fun navigateUp(): Boolean {
                    TODO("Not yet implemented")
                }

                override fun navigate(
                    route: String,
                    builder: NavOptionsBuilder.() -> Unit
                ): Boolean {
                    TODO("Not yet implemented")
                }

                override fun popBackStack(): Boolean {
                    TODO("Not yet implemented")
                }

                override val destinations: Flow<PerAssNavigatorEvent>
                    get() = TODO("Not yet implemented")
            })
        )
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