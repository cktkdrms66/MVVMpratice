package com.packages.kakaomvvmpratice.model.service

data class SearchResponse(val documents : List<Document>)
data class Document(var thumbnail : String, var title : String)