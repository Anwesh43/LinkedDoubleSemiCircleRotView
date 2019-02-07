package com.anwesh.uiprojects.doublesemicirclerotview

/**
 * Created by anweshmishra on 07/02/19.
 */
import android.view.View
import android.view.MotionEvent
import android.app.Activity
import android.content.Context
import android.graphics.*

val nodes : Int = 5
val parts : Int = 2
val scGap : Float = 0.05f
val scDiv : Double = 0.51
val sizeFactor : Float = 2.9f
val foreColor : Int = Color.parseColor("#FF6F00")
val backColor : Int = Color.parseColor("#212121")

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()
fun Float.mirrorValue(a : Int, b : Int) : Float {
    val k : Float = scaleFactor()
    return (1 - k) * a.inverse() + k * b.inverse()
}
fun Float.updateValue(dir : Float, a : Int, b : Int) : Float = mirrorValue(a, b) * dir * scGap

fun Float.getRectForScale(sc : Float) : RectF = RectF(0f, -this / 2, this , this / 2 * sc)

fun Canvas.drawFillSemiCircle(i : Int, size : Float, sc : Float, paint : Paint) {
    val rect : RectF = RectF(0f, -size / 2, size, size / 2)
    save()
    translate(-size + i * size, 0f)
    val path : Path = Path()
    path.addArc(size.getRectForScale(1f), 0f, 180f)
    clipPath(path)
    drawRect(size.getRectForScale(sc.divideScale(i, parts)), paint)
    restore()
}

fun Canvas.drawDSCRNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = h / (nodes + 1)
    val size : Float = gap / sizeFactor
    val sc1 : Float = scale.divideScale(0, 2)
    val sc2 : Float = scale.divideScale(1, 2)
    paint.color = foreColor
    save()
    translate( w / 2, gap * (i + 1))
    for (j in 0..(parts - 1)) {
        save()
        rotate(180f * j * sc2)
        drawFillSemiCircle(j, size, sc1, paint)
        restore()
    }
    restore()
}