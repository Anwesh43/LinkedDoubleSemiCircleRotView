package com.anwesh.uiprojects.linkeddoublesemicirclerotview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.doublesemicirclerotview.DoubleSemiCircleRotView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DoubleSemiCircleRotView.create(this)
    }
}
