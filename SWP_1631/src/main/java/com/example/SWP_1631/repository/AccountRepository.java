package com.example.SWP_1631.repository;

import com.example.SWP_1631.entity.Account;
import com.example.SWP_1631.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer > {

    @Query("SELECT u FROM Account u WHERE u.email= :email AND u.password = :pass")
    public Account getAccByInfo(@Param("email") String email, @Param("pass") String pass);

    @Query("SELECT u FROM Account u WHERE u.email= :email")
    public Account getAccByEmail(@Param("email") String email);

    @Query("DELETE from Account a where a.accountId = :id")
    public Role deteteById(@Param("id") Integer id);

    @Query("SELECT r FROM Account u INNER JOIN  Role r ON u.role.roleID = r.roleID WHERE u.email= :email")
    public Role getRoleByEmail(@Param("email") String email);
}
