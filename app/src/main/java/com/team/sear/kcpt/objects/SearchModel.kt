package com.team.sear.kcpt.objects

import ir.mirrajabi.searchdialog.core.Searchable

class SearchModel(private var mTitle: String?) : Searchable {
    override fun getTitle(): String {
        return mTitle!!
    }
}