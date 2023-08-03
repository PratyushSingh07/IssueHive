package com.example.findissues.api

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GithubApi

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProfileSummaryApi

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PinnedRepoApi