package org.example.com.mbdev.springdata.repository;

import org.example.com.mbdev.springdata.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAccountByBill(Integer bill);
    Account findAccountByName(String name);
    @Query(nativeQuery = true, value = "select * from Account where name = :name and bill = :bill")
    Account findAccountByNameAndBill(@Param("name") String name, @Param("bill") Integer bill);
@Modifying
@Query(nativeQuery = true, value = "UPDATE Account set name = ?2 WHERE id = ?1")
    int setNameFor(Long id, String name);
}
