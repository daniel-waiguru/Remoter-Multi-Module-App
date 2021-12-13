package com.danielwaiguru.remoter.core.data.models.dtos

import com.squareup.moshi.Json

data class JobDto(
    val id: Int,
    val url: String,
    val title: String,
    @field:Json(name = "company_name")
    val companyName: String,
    val category: String,
    val tags: List<Any>,
    @field:Json(name = "job_type")
    val jobType: String,
    @field:Json(name = "publication_date")
    val publicationDate: String,
    @field:Json(name = "candidate_required_location")
    val candidateRequiredLocation: String,
    val salary: String,
    val description: String
)
