package com.dsmp.collectdatadsmpapi.repo;


import com.dsmp.collectdatadsmpapi.entity.Business;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface BusinessRepo extends JpaRepository<Business,String> {
    @Query(value = "SELECT business_id FROM business WHERE business_id like ?% ORDER BY CAST(SUBSTRING(business_id,?) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLastId(String s, int i);

    @Query(value = "SELECT * FROM business where business_name LIKE %?%", nativeQuery = true)
    List<Business> getAllBusinessForProvidedDate( Pageable pageable,String searchText);

    @Query(value = "SELECT COUNT(*) FROM business where business_name LIKE %?%", nativeQuery = true)
    Long countAllBusinessForProvidedDate(String searchText);

    @Query(value = "SELECT *\n" +
            "FROM business AS b\n" +
            "         JOIN address AS a ON a.business_id = b.business_id\n" +
            "         JOIN city AS c ON a.city_id = c.city_id WHERE b.user_id=?2 AND DATE(b.register_date)=?1 AND c.city_id = ?3", nativeQuery = true)
    List<Business> findByRegDateUserIdAndBusinessId(String regDate, String userId, String cityId);


//    @Query(value = "SELECT * FROM business WHERE business.user_id=?3 AND DATE(business.register_date)=?1 AND business_id=?2", nativeQuery = true)
//    List<Business> getAllBusinessForProvidedDate(String regDate,String businessId, String userId, Pageable pageable);

}