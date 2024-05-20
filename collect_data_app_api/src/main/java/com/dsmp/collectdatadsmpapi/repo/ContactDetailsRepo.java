package com.dsmp.collectdatadsmpapi.repo;



import com.dsmp.collectdatadsmpapi.entity.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ContactDetailsRepo extends JpaRepository<ContactDetails,String> {
    @Query(value = "SELECT contact_id FROM contact_details WHERE contact_id like ?% ORDER BY CAST(SUBSTRING(contact_id,?) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLastId(String s, int i);
}
