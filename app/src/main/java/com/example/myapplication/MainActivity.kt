package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ImgGal()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImgGal() {
    ImgGalUi()
}

@Composable
fun ImgGalUi(modifier: Modifier = Modifier){
    val imgs = listOf(
        "oip",
        "changemymind",
        "twogirloneguy",
        "slap"
    )

    val (resIdx,setResIdx) = remember {
        mutableStateOf(0)
    }

    val imgResName = imgs.getOrElse(resIdx) {"oip"}

    val imageResources = painterResource(getResourceIdByName(imgResName))

    Column(
        modifier =modifier
            .fillMaxSize(),
        horizontalAlignment =Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(text = imgResName,
            modifier =  modifier.padding(18.dp),
            style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold)
        )
    Image(
        painter = imageResources,
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .size(300.dp)
    )
    Row {
        Button(onClick = { setResIdx(resIdx - 1) }) {
            if (resIdx < 0) {
                setResIdx(imgs.size -1)
            }
            Text(text = "Previous")
        }
        Button(onClick = { setResIdx(resIdx + 1) }) {
            if (resIdx > 4) {
                setResIdx(resIdx - 4)
            }
            Text(text = "Next")
        }
    }
    }
}

fun getResourceIdByName(resourceName: String): Int {
    return try {
        val res = R.drawable::class.java
        val field = res.getField(resourceName)
        field.getInt(null)
    } catch (e: Exception) {
        e.printStackTrace()
        -1 // Return a default value or handle the error as needed
    }
}

