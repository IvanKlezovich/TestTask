package com.example.testtask.repository;

import com.example.testtask.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  @Query("""
      select u from User u
                      join PhoneData pd on pd.user.id = u.id
                      join EmailData ed on ed.user.id = u.id
                      join Account a on a.id = u.id
      where (coalesce(:phone, '') = '' or pd.phone = :phone) and
            (coalesce(:email, '') = '' or ed.email = :email) and
            (coalesce(:name, '') = '' or lower(u.name) like lower(concat('%', :name, '%'))) and
            (coalesce(:dateOfBirth, null) is null or u.birthday > :dateOfBirth)
      """)
  Page<User> findAllWithFilters(@Param("name") String name,
      @Param("email") String email,
      @Param("phone") String phone,
      @Param("dateOfBirth") LocalDate dateOfBirth,
      Pageable pageable);

  User getUserById(Long id);

  @Query("""
          select u from User u
                  join PhoneData pd
          where pd.phone = :phone
          """)
  Optional<User> findByPhone(String phone);

  @Query("""
          select u from User u
                  join EmailData ed
          where ed.email = :email
          """)
  Optional<User> findByEmail(String email);
}
