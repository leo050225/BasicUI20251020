package tw.edu.pu.csim.tcyang.basicui


import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tw.edu.pu.csim.tcyang.basicui.ui.theme.BasicUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicUITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Main(modifier: Modifier = Modifier) {

    var Animals = listOf(R.drawable.animal0, R.drawable.animal1,
        R.drawable.animal2, R.drawable.animal3,
        R.drawable.animal4, R.drawable.animal5,
        R.drawable.animal6, R.drawable.animal7,
        R.drawable.animal8, R.drawable.animal9)

    var AnimalsName = arrayListOf("鴨子","企鵝",
        "青蛙","貓頭鷹","海豚", "牛", "無尾熊", "獅子", "狐狸", "小雞")

    var flag by remember { mutableStateOf("test") }

    val context = LocalContext.current

    var mper: MediaPlayer? by remember { mutableStateOf(null) }

    // 🔸 新增狀態來控制目前的圖片（預設為鴨子）
    var isDuck by remember { mutableStateOf(true) }

    Column (
        modifier = modifier
            .fillMaxSize() // 1. 設定全螢幕（填滿父容器）
            .background(Color(0xFFE0BBE4)), // 4. 設定背景為淺紫色
        horizontalAlignment = Alignment.CenterHorizontally, // 2. 設定水平置中
        verticalArrangement = Arrangement.Top // 3. 設定垂直靠上
    ) {
        Text(
            text = stringResource(R.string.app_title),
            fontSize = 25.sp,
            color = Color.Blue,
            fontFamily = FontFamily(Font(R.font.kai))

        )

        Spacer(modifier = Modifier.size(10.dp))

        Text(
            text = stringResource(R.string.app_author),
            fontSize = 20.sp,
            color = Color(0xFF654321)
        )

        Spacer(modifier = Modifier.size(10.dp))

        Row {
            Image(
                painter = painterResource(id = R.drawable.android),
                contentDescription = "Android 圖示",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.Yellow),
                alpha = 0.6f,
            )

            Image(
                painter = painterResource(id = R.drawable.compose),
                contentDescription = "Compose icon",
                modifier = Modifier.size(100.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.firebase),
                contentDescription = "Firebase icon",
                modifier = Modifier.size(100.dp)
            )

        }

        Spacer(modifier = Modifier.size(10.dp))

        LazyRow {
            items(51) { index ->
                Text(text = "$index:")
                Text(text = AnimalsName[index % 10])

                Image(
                    painter = painterResource(id = Animals[index % 10]),
                    contentDescription = "可愛動物",
                    modifier = Modifier.size(60.dp)
                )

            }
        }

        Spacer(modifier = Modifier.size(10.dp))
        Row {

            Button(
                onClick = {
                    mper?.release()  //釋放資源
                    mper = null // 清除舊引用
                    mper = MediaPlayer.create(context, R.raw.tcyang) //設定音樂
                    mper?.start()  } , //開始播放
                modifier = Modifier
                    .fillMaxWidth(0.33f)
                    .fillMaxHeight(0.8f),
                colors = buttonColors(Color.Green)
            ) {
                Text(text = "歡迎", color = Color.Blue)
                Text(text = "修課", color = Color.Red)
                Image(
                    painterResource(id = R.drawable.teacher),
                    contentDescription ="teacher icon")
            }

            Button(
                onClick = {
                    mper?.release()  //釋放資源
                    mper = null // 清除舊引用
                    mper = MediaPlayer.create(context, R.raw.fly) //設定音樂
                    mper?.start()  },  //開始播放
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(0.4f),
                colors = buttonColors(Color.Blue)
            ) {
                Text(text = "展翅飛翔", color = Color.White)
                Image(
                    painterResource(id = R.drawable.fly),
                    contentDescription ="fly icon")
            }

            Button(
                onClick = {
                    // 停止並釋放 MediaPlayer
                    mper?.stop()
                    mper?.release()
                    mper = null

                    // 將 Context 安全地轉換(as?)為 Activity，並儲存到變數中
                    val activity = context as? Activity
                    // 如果成功轉換為 Activity，則呼叫 finish()
                    activity?.finish()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00BFFF)),
                shape = CutCornerShape(10),
                border = BorderStroke(1.dp, Color.Blue),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp)
            ) {
                Text(text = "結束App")
            }

        }

        Spacer(modifier = Modifier.size(20.dp))

        // 🐥🐯 🔸 新增的圖形按鈕放在三個功能下面
        Button(
            onClick = {
                isDuck = !isDuck  // 切換圖片
            },
            colors = buttonColors(Color(0xFFFFC107))
        ) {
            Image(
                painter = painterResource(
                    id = if (isDuck) Animals[0] else Animals[7]  // 鴨子 vs 獅子
                ),
                contentDescription = if (isDuck) "鴨子" else "獅子",
                modifier = Modifier.size(100.dp)
            )
        }

        Spacer(modifier = Modifier.size(10.dp))

        DisposableEffect(Unit) { // Unit 作為 key 表示這個 effect 只會執行一次
            onDispose {
                // 釋放 MediaPlayer 資源，避免記憶體洩漏
                mper?.release()
                mper = null
            }
        }




    }
}
