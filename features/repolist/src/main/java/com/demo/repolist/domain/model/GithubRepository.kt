package com.demo.testdomain.domain.model

data class GithubRepository(
    val name: String,
    val description: String,
    val owner: Owner,
    val url: String,
    val createdAt: String,
    val homepageUrl: String,
    val languages: List<String>,
    val stargazerCount: Int,
    val isPrivate: Boolean
)
