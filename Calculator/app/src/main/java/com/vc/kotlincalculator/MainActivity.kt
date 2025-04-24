package com.vc.kotlincalculator

import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
 <?xml version=:"1.0" encoding="utf-8"?>
 <LinearLayout
     xml:android="https://schemas.android.com/apk/res/android"
     xml:android="https://schemas.android.com/apk/res-auto"
     xml:android="https://schemas.android.com/tools"
     android:layout_width="match_parent"
     andoid:layout_height="match_parent"
     android:orientation="vertical"
     android:background="@color/almostBlack"
     tools:context="MainActivity">
     <androidx.constraintslayout.widget.ConstraintLayout
          android:layout_width="match_parent"
          android:layout_height"0dp"
          android:layout_weight"2"
          android:padding"20dp"

    <TextView
       android:id="@id/workingsTv"
       android:layout_width="match_parent"
       android:layout_height"wrap_parent"
       android:text=""
       android:lines"2"
       android:maxlines:"2"
       android:text="@color/white"
       android:textAllignment"textEnd"
       android:textSize="25sp"
       android:layout_constraintBottom_toTopOf="@id/resutlTv"
       android:layout_constraintleft_toLeftOf="parent"
       android:layout_constraintRight_toRightOf="parent"
       android:layout_constraintTop_toTopPf="parent" />

    <TextView
       android:id="@id/resultsTvv"
       android:layout_width="match_parent"
       android:layout_height"wrap_parent"
       android:text=""
       android:lines"1"
       android:maxlines:"1"
       android:text="@color/white"
       android:textAllignment"textEnd"
       android:textSize="40sp"
       android:layout_constraintBottom_toBottomOf="parent"
       android:layout_constraintleft_toLeftOf="parent"
       android:layout_constraintTop_toTopOf="parent" />


    </androidx.constraintlayout.widget.ConstraintLayout>

    <LinearLayout
       style="@style/buttonRow">

        <Button

          style=:

    }
}