package dev.susu.personalassistant.feature.home.ui.list

import android.content.ClipData
import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.Gson
import dev.susu.personalassistant.feature.home.domain.models.TaskItemModel
import dev.susu.personalassistant.feature.home.domain.models.TaskProgress
import dev.susu.personalassistant.feature.home.domain.models.TaskType
import dev.susu.personalassistant.core.ui.theme.GreyTextColor
import androidx.compose.ui.graphics.Color as ComposeColor

private const val TASK_ITEM_ACTION = "task action"
private const val TASK_ITEM_DATA = "task data"

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun TaskItem(task: TaskItemModel, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .dragAndDropSource(
                drawDragDecoration = {
                    // UI перетаскиваемого элемента
                }
            ) {
                // логика обработки Drag and Drop
                startTransfer(
                    DragAndDropTransferData(
                        clipData = ClipData.newIntent(
                            "task item",
                            Intent(TASK_ITEM_ACTION).putExtra(
                                TASK_ITEM_DATA,
                                Gson().toJson(task)
                            )
                        )
                    )
                )
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(unbounded = false)
                .padding(end = 20.dp, start = 20.dp, bottom = 8.dp)
                .background(color = ComposeColor.White, shape = RoundedCornerShape(12.dp))
                .border(1.dp, Color(0xFFDCE1EF), RoundedCornerShape(12.dp))
                .clickable(true, onClick = onClick)
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .padding(start = 12.dp),
                text = task.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            Text(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .padding(start = 12.dp),
                text = task.description,
                color = GreyTextColor,
                maxLines = 2,
                fontSize = 12.sp,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
                    .padding(start = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    modifier = Modifier.size(12.dp)
                )
                Text(
                    text = task.deadline, modifier = Modifier
                        .padding(start = 8.dp)
                        .weight(1f),
                    fontSize = 10.sp,
                    color = GreyTextColor
                )

                Text(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .background(
                            color = task.type.color.copy(alpha = 0.09f),
                            shape = RoundedCornerShape(40.dp)
                        )
                        .padding(horizontal = 10.dp, vertical = 6.dp),
                    text = task.type.progress.value,
                    color = task.type.color,
                    fontSize = 12.sp,
                )
            }
        }
    }
}

@Preview
@Composable
fun TaskItemPreview() {

    Surface {
        TaskItem(
            task = TaskItemModel(
                title = "Test title with sample text",
                description = "Test description with sample text and very long",
                type = TaskType(Color.Green, TaskProgress.NEW_TASK),
                deadline = "21.02.2022",
                0
            )
        ) {}
    }

}