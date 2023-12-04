package com.example.hci_1

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.hci_1.lullabyer.L
import kotlin.collections.List as ____KtList

public object Lullabyer

private var __AllIcons: ____KtList<ImageVector>? = null

public val Lullabyer.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(L)
    return __AllIcons!!
  }
