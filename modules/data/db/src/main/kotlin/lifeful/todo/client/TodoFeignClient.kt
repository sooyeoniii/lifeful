package lifeful.todo.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
        name = "todoClient",
        url = "\${external.todo.api.url}",
//        configuration = [BookClientConfig::class]
)
interface TodoFeignClient {
    @GetMapping()
    fun isProfane(
            @RequestParam("text") text: String,
    ): String
}
