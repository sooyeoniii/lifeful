package lifeful.review.data

import lifefule.review.domain.Review
import lifefule.review.domain.ReviewRepository
import lifefule.shared.BookId
import lifefule.shared.ReviewId
import lifefule.shared.ReviewerId
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
internal class ReviewRdbRepository(
    private val jpaRepository: ReviewJpaRepository,
) : ReviewRepository {
    @Transactional
    override fun addReview(review: Review) {
        jpaRepository.save(review)
    }

    override fun findBy(
        bookId: BookId,
        reviewerId: ReviewerId,
    ): Review? {
        return jpaRepository.findAll {
            select(entity(Review::class))
                .from(entity(Review::class))
                .whereAnd(
                    path(Review::bookId).equal(bookId),
                    path(Review::reviewerId).equal(reviewerId),
                )
        }.firstOrNull()
    }

    override fun findBy(
        bookId: BookId,
        reviewId: ReviewId,
    ): Review? {
        return jpaRepository.findAll {
            select(entity(Review::class))
                .from(entity(Review::class))
                .whereAnd(
                    path(Review::bookId).equal(bookId),
                    path(Review::id).equal(reviewId),
                )
        }.firstOrNull()
    }

    override fun findAll(bookId: BookId): List<Review> {
        return jpaRepository.findAll {
            select(entity(Review::class))
                .from(entity(Review::class))
                .whereAnd(
                    path(Review::bookId).equal(bookId),
                )
        }.filterNotNull()
    }
}
