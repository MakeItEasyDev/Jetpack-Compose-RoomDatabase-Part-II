package com.jetpack.roomdatabase

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jetpack.roomdatabase.entity.SampleEntity
import com.jetpack.roomdatabase.ui.theme.Purple500
import com.jetpack.roomdatabase.ui.theme.RoomDatabaseTheme
import com.jetpack.roomdatabase.viewmodel.SampleViewModel
import com.jetpack.roomdatabase.viewmodel.SampleViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomDatabaseTheme {
                CallDatabase()
            }
        }
    }
}

val date = SimpleDateFormat("dd-MM-yyyy")
val strDate: String = date.format(Date())

val insertSampleData = listOf(
    SampleEntity(1, "Sample 1", "Make It Easy Sample 1", "Image Url 1", strDate),
    SampleEntity(2, "Sample 2", "Make It Easy Sample 2", "Image Url 2", strDate),
    SampleEntity(3, "Sample 3", "Make It Easy Sample 3", "Image Url 3", strDate),
    SampleEntity(4, "Sample 4", "Make It Easy Sample 4", "Image Url 4", strDate),
    SampleEntity(5, "Sample 5", "Make It Easy Sample 5", "Image Url 5", strDate),
    SampleEntity(6, "Sample 6", "Make It Easy Sample 6", "Image Url 6", strDate),
    SampleEntity(7, "Sample 7", "Make It Easy Sample 7", "Image Url 7", strDate),
    SampleEntity(8, "Sample 8", "Make It Easy Sample 8", "Image Url 8", strDate),
    SampleEntity(9, "Sample 9", "Make It Easy Sample 9", "Image Url 9", strDate),
    SampleEntity(10, "Sample 10", "Make It Easy Sample 10", "Image Url 10", strDate),
)

@Composable
fun CallDatabase() {
    val context = LocalContext.current
    val sampleViewModel: SampleViewModel = viewModel(
        factory = SampleViewModelFactory(context.applicationContext as Application)
    )

    sampleViewModel.addSample(insertSampleData)

    val getAllRecord = sampleViewModel.readAllData.observeAsState(listOf()).value // fetching the data

    //Create the list item
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        //ActionBar
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Purple500),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Database List Item",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        //Create List Item
        LazyColumn(
            modifier = Modifier.padding(10.dp)
        ) {
            items(getAllRecord.size) { index ->
                AddDataListItem(getAllRecord[index])
            }
        }
    }
}

@Composable
fun AddDataListItem(sampleEntity: SampleEntity) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = sampleEntity.name,
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.padding(10.dp))

            //Split the row in two item -> description and createdDate
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = sampleEntity.desc,
                    modifier = Modifier
                        .weight(1.0f),
                    fontSize = 15.sp,
                    color = Color.Black
                )

                Text(
                    text = sampleEntity.createdDate,
                    modifier = Modifier
                        .weight(0.5f),
                    fontSize = 12.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}
















