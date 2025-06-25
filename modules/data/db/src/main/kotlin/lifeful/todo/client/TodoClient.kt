package lifeful.todo.client

import lifefule.todo.domain.TodoCheckClient
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
        name = "todoClient",
        url = "\${external.todo.api.url}"
//        configuration = [BookClientConfig::class]
)
interface TodoClient : TodoCheckClient {

    @GetMapping()
    override fun isProfane(@RequestParam("text") text: String): String

}