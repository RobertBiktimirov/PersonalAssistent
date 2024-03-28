@file:OptIn(ExperimentalMaterial3Api::class)

package dev.susu.personalassistant.task_details.ui

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.rememberNavController
import dev.susu.personalassistant.core.details.TaskDetailsDestination
import dev.susu.personalassistant.core.navigator.PerAssNavigator
import dev.susu.personalassistant.core.navigator.PerAssNavigatorEvent
import dev.susu.personalassistant.core.ui.theme.LineBackground
import kotlinx.coroutines.flow.Flow

@Composable
fun TaskDetailsScreen() {

    val navController = rememberNavController()
    val viewModel: TaskDetailsViewModel = hiltViewModel()
    val stateFlow = viewModel.screenState.collectAsStateWithLifecycle()

    when (val state = stateFlow.value) {
        TaskDetailsScreenState.Error -> {}
        TaskDetailsScreenState.Loading -> {}
        is TaskDetailsScreenState.Success -> {
            TaskDetailsScreen(taskDetailsValue = state.value, viewModel = viewModel)
        }
    }
}

@Composable
internal fun TaskDetailsScreen(
    taskDetailsValue: TaskDetailsValue?,
    viewModel: TaskDetailsViewModel
) {

    Column {
        ToolbarView(taskDetailsValue == null) {
            viewModel.onAction(TaskDetailsAction.ToBack)
        }
        Spacer(modifier = Modifier.height(60.dp))
        TaskDetailsContent(taskDetailsValue = taskDetailsValue)

    }
}

@Composable
internal fun ToolbarView(isNewTask: Boolean, onBack: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 18.dp, bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = "onBack",
            modifier = Modifier
                .clickable(onClick = onBack)
                .padding(start = 20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = if (isNewTask) "Создать новую задачу" else "Отредактировать задачу",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TaskDetailsContent(taskDetailsValue: TaskDetailsValue?) {

    Card(
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        colors = CardDefaults.elevatedCardColors(Color.White),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(Modifier.padding(start = 20.dp, end = 20.dp)) {
            Text(
                text = "Контент задачи",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(top = 24.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(
                thickness = 1.dp,
                color = LineBackground,
            )
            Spacer(modifier = Modifier.height(20.dp))


            Text(text = "Описание задачи", fontWeight = FontWeight.Medium, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(10.dp))

            val description = remember { mutableStateOf(taskDetailsValue?.description ?: "") }

            TextField(
                value = description.value,
                onValueChange = { description.value = it },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .border(1.dp, color = LineBackground, RoundedCornerShape(20.dp))
                    .padding(8.dp)
                    .background(Color.White)
                    .fillMaxWidth(),
                maxLines = 5,
                minLines = 5,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black
                ),
                placeholder = {
                    Text(text = "Введите описание задачи", color = Color.Gray, fontSize = 12.sp)
                },
                textStyle = TextStyle.Default.merge(fontSize = 12.sp),
            )
        }


    }

}


@Composable
@Preview
internal fun ToolbarViewPreview() {
    Surface() {
        ToolbarView(true) {}
    }
}

@Preview
@Composable
internal fun TaskDetailScreenPreview() {
    TaskDetailsScreen(
        taskDetailsValue = null, viewModel = TaskDetailsViewModel(
            object : PerAssNavigator {
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

            },
            savedStateHandle = SavedStateHandle()
        )
    )

}