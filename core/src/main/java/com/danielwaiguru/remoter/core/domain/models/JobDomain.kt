package com.danielwaiguru.remoter.core.domain.models

data class JobDomain(
    val id: Int,
    val url: String,
    val title: String,
    val companyName: String,
    val category: String,
    val jobType: String,
    val publicationDate: String,
    val candidateRequiredLocation: String,
    val salary: String,
    val description: String
)