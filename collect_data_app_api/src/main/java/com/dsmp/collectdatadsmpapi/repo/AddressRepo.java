package com.dsmp.collectdatadsmpapi.repo;

import com.dsmp.collectdatadsmpapi.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@EnableJpaRepositories
public interface AddressRepo extends JpaRepository<Address,String> {
    @Query(value = "SELECT address_id FROM address WHERE address_id like ?% ORDER BY CAST(SUBSTRING(address_id,?) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLastId(String s, int i);
    @Query(value = "SELECT * FROM address WHERE address.city_id=?1", nativeQuery = true)
    List<Address> findByCityId(String cityId);
}
