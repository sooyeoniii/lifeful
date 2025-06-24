package lifeful.book.client

import feign.Logger
import feign.codec.Decoder
import feign.codec.Decoder.Default
import feign.codec.Encoder
import feign.form.FormEncoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BookClientConfig {
    
    @Bean
    fun feignLoggerLevel(): Logger.Level {
        return Logger.Level.FULL
    }
    
    @Bean
    fun feignEncoder(): Encoder {
        return FormEncoder()
    }
    
    @Bean
    fun feignDecoder(): Decoder {
        return Default()
    }
} 