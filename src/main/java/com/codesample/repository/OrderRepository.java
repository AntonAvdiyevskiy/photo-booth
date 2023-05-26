package com.codesample.repository;

import com.codesample.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT IFNULL(SUM(PRICE), 0) FROM ORDERS WHERE MONTH(DATE) = :month AND YEAR(DATE) = :year",
    nativeQuery = true)
    double calculateRevenueByMonth(@Param("month") int month, @Param("year") int year);
}
