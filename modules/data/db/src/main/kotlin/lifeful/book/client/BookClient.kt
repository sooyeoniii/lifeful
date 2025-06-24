package lifeful.book.client

import lifefule.book.domain.Book
import lifefule.book.domain.BookClient
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(
    name = "book-client",
    url = "\${external.book.api.url:http://localhost:8081}",
    configuration = [BookClientConfig::class]
)
interface BookFeignClient {
    /**
     * 검색 조건으로 책 데이터를 외부에서 가져온다.
     * @param bookId 책 ID
     * @return 책 응답 DTO
     */
    @GetMapping("/books/{bookId}")
    fun getBook(@PathVariable bookId: String): BookResponse
}

@Component
class BookClientImpl(
    private val bookFeignClient: BookFeignClient
) : BookClient {

    override fun getBook(isbn: String?, title: String?): Book? {
        TODO("Not yet implemented")
    }
}

data class BookResponse(
    val id: String,
    val isbn: String,
    val title: String,
    val description: String,
    val author: String,
    val publisher: String
) {
    fun toDomain(): Book {
        return Book(
            isbn = isbn,
            title = title,
            description = description,
            author = author,
            publisher = publisher,
            id = lifefule.shared.BookId(id.toLong())
        )
    }
}


