package com.prasuco.gita.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp

/** Hand-drawn flat onboarding art: dawn, crossroads, carry. No assets needed. */
@Composable
fun OnboardArt(page: Int) {
    Canvas(
        Modifier.fillMaxWidth().height(260.dp).clip(RoundedCornerShape(28.dp))
    ) {
        when (page.coerceIn(0, 2)) {
            0 -> drawDawn()
            1 -> drawCrossroads()
            else -> drawCarry()
        }
    }
}

private fun DrawScope.sunRays(cx: Float, cy: Float, r: Float, n: Int, color: Color, len: Float) {
    for (i in 0 until n) {
        rotate((360f / n) * i, Offset(cx, cy)) {
            drawLine(color, Offset(cx, cy - r), Offset(cx, cy - r - len), strokeWidth = len * 0.28f, cap = StrokeCap.Round)
        }
    }
}

private fun DrawScope.leaf(cx: Float, cy: Float, rx: Float, ry: Float, angle: Float, color: Color) {
    rotate(angle, Offset(cx, cy)) {
        drawOval(color, Offset(cx - rx, cy - ry), Size(rx * 2f, ry * 2f))
    }
}

private fun DrawScope.person(
    cx: Float, footY: Float, ht: Float,
    shirt: Color = Color(0xFF1E3A8A),
    pants: Color = Color(0xFFDC2626),
    skin: Color = Color(0xFFF2C9A0),
    hair: Color = Color(0xFF1D4ED8)
) {
    drawLine(pants, Offset(cx, footY - ht * 0.42f), Offset(cx - ht * 0.07f, footY), strokeWidth = ht * 0.055f, cap = StrokeCap.Round)
    drawLine(pants, Offset(cx, footY - ht * 0.42f), Offset(cx + ht * 0.07f, footY), strokeWidth = ht * 0.055f, cap = StrokeCap.Round)
    drawLine(shirt, Offset(cx, footY - ht * 0.78f), Offset(cx, footY - ht * 0.40f), strokeWidth = ht * 0.13f, cap = StrokeCap.Round)
    drawLine(shirt, Offset(cx, footY - ht * 0.72f), Offset(cx + ht * 0.09f, footY - ht * 0.52f), strokeWidth = ht * 0.05f, cap = StrokeCap.Round)
    val headR = ht * 0.11f
    val headY = footY - ht + headR
    drawCircle(skin, headR, Offset(cx, headY))
    drawArc(hair, 180f, 180f, true, Offset(cx - headR, headY - headR), Size(headR * 2f, headR * 2f))
    drawOval(Color.White.copy(alpha = 0.5f), Offset(cx - ht * 0.16f, footY - ht * 0.01f), Size(ht * 0.32f, ht * 0.035f))
}

private fun DrawScope.drawDawn() {
    val w = size.width
    val h = size.height
    drawRect(Brush.verticalGradient(listOf(Color(0xFF8B5CF6), Color(0xFFFB7185), Color(0xFFFDE68A), Color(0xFFF59E0B)), startY = 0f, endY = h))
    val cx = w * 0.5f
    val cy = h * 0.58f
    val r = h * 0.15f
    drawCircle(Color.White.copy(alpha = 0.25f), r * 2.1f, Offset(cx, cy))
    sunRays(cx, cy, r * 1.35f, 12, Color.White.copy(alpha = 0.75f), r * 0.5f)
    drawCircle(Color(0xFFFFF9DB), r, Offset(cx, cy))
    drawCircle(Color(0xFF6D28D9), radius = w * 0.42f, center = Offset(w * 0.12f, h * 1.25f))
    drawCircle(Color(0xFF4C1D95), radius = w * 0.46f, center = Offset(w * 0.92f, h * 1.30f))
    val bird = Color(0xFF1E1B4B).copy(alpha = 0.55f)
    listOf(Offset(w * 0.22f, h * 0.22f), Offset(w * 0.32f, h * 0.15f), Offset(w * 0.74f, h * 0.24f)).forEach { b ->
        val s = w * 0.022f
        drawLine(bird, Offset(b.x - s, b.y), Offset(b.x, b.y - s * 0.7f), strokeWidth = 3.dp.toPx(), cap = StrokeCap.Round)
        drawLine(bird, Offset(b.x + s, b.y), Offset(b.x, b.y - s * 0.7f), strokeWidth = 3.dp.toPx(), cap = StrokeCap.Round)
    }
    val lx = w * 0.5f
    val ly = h * 0.99f
    val lc = Color.White.copy(alpha = 0.92f)
    leaf(lx, ly - h * 0.05f, w * 0.020f, h * 0.05f, 0f, lc)
    leaf(lx - w * 0.025f, ly - h * 0.035f, w * 0.018f, h * 0.04f, -28f, lc)
    leaf(lx + w * 0.025f, ly - h * 0.035f, w * 0.018f, h * 0.04f, 28f, lc)
}

