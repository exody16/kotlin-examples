package org.example.async

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.AsyncTaskExecutor
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@EnableAsync
@Configuration
class AsyncConfig: AsyncConfigurer {

    @Bean("myTaskExecutor")
    @Suppress("MagicNumber")
    override fun getAsyncExecutor(): AsyncTaskExecutor {
        return ThreadPoolTaskExecutor().apply {
            corePoolSize = 1
            maxPoolSize = 5
            queueCapacity = 2
            threadNamePrefix = "MyExecutor-"
        }
    }

    @Bean
    fun mySingleThreadExecutor(): ExecutorService {
        return Executors.newSingleThreadExecutor();
    }

}
