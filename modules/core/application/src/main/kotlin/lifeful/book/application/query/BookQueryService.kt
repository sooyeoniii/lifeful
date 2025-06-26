package lifeful.book.application.query

import lifefule.book.domain.Book
import lifefule.book.domain.BookClient
import lifefule.book.domain.BookNotFoundException
import lifefule.book.domain.BookRepository
import lifefule.shared.BookId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 책 검색 유즈케이스의 구현체이다.
 *
 * @author hd15807@gmail.com
 */
@Transactional(readOnly = true)
@Service
class BookQueryService(
        private val bookRepository: BookRepository,
        private val bookClient: BookClient,
) : FindBook {
    override fun all(): List<Book> {
        return bookRepository.findAll()
    }

    override fun findById(bookId: BookId): Book {
        return bookRepository.findById(bookId)?: throw BookNotFoundException("없더")
    }

}
