package com.example.demo.Controller;

import com.example.demo.Model.Rating;
import com.example.demo.Service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/category")
    public Page<Rating> getRatingsByCategoryWithPagination(
            @RequestParam String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ratingService.getRatingsByCategoryWithPagination(category, page, size);
    }

    @GetMapping("/score")
    public Page<Rating> getRatingsByScoreRangeWithPagination(
            @RequestParam Integer min,
            @RequestParam Integer max,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ratingService.getRatingsByScoreRangeWithPagination(min, max, page, size);
    }

    @GetMapping("/product")
    public Page<Rating> getRatingsByProductIdWithPagination(
            @RequestParam Long productId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ratingService.getRatingsByProductIdWithPagination(productId, page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rating> getRating(@PathVariable Long id) {
        Rating rating = ratingService.getRating(id);
        return rating != null ? ResponseEntity.ok(rating) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rating> updateRating(@PathVariable Long id, @RequestBody Rating rating) {
        Rating updatedRating = ratingService.updateRating(id, rating);
        return updatedRating != null ? ResponseEntity.ok(updatedRating) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRating(@PathVariable Long id) {
        boolean isDeleted = ratingService.deleteRating(id);

        if (isDeleted) {
            return ResponseEntity.ok("Rating deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("Rating not found.");
        }
    }

    @GetMapping
    public ResponseEntity<Page<Rating>> getAllRatings(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "min", required = false) Double minRating,
            @RequestParam(value = "max", required = false) Double maxRating,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<Rating> ratings = ratingService.getAllRatings(category, minRating, maxRating, page, size);
        return ResponseEntity.ok(ratings);
    }

    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        Rating createdRating = ratingService.createRating(rating);
        return new ResponseEntity<>(createdRating, HttpStatus.CREATED);
    }
}
