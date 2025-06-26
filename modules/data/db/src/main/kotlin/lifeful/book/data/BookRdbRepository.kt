package lifeful.book.data

import lifefule.book.domain.Book
import lifefule.book.domain.BookRepository
import lifefule.shared.BookId
import org.springframework.stereotype.Repository

@Repository
internal class BookRdbRepository(
        private val bookJpaRepository: BookJpaRepository,
) : BookRepository {
    override fun findAll(): List<Book> {
        return bookJpaRepository.findAll()
    }

    override fun findById(bookId: BookId): Book? {
        return bookJpaRepository.findAll {
            select(entity(Book::class))
                    .from(entity(Book::class))
                    .whereAnd(
                            path(Book::id).equal(bookId),
                    )
        }.firstOrNull()
    }
}
