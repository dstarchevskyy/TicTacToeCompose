package com.example.myapplication.data

import com.example.myapplication.domain.CellPosition
import com.example.myapplication.domain.PlayerSign

data class CellData(
    val position: CellPosition,
    val sign: PlayerSign?
)