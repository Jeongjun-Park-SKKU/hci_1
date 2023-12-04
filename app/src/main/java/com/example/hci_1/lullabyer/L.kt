package com.example.hci_1.lullabyer

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.example.hci_1.Lullabyer

public val Lullabyer.L: ImageVector
    get() {
        if (_l != null) {
            return _l!!
        }
        _l = Builder(name = "L", defaultWidth = 64.0.dp, defaultHeight = 64.0.dp, viewportWidth =
                64.0f, viewportHeight = 64.0f).apply {
            path(fill = linearGradient(0.00312488f to Color(0xFF00008B), 0.786721f to
                    Color(0xFF87CEEB), start = Offset(5.0f,4.0f), end = Offset(73.5f,73.5f)), stroke
                    = null, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(16.0f, 0.0f)
                lineTo(48.0f, 0.0f)
                arcTo(16.0f, 16.0f, 0.0f, false, true, 64.0f, 16.0f)
                lineTo(64.0f, 48.0f)
                arcTo(16.0f, 16.0f, 0.0f, false, true, 48.0f, 64.0f)
                lineTo(16.0f, 64.0f)
                arcTo(16.0f, 16.0f, 0.0f, false, true, 0.0f, 48.0f)
                lineTo(0.0f, 16.0f)
                arcTo(16.0f, 16.0f, 0.0f, false, true, 16.0f, 0.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFAFF00)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(20.1572f, 30.1198f)
                curveTo(19.3696f, 31.2827f, 19.1f, 32.0518f, 18.9777f, 33.4527f)
                curveTo(17.8148f, 32.6651f, 17.0457f, 32.3955f, 15.6447f, 32.2732f)
                curveTo(16.4324f, 31.1103f, 16.7019f, 30.3412f, 16.8242f, 28.9402f)
                curveTo(17.9903f, 29.7264f, 18.7562f, 29.9974f, 20.1572f, 30.1198f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFAFF00)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(25.1826f, 24.3974f)
                curveTo(24.6137f, 25.4559f, 24.4479f, 26.1755f, 24.4544f, 27.515f)
                curveTo(23.4197f, 26.6662f, 22.7549f, 26.3469f, 21.5726f, 26.1202f)
                curveTo(22.1416f, 25.0616f, 22.3074f, 24.342f, 22.3009f, 23.0025f)
                curveTo(23.3381f, 23.8502f, 24.0004f, 24.1707f, 25.1826f, 24.3974f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFAFF00)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(34.5972f, 23.2287f)
                curveTo(33.8096f, 24.3916f, 33.54f, 25.1607f, 33.4177f, 26.5617f)
                curveTo(32.2548f, 25.774f, 31.4857f, 25.5045f, 30.0847f, 25.3821f)
                curveTo(30.8724f, 24.2192f, 31.1419f, 23.4501f, 31.2642f, 22.0492f)
                curveTo(32.4272f, 22.8368f, 33.1962f, 23.1064f, 34.5972f, 23.2287f)
                close()
            }
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(52.0501f, 19.0257f)
                curveTo(51.8801f, 19.2983f, 51.6947f, 19.5538f, 51.5024f, 19.8023f)
                curveTo(51.7059f, 17.4358f, 51.0882f, 16.119f, 49.3243f, 14.7889f)
                lineTo(48.863f, 15.2074f)
                curveTo(49.781f, 16.6593f, 49.9978f, 19.6223f, 49.6191f, 21.599f)
                curveTo(49.0443f, 22.0195f, 48.4295f, 22.386f, 47.7857f, 22.7141f)
                curveTo(47.6227f, 19.9691f, 46.3331f, 17.4763f, 44.3869f, 15.8991f)
                lineTo(43.9298f, 16.319f)
                curveTo(45.5856f, 18.658f, 45.7597f, 21.7109f, 44.7383f, 23.9735f)
                curveTo(43.8323f, 24.284f, 42.9158f, 24.5648f, 42.018f, 24.8401f)
                curveTo(42.5688f, 21.3368f, 41.6543f, 17.6834f, 39.3545f, 15.0357f)
                lineTo(38.8489f, 15.3989f)
                curveTo(40.7152f, 18.4935f, 40.7367f, 22.6044f, 39.0576f, 25.7725f)
                curveTo(38.3016f, 26.008f, 37.5426f, 26.2519f, 36.7833f, 26.5028f)
                curveTo(36.4671f, 22.2612f, 34.2793f, 18.2757f, 30.7629f, 15.9615f)
                lineTo(30.3533f, 16.4285f)
                curveTo(33.1447f, 19.4924f, 34.0738f, 24.1273f, 32.7433f, 27.9435f)
                curveTo(31.9696f, 28.2466f, 31.2011f, 28.5681f, 30.4463f, 28.9109f)
                curveTo(29.4558f, 24.1696f, 26.6402f, 19.9095f, 22.5877f, 17.3255f)
                lineTo(22.1893f, 17.8011f)
                curveTo(25.716f, 21.3947f, 27.2055f, 26.4478f, 26.5976f, 30.9321f)
                curveTo(25.7955f, 31.4228f, 25.0166f, 31.9546f, 24.268f, 32.5276f)
                curveTo(23.7464f, 30.9179f, 22.9979f, 29.4094f, 22.0672f, 28.0506f)
                curveTo(20.3239f, 25.5133f, 17.9317f, 23.4806f, 15.1969f, 22.2727f)
                lineTo(14.883f, 22.8054f)
                curveTo(19.0874f, 25.6927f, 21.4124f, 30.5872f, 21.3391f, 35.1958f)
                curveTo(20.646f, 35.9373f, 20.0204f, 36.7231f, 19.4681f, 37.5575f)
                curveTo(17.2507f, 34.0491f, 13.6084f, 31.3738f, 10.2129f, 29.1481f)
                lineTo(9.8131f, 29.6208f)
                curveTo(11.9004f, 31.6285f, 13.9801f, 33.6503f, 15.5152f, 35.9459f)
                curveTo(16.5677f, 37.5076f, 17.3415f, 39.2337f, 17.682f, 40.9507f)
                curveTo(17.4008f, 41.6418f, 17.155f, 42.3473f, 16.9534f, 43.0631f)
                curveTo(14.7808f, 40.4348f, 11.8818f, 38.5082f, 8.7183f, 37.5849f)
                lineTo(8.4793f, 38.1549f)
                curveTo(12.3466f, 40.0963f, 15.1322f, 43.612f, 16.2057f, 47.4191f)
                curveTo(15.9673f, 51.0901f, 16.6674f, 54.811f, 18.2816f, 58.0364f)
                lineTo(18.8833f, 57.8745f)
                curveTo(18.4904f, 48.9567f, 20.7774f, 40.0165f, 28.9342f, 34.9822f)
                curveTo(32.3223f, 32.7597f, 36.4374f, 31.057f, 40.4477f, 29.2545f)
                lineTo(40.4491f, 29.2574f)
                lineTo(40.4506f, 29.2531f)
                curveTo(41.9192f, 28.5941f, 43.3781f, 27.9222f, 44.7795f, 27.2047f)
                curveTo(48.0879f, 25.504f, 51.7786f, 23.0467f, 52.6327f, 19.2281f)
                curveTo(52.6269f, 19.2308f, 52.0501f, 19.0257f, 52.0501f, 19.0257f)
                close()
            }
        }
        .build()
        return _l!!
    }

private var _l: ImageVector? = null
