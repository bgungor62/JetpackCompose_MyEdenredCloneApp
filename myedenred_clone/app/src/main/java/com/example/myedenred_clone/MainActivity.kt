package com.example.myedenred_clone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myedenred_clone.data.entity.DoluIndirim
import com.example.myedenred_clone.data.entity.ImagesApp
import com.example.myedenred_clone.ui.theme.Myedenred_cloneTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
        )
        setContent {
            Myedenred_cloneTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        topBarApp()
                    },
                    bottomBar = {
                        MyBottomBar()
                    }
                ) { paddingValues ->
                    MainScreen(paddingValues = paddingValues)
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topBarApp() {
    var isSelected by remember {
        mutableStateOf("")
    }
    TopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.dot1),
                    contentDescription = null,
                    modifier = Modifier.size(15.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.dot2),
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.4f), // %40 opaklık
                    modifier = Modifier.size(15.dp)
                )
            }

        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.anaekran_kirmizi)
        ),
        actions = {
            IconButton(onClick = {}, modifier = Modifier.size(50.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.card),
                    contentDescription = null,
                    tint = colorResource(id = R.color.white),
                    modifier = Modifier
                        .padding(10.dp)
                        .size(25.dp)
                )

            }

        },

        navigationIcon = {
            IconButton(
                onClick = {},
                modifier = Modifier.size(50.dp) // Buton alanı büyütüldü
            ) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = "",
                    tint = colorResource(R.color.white),
                    modifier = Modifier
                        .size(30.dp) // İkonun kendisi büyütüldü
                        .rotate(330f)
                )
            }

        }
    )
}


@Composable
fun MyBottomBar() {


    val items = listOf("Ana Sayfa", "Arama", "QR Ödeme", "Destek", "Ayarlar")
    val selectedIndex = remember { mutableStateOf(0) }

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp),
        containerColor = colorResource(R.color.anaekran_bottom_bar_bg)
    ) {
        items.forEachIndexed { index, label ->
            NavigationBarItem(
                icon = {
                    if (label.contains("QR Ödeme")) {
                        Icon(
                            painter = painterResource(id = R.drawable.qrcode),
                            contentDescription = label,
                            modifier = Modifier.size(24.dp),
                            tint = Color.Unspecified // PNG ise renk bozulmasın
                        )

                    } else if (label.contains("Destek")) {
                        Icon(
                            painter = painterResource(id = R.drawable.message),
                            contentDescription = label,
                            modifier = Modifier.size(24.dp),
                            tint = Color.Unspecified
                        )
                    } else {
                        Icon(
                            imageVector = when (label) {
                                "Ana Sayfa" -> Icons.Outlined.Home
                                "Arama" -> Icons.Outlined.Search
                                "Ayarlar" -> Icons.Outlined.Settings
                                else -> Icons.Outlined.Info
                            },
                            contentDescription = label
                        )
                    }
                },
                label = { Text(text = label) },
                selected = selectedIndex.value == index,
                onClick = { selectedIndex.value = index },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colorResource(R.color.anaekran_primary_bottom),
                    unselectedIconColor = colorResource(R.color.anaekran_button),
                    selectedTextColor = colorResource(R.color.anaekran_primary_bottom),
                    unselectedTextColor = colorResource(R.color.anaekran_button),
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
    /*listOf(navigationItems.value){
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.Home, contentDescription = "Ana Sayfa") },
            label = { Text( text = "Ana Sayfa") },
            selected = true,
            onClick = { /* Ana sayfa işlemi */ },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = colorResource(R.color.anaekran_primary_bottom),
                unselectedIconColor = colorResource(R.color.anaekran_button),
                selectedTextColor = colorResource(R.color.anaekran_primary_bottom),
                unselectedTextColor = colorResource(R.color.anaekran_button),
                indicatorColor = Color.Transparent
            )
        )
    }*/


}


