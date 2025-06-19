package lifeful.book.data

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import lifefule.book.domain.Book
import lifefule.shared.BookId
import org.springframework.data.jpa.repository.JpaRepository

internal interface BookJpaRepository : JpaRepository<Book, BookId>, KotlinJdslJpqlExecutor
