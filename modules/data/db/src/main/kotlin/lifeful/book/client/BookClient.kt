package lifeful.book.client

import lifefule.book.domain.Book
import lifefule.book.domain.BookClient
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "book-client",
    url = "\${external.book.api.url:http://localhost:8081}",
    configuration = [BookClientConfig::class],
)
interface BookFeignClient {
    /**
     * 검색 조건으로 책 데이터를 외부에서 가져온다.
     * @param bookId 책 ID
     * @return 책 응답 DTO
     */
    @GetMapping("/books/{bookId}")
    fun getBook(
        @PathVariable bookId: String,
    ): BookResponse

    @GetMapping("/books/search")
    fun searchBooks(
        @RequestParam query: String,
    ): List<BookResponse>

    @GetMapping("/books/isbn/{isbn}")
    fun getBookByIsbn(
        @PathVariable isbn: String,
    ): BookResponse
}

@Component
class BookClientImpl(
    private val bookFeignClient: BookFeignClient,
) : BookClient {
    override fun getBook(bookId: String): Book? {
        return try {
            val response = bookFeignClient.getBook(bookId)
            response.toDomain()
        } catch (e: Exception) {
            null
        }
    }

    override fun searchBooks(query: String): List<Book> {
        return try {
            val responses = bookFeignClient.searchBooks(query)
            responses.map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override fun getBookByIsbn(isbn: String): Book? {
        return try {
            val response = bookFeignClient.getBookByIsbn(isbn)
            response.toDomain()
        } catch (e: Exception) {
            null
        }
    }
}

data class BookResponse(
    val id: String,
    val isbn: String,
    val title: String,
    val description: String,
    val author: String,
    val publisher: String,
) {
    fun toDomain(): Book {
        return Book(
            isbn = isbn,
            title = title,
            description = description,
            author = author,
            publisher = publisher,
            id = lifefule.shared.BookId(id.toLong()),
        )
    }
}
