package com.laskowski.kuiks

class TestExample {
    fun testOne(app: Application) {
        app.launch()
        val table = app.table("recipes_list")
        val cell = table.cell("mulled_wine")
        cell.tap()
    }
}