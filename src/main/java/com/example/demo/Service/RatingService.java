package com.example.demo.Service;

import com.example.demo.Model.Rating;
import com.example.demo.Repository.RatingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public Page<Rating> getRatingsByCategoryWithPagination(String category, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ratingRepository.findByCategory(category, pageable);
    }

    public Page<Rating> getRatingsByScoreRangeWithPagination(Integer minScore, Integer maxScore, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ratingRepository.findByScoreBetween(minScore, maxScore, pageable);
    }

    public Page<Rating> getRatingsByProductIdWithPagination(Long productId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ratingRepository.findByProductId(productId, pageable);
    }

    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public Rating updateRating(Long id, Rating updatedRating) {
        if (ratingRepository.existsById(id)) {
            updatedRating.setId(id);
            return ratingRepository.save(updatedRating);
        }
        return null;
    }

    public boolean deleteRating(Long id) {
        if (ratingRepository.existsById(id)) {
            ratingRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Rating getRating(Long id) {
        return ratingRepository.findById(id).orElse(null);
    }

    public Page<Rating> getAllRatings(String category, Double minRating, Double maxRating, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (category != null && minRating != null && maxRating != null) {
            return ratingRepository.findByCategoryAndRatingBetween(category, minRating, maxRating, pageable);
        } else if (category != null) {
            return ratingRepository.findByCategory(category, pageable);
        } else if (minRating != null && maxRating != null) {
            return ratingRepository.findByRatingBetween(minRating, maxRating, pageable);
        }
        return ratingRepository.findAll(pageable);
    }
}
