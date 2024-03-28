@file:OptIn(ExperimentalMaterial3Api::class)

package dev.susu.personalassistant.task_details.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.rememberNavController
import dev.susu.personalassistant.core.navigator.PerAssNavigator
import dev.susu.personalassistant.core.navigator.PerAssNavigatorEvent
import dev.susu.personalassistant.core.ui.theme.LineBackground
import dev.susu.personalassistant.core.ui.theme.TaskItemColor
import kotlinx.coroutines.flow.Flow

@Composable
fun TaskDetailsScreen() {

    val viewModel: TaskDetailsViewModel = hiltViewModel()
    val stateFlow = viewModel.screenState.collectAsStateWithLifecycle()

    when (val state = stateFlow.value) {
        TaskDetailsScreenState.Error -> {}
        TaskDetailsScreenState.Loading -> {}
        is TaskDetailsScreenState.Success -> {
            TaskDetailsContent(taskDetailsValue = state.value, onAction = viewModel::onAction)
        }
    }
}

@Composable
internal fun TaskDetailsContent(
    onAction: (TaskDetailsAction) -> Unit,
    taskDetailsValue: TaskDetailsValue?
) {

    val scrollableState = rememberScrollState()
    val description = remember { mutableStateOf(taskDetailsValue?.description ?: "") }

    Card(
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        colors = CardDefaults.elevatedCardColors(Color.White),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            Modifier
                .padding(start = 20.dp, end = 20.dp)
                .scrollable(scrollableState, orientation = Orientation.Vertical)
        ) {
            Toolbar(onAction)
            Spacer(modifier = Modifier.height(12.dp))
            SampleLine()
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Описание", fontWeight = FontWeight.Medium, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(10.dp))
            TaskDescription(description)
            Spacer(modifier = Modifier.height(16.dp))

            TaskPeculiaritiesList(
                term = taskDetailsValue?.term,
                reminder = taskDetailsValue?.reminder,
                importance = taskDetailsValue?.importance
            )
        }
    }

}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TaskDescription(
    description: MutableState<String>,
) {
    TextField(
        value = description.value,
        onValueChange = { description.value = it },
        modifier = Modifier
            .padding(8.dp)
            .background(Color.White)
            .fillMaxWidth(),
        minLines = 8,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black
        ),
        textStyle = TextStyle.Default.merge(fontSize = 16.sp),
    )
}

@Composable
private fun Toolbar(onAction: (TaskDetailsAction) -> Unit) {
    Row(modifier = Modifier.padding(top = 24.dp)) {
        Icon(
            Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = null,
            modifier = Modifier.clickable { onAction(TaskDetailsAction.ToBack) })
        Spacer(modifier = Modifier.width(24.dp))
        Text(
            text = "Задача",
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
    }
}

@Composable
fun TaskPeculiaritiesList(
    term: String?,
    reminder: String?,
    importance: String?
) {

    LazyColumn {
        item {
            TaskPeculiaritiesItem(
                title = "срок по задаче",
                icon = Icons.Default.DateRange,
                value = term
            ) {}
        }

        item {
            TaskPeculiaritiesItem(
                title = "Напоминание",
                icon = Icons.Filled.Notifications,
                value = reminder
            ) {}
        }

        item {
            TaskPeculiaritiesItem(
                title = "Важность",
                icon = Icons.Default.Warning,
                value = importance
            ) {}
        }
    }
}

@Composable
fun TaskPeculiaritiesItem(
    title: String,
    icon: ImageVector? = null,
    @DrawableRes resource: Int? = null,
    value: String? = null,
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .clickable(onClick = onClick)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            icon?.let {
                Icon(it, contentDescription = null)
            }

            resource?.let {
                Icon(painterResource(id = it), contentDescription = null)
            }

            Spacer(modifier = Modifier.width(8.dp))
            Text(text = title)
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = value ?: "Нет",
                color = Color.Gray,
                modifier = Modifier
                    .background(
                        color = TaskItemColor,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(vertical = 4.dp, horizontal = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        SampleLine()
    }


}

@Composable
private fun SampleLine() {
    HorizontalDivider(
        thickness = 0.5.dp,
        color = LineBackground,
    )
}

@Preview
@Composable
internal fun TaskDetailScreenPreview() {
    TaskDetailsContent(onAction = {}, taskDetailsValue = null)
}