private fun DrawScope.drawCrossroads() {
    val w = size.width
    val h = size.height
    drawRect(
        Brush.horizontalGradient(
            listOf(Color(0xFF64748B), Color(0xFFCBD5E1), Color(0xFFFDE68A), Color(0xFFF97316)),
            startX = 0f, endX = w
        )
    )
    val sx = w * 0.78f
    val sy = h * 0.32f
    val sr = h * 0.11f
    drawCircle(Color.White.copy(alpha = 0.30f), sr * 2f, Offset(sx, sy))
    sunRays(sx, sy, sr * 1.3f, 10, Color.White.copy(alpha = 0.8f), sr * 0.45f)
    drawCircle(Color(0xFFFFF3C4), sr, Offset(sx, sy))
    val cloud = Color(0xFF475569)
    val ccx = w * 0.24f
    val ccy = h * 0.24f
    val cr = h * 0.085f
    drawCircle(cloud, cr * 1.15f, Offset(ccx - cr * 0.9f, ccy + cr * 0.25f))
    drawCircle(cloud, cr * 1.35f, Offset(ccx, ccy))
    drawCircle(cloud, cr * 1.0f, Offset(ccx + cr * 1.0f, ccy + cr * 0.3f))
    val rain = Color(0xFFCBD5E1)
    for (i in 0..4) {
        val rx = ccx - cr * 1.1f + i * cr * 0.55f
        drawLine(rain, Offset(rx, ccy + cr * 0.9f), Offset(rx - cr * 0.25f, ccy + cr * 1.7f), strokeWidth = 4.dp.toPx(), cap = StrokeCap.Round)
    }
    val road = Path().apply {
        moveTo(w * 0.55f, h * 1.02f)
        quadraticBezierTo(w * 0.50f, h * 0.78f, w * 0.30f, h * 0.70f)
    }
    drawPath(road, Color(0xFFF1F5F9), style = Stroke(width = h * 0.09f, cap = StrokeCap.Round))
    person(w * 0.30f, h * 0.70f, h * 0.17f)
}

private fun cardWithLines(scope: DrawScope, cx: Float, cy: Float, wdt: Float, hgt: Float, angle: Float, color: Color) {
    scope.rotate(angle, Offset(cx, cy)) {
        scope.drawRoundRect(color, Offset(cx - wdt / 2f, cy - hgt / 2f), Size(wdt, hgt), CornerRadius(12.dp.toPx()))
        val lc = Color(0xFF64748B).copy(alpha = 0.5f)
        for (i in 0..2) {
            val ly = cy - hgt * 0.18f + i * hgt * 0.14f
            scope.drawLine(lc, Offset(cx - wdt * 0.32f, ly), Offset(cx + wdt * 0.32f, ly), strokeWidth = 4.dp.toPx(), cap = StrokeCap.Round)
        }
        scope.drawCircle(Color(0xFFFBBF24), wdt * 0.06f, Offset(cx + wdt * 0.28f, cy - hgt * 0.32f))
    }
}

private fun DrawScope.drawCarry() {
    val w = size.width
    val h = size.height
    drawRect(Brush.verticalGradient(listOf(Color(0xFFDBEAFE), Color(0xFFEDE9FE))))
    val sx = w * 0.80f
    val sy = h * 0.20f
    val sr = h * 0.09f
    sunRays(sx, sy, sr * 1.35f, 12, Color(0xFFFBBF24).copy(alpha = 0.85f), sr * 0.55f)
    drawCircle(Color(0xFFFBBF24), sr, Offset(sx, sy))
    leaf(w * 0.10f, h * 0.85f, w * 0.05f, h * 0.10f, -25f, Color(0xFF34D399))
    leaf(w * 0.16f, h * 0.92f, w * 0.04f, h * 0.08f, -45f, Color(0xFF10B981))
    leaf(w * 0.90f, h * 0.88f, w * 0.05f, h * 0.09f, 25f, Color(0xFFA78BFA))
    leaf(w * 0.84f, h * 0.95f, w * 0.04f, h * 0.07f, 45f, Color(0xFF8B5CF6))
    cardWithLines(this, w * 0.17f, h * 0.42f, w * 0.20f, h * 0.30f, -8f, Color(0xFFF9A8D4))
    cardWithLines(this, w * 0.83f, h * 0.42f, w * 0.20f, h * 0.30f, 8f, Color(0xFFFDE68A))
    val pw = w * 0.30f
    val ph = h * 0.62f
    val px = w * 0.5f - pw / 2f
    val py = h * 0.30f
    drawRoundRect(Color(0xFF1E1B4B), Offset(px, py), Size(pw, ph), CornerRadius(ph * 0.12f))
    val m = pw * 0.07f
    drawRoundRect(
        Brush.verticalGradient(listOf(Color(0xFF7C3AED), Color(0xFFC084FC))),
        Offset(px + m, py + m), Size(pw - 2f * m, ph - 2f * m), CornerRadius(ph * 0.08f)
    )
    val cw = pw * 0.72f
    val chh = ph * 0.30f
    drawRoundRect(Color.White, Offset(px + (pw - cw) / 2f, py + ph * 0.14f), Size(cw, chh), CornerRadius(8.dp.toPx()))
    val lc = Color(0xFFCBD5E1)
    for (i in 0..2) {
        val ly = py + ph * 0.20f + i * ph * 0.055f
        drawLine(lc, Offset(px + (pw - cw) / 2f + cw * 0.12f, ly), Offset(px + (pw + cw) / 2f - cw * 0.12f, ly), strokeWidth = 4.dp.toPx(), cap = StrokeCap.Round)
    }
    for (i in 0..3) {
        drawCircle(Color.White.copy(alpha = if (i == 1) 1f else 0.45f), pw * 0.045f, Offset(px + pw * (0.28f + i * 0.15f), py + ph * 0.78f))
    }
}
