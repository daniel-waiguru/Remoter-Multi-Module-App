package com.danielwaiguru.remoter.core.data.mappers

import com.danielwaiguru.remoter.core.data.models.dtos.JobDto
import com.danielwaiguru.remoter.core.domain.models.JobDomain

fun JobDto.toDomain(): JobDomain = JobDomain(
    id,
    url,
    title,
    companyName,
    category,
    jobType,
    publicationDate,
    candidateRequiredLocation,
    salary,
    description
)