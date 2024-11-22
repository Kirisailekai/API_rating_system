package com.example.demo.Repository;

import com.example.demo.Model.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    Page<Rating> findByCategory(String category, Pageable pageable);

    Page<Rating> findByScoreBetween(Integer minScore, Integer maxScore, Pageable pageable);

    Page<Rating> findByProductId(Long productId, Pageable pageable);

    Page<Rating> findByRatingBetween(Double minRating, Double maxRating, Pageable pageable);

    Page<Rating> findByCategoryAndRatingBetween(String category, Double minRating, Double maxRating, Pageable pageable);
}
