package com.example.findissues.api

//import com.example.findissues.models.home.Repository
//import io.ktor.client.*
//import io.ktor.client.engine.android.*
//import io.ktor.client.features.json.*
//import io.ktor.client.features.json.serializer.*
//import io.ktor.client.features.logging.*
//
//interface KtorApiService {
//
//    suspend fun getRepos(): List<Repository>
//
//    companion object {
//        fun create() : KtorApiService {
//            return KtorApiServiceIml(
//                client = HttpClient(Android) {
//                    install(Logging) {
//                        level = LogLevel.ALL
//                    }
//                    install(JsonFeature) {
//                        serializer = KotlinxSerializer()
//                    }
//                }
//            )
//        }
//    }
//}