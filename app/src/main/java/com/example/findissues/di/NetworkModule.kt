package com.example.findissues.di

import com.example.findissues.api.*
import com.example.findissues.api.github.GithubApiService
import com.example.findissues.api.pinned.PinnedRepoService
import com.example.findissues.api.profile.ProfileStatsService
import com.example.findissues.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    @GithubApi
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @ProfileSummaryApi
    fun providesProfileSummaryApi(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.PROFILE_SUMMARY_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @PinnedRepoApi
    fun providesPinnedRepoApi(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.PINNED_REPO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesGithubService(@GithubApi retrofit: Retrofit): GithubApiService {
        return retrofit.create(GithubApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesProfileSummaryService(@ProfileSummaryApi retrofit: Retrofit): ProfileStatsService {
        return retrofit.create(ProfileStatsService::class.java)
    }

    @Provides
    @Singleton
    fun providesPinnedRepoService(@PinnedRepoApi retrofit: Retrofit): PinnedRepoService {
        return retrofit.create(PinnedRepoService::class.java)
    }
}