@Composable
fun MainScreen(
    paddingValues: androidx.compose.foundation.layout.PaddingValues,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    fun loadImagesOnlineSiparis(): List<ImagesApp> {
        return listOf(
            ImagesApp(1, "ys", "Yemek Sepeti Logo"),
            ImagesApp(2, "trendyolyemek", "Trendyol Yemek Logo"),
            ImagesApp(3, "getiryemek", "Getir Yemek Logo"),
            ImagesApp(3, "sepetleyin", "Sepetleyin Logo")
        )
    }


    fun loadDoludoluIndirimleri(): List<DoluIndirim> {
        return listOf(
            DoluIndirim(
                true,
                "İstanbul'daki üye iş yerlerini görmektesiniz. Konum paylaşımına izin vererek, çevrenizdekileri görmeye başlayın.",
                "", ""
            ),
            DoluIndirim(
                false,
                "Dolu Dolu ile Kazanın",
                "Coot Döner", "10"
            ),
            DoluIndirim(
                false,
                "Dolu Dolu ile Kazanın",
                "Paşa Döner", "15"
            ),
            DoluIndirim(
                false,
                "Dolu Dolu ile Kazanın",
                "Popeyes", "5"
            ),
        )
    }

    val imagesApp = remember { mutableStateOf(loadImagesOnlineSiparis()) }
    val doluIndirim = remember { mutableStateOf(loadDoludoluIndirimleri()) }


    /*Box(modifier = Modifier.fillMaxSize()) {
    Box {
        Box {
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .height(200.dp),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp),
                colors =
                    CardDefaults.cardColors(containerColor = colorResource(R.color.anaekran_kirmizi)),
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 20.dp,
                    bottomEnd = 20.dp
                ),
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(
                            fontSize = 16.sp,
                            text = "Kart Detayı İçin Tıklayınız",
                            color = colorResource(R.color.white),
                            modifier = Modifier
                                .clickable(onClick = {}),
                            style = TextStyle(textDecoration = TextDecoration.Underline)
                        )
                    }
                }
            }

        }

        Box {
            Card(
                modifier = modifier
                    .padding(horizontal = 40.dp)
                    .fillMaxWidth()
                    .size(150.dp),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp),
                colors =
                    CardDefaults.cardColors(containerColor = colorResource(R.color.anaekran_turuncu)),
                shape = RoundedCornerShape(
                    16.dp
                ),
            ) {

            }
        }

    }



    }*/


    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        //Spacer(modifier = Modifier.height(paddingValues.calculateTopPadding()))

        Spacer(
            modifier = Modifier
                .background(colorResource(R.color.anaekran_kirmizi))
                .fillMaxWidth()
                .height(110.dp) // Top bar ile kırmızı kart arasındaki boşluk
        )
        //Üst card kısmı baslnagic
        Box {

            //Kırmızı Card
            Box {
                Card(
                    modifier = modifier
                        .height(220.dp),
                    colors =
                        CardDefaults.cardColors(containerColor = colorResource(R.color.anaekran_kirmizi)),
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 20.dp,
                        bottomEnd = 20.dp
                    ),
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Row(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 24.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(
                                fontSize = 16.sp,
                                text = "Kart Detayı İçin Tıklayınız",
                                color = colorResource(R.color.white),
                                modifier = Modifier
                                    .padding(vertical = 8.dp)
                                    .clickable(onClick = {}),
                                style = TextStyle(textDecoration = TextDecoration.Underline)
                            )


                        }
                    }

                }

            }

            //Turuncu Kart iç içe Box yapısı
            Box {
                Card(
                    modifier = modifier
                        .padding(horizontal = 40.dp)
                        .fillMaxWidth()
                        .size(150.dp),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp),
                    colors =
                        CardDefaults.cardColors(containerColor = colorResource(R.color.anaekran_turuncu)),
                    shape = RoundedCornerShape(
                        8.dp
                    ),
                ) {
                    Column {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            text = "Ticket Restaurant Yemek Kartı",
                            color = colorResource(R.color.white),
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.size(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(start = 32.dp, bottom = 4.dp),
                                text = "2654",
                                color = colorResource(R.color.white),
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp
                            )
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = ".63TL",
                                fontSize = 16.sp,
                                color = colorResource(R.color.white),
                                fontWeight = FontWeight.Bold
                            )
                        }

                    }

                }
            }
            Image(
                painter = painterResource(R.drawable.yaprak),
                contentDescription = null,
                modifier = Modifier
                    .size(180.dp)
                    .align(Alignment.Center)
                    .offset(x = (16).dp, y = (-110).dp)
                    .padding(top = 8.dp)
            )
            Image(
                painter = painterResource(R.drawable.yaprak),
                contentDescription = null,
                modifier = Modifier
                    .size(72.dp)
                    .align(Alignment.TopStart)
                    .offset(x = 0.dp, y = (120).dp)
                    .blur(3.dp)
            )
            //İç içe Box yapısı devam Buton
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(240.dp),
                contentAlignment = Alignment.BottomCenter // Butonu tepeye hizalar
            ) {
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.anaekran_button),
                        contentColor = colorResource(R.color.white),
                        disabledContainerColor = colorResource(R.color.white),
                        disabledContentColor = colorResource(R.color.white)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        "Online Sipariş",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            //Üst card kısmı bitis

            Image(
                painter = painterResource(R.drawable.yaprak),
                contentDescription = null,
                modifier = Modifier
                    .size(72.dp)
                    .align(Alignment.CenterEnd)
                    .offset(x = (6).dp, y = (-110).dp)
                    .blur(3.dp)
            )
            Box(
                modifier = Modifier
                    .padding(horizontal = 40.dp)
                    .padding(bottom = 20.dp)
                    .align(Alignment.CenterEnd)
                    .height(80.dp)
                    .width(100.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {

                Image(
                    painter = painterResource(id = R.drawable.makarna),
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .align(Alignment.BottomEnd)
                        .offset(x = 25.dp, y = 10.dp) // Görseli dışa taşır
                )
            }


        }




        //Online Siparis baslangic
        // Box yerine Column kullanın ki, altındaki LazyRow başlığın altına insin.
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            // Başlık ve İlerleme Oku
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    // Sadece yatayda dolgu uygulayın. Dikey dolgu artık yukarıdaki Column'da.
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "ONLİNE SİPARİŞ",
                    color = colorResource(R.color.anaekran_button),
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.width(0.dp))

                IconButton(
                    onClick = { /* Tıklama eylemi */ },
                    modifier = Modifier.size(24.dp) // İkonu küçülterek başlığa yaklaştırın
                ) {
                    Icon(
                        Icons.Outlined.ArrowForward,
                        contentDescription = "Online Sipariş Sayfasına Git",
                        tint = colorResource(R.color.anaekran_button)
                    )
                }
            }

            // LazyRow için dikey boşluk
            Spacer(modifier = Modifier.height(16.dp))

            // Online Sipariş Card başlangıç
            LazyRow(
                // İçeriği soldan başlatmak için padding kullanın.
                modifier = modifier.padding(start = 16.dp, end = 16.dp)
            ) {
                items(imagesApp.value) { image ->
                    // Her bir eleman arasında 12.dp boşluk bırakın.
                    Spacer(modifier = Modifier.width(8.dp))

                    if (imagesApp.value.indexOf(image) > 0) {
                        Spacer(modifier = Modifier.width(12.dp))
                    }

                    Card(
                        modifier = Modifier
                            .width(150.dp)
                            .height(80.dp),
                        shape = RoundedCornerShape(0.dp),
                        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp),
                        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.white)),
                        onClick = { /* Kart tıklama eylemi */ }
                    ) {
                        Image(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                                .align(Alignment.CenterHorizontally),
                            painter = painterResource(
                                id = LocalContext.current.resources.getIdentifier(
                                    image.name,
                                    "drawable",
                                    LocalContext.current.packageName
                                )
                            ),
                            contentScale = ContentScale.Fit,
                            contentDescription = image.description
                        )
                    }
                }
            }
        }        //Online Siparis bitis


        //indirimler kısmı başlangıç
        Column(
            modifier = Modifier
                .fillMaxWidth()
                // Üstten 32.dp boşluk, tüm bu bölüm için geçerli olsun.
                .padding(top = 32.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "DOLU DOLU İNDİRİMLERİ ",
                    color = colorResource(R.color.anaekran_button),
                    fontWeight = FontWeight.Bold,

                    )
                Spacer(modifier = Modifier.width(2.dp))
                IconButton(onClick = {}, modifier = Modifier.size(24.dp)) {
                    Icon(
                        Icons.Outlined.ArrowForward, contentDescription = "",
                        tint = colorResource(R.color.anaekran_button)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(modifier = modifier.padding(start = 16.dp, end = 16.dp)) {
                items(doluIndirim.value) {
                    val data = it
                    Spacer(modifier = Modifier.width(8.dp))

                    if (data.girisMi) {
                        Card(
                            modifier = Modifier
                                .width(300.dp)
                                .height(160.dp),
                            shape = RoundedCornerShape(0.dp),
                            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp),
                            colors = CardDefaults.cardColors(containerColor = colorResource(R.color.white))
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "İstanbul'daki üye iş yerlerini görmektesiniz. Konum paylaşımına izin vererek, çevrenizdekileri görmeye başlayın.",
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Center,
                                    fontSize = 13.sp,
                                    color = colorResource(R.color.gray)
                                )
                            }
                        }
                    } else {
                        Card(
                            modifier = Modifier
                                .width(300.dp)
                                .height(160.dp),
                            shape = RoundedCornerShape(4.dp),
                            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp),
                            colors = CardDefaults.cardColors(containerColor = colorResource(R.color.white))
                        ) {
                            Column {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                        .background(colorResource(R.color.anaekran_acikkirmizi)),
                                    contentAlignment = Alignment.TopCenter,
                                ) {
                                    Text(
                                        text = data.baslik,
                                        fontWeight = FontWeight.W500,
                                        textAlign = TextAlign.Center,
                                        fontSize = 15.sp,
                                        letterSpacing = 0.2.sp,
                                        color = colorResource(R.color.anaekran_acikkirmizi_text)
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.TopCenter,
                                ) {
                                    Row() {
                                        Text(
                                            text = data.marka,
                                            fontWeight = FontWeight.W600,
                                            textAlign = TextAlign.Left,
                                            fontSize = 15.sp,
                                            letterSpacing = 0.2.sp,
                                            color = colorResource(R.color.anaekran_button)
                                        )
                                        Spacer(modifier = modifier.size(120.dp))
                                        Text(
                                            text = "%${data.indirimOrani}",
                                            fontWeight = FontWeight.ExtraBold,
                                            textAlign = TextAlign.End,
                                            fontSize = 20.sp,
                                            letterSpacing = 0.0.sp,
                                            color = colorResource(R.color.anaekran_kirmizi)
                                        )
                                    }

                                }
                            }
                        }
                    }


                }

            }
        }
        //indirimle kısmı bitiş

        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "DOLU DOLU İNDİRİMLERİ ",
                    color = colorResource(R.color.anaekran_button),
                    fontWeight = FontWeight.Bold,

                    )
                IconButton(onClick = {}, modifier = Modifier.size(50.dp)) {
                    Icon(
                        Icons.Outlined.ArrowForward, contentDescription = "",
                        tint = colorResource(R.color.anaekran_button)
                    )
                }
            }
            LazyRow(modifier = modifier.padding(start = 16.dp, end = 16.dp)) {
                items(imagesApp.value) {
                    val image = it
                    Spacer(modifier = Modifier.width(24.dp))

                    Card(
                        modifier = Modifier
                            .width(300.dp)
                            .height(150.dp),
                        shape = RoundedCornerShape(0.dp),
                        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp),
                        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.white))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "İstanbul'daki üye iş yerlerini görmektesiniz. Konum paylaşımına izin vererek, çevrenizdekileri görmeye başlayın.",
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp
                            )
                        }
                    }

                }

            }
        }

    }


}



