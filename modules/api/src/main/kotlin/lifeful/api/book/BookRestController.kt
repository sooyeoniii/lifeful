package lifeful.api.book

import lifeful.book.application.query.FindBook
import lifefule.shared.BookId
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1")
@RestController
class BookRestController(
    private val findBook: FindBook,
) : BookApi {
    @GetMapping("/books")
    override fun getBooks(): List<BookResponse> {
        val books = findBook.all()
        return books.map { BookResponse.Companion.from(it) }
    }

    @PostMapping("/books")
    override fun addBook() {
        TODO("책 추가 구현")
//        val books =
    }

    @GetMapping("/books/{bookId}")
    override fun getBook(
        @PathVariable bookId: BookId,
    ): BookResponse {
        val book = findBook.findById(bookId = bookId)
        return BookResponse.from(book)
    }
}
