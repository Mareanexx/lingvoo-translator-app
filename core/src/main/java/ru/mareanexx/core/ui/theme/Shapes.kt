package ru.mareanexx.core.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    extraSmall = RoundedCornerShape(15.dp),
    small = RoundedCornerShape(20.dp),
    medium = RoundedCornerShape(25.dp),
    large = RoundedCornerShape(35.dp),
    extraLarge = RoundedCornerShape(50.dp)
)

val FirstCardShape = RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp, bottomStart = 20.dp, bottomEnd = 20.dp)
val HeaderShape = RoundedCornerShape(bottomEnd = 35.dp, bottomStart = 35.dp)
val WordSkeletonShape = RoundedCornerShape(10.dp)