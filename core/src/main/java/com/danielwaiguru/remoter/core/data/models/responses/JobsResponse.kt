package com.danielwaiguru.remoter.core.data.models.responses

import com.danielwaiguru.remoter.core.data.models.dtos.JobDto

data class JobsResponse(
    val job_count: Int,
    val jobs: List<JobDto>
)
