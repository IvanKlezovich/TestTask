package com.example.testtask.repository;

import com.example.testtask.entity.User;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@CacheConfig(cacheNames = "users")
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

  @Cacheable(key = "#id", unless = "#result == null")
  User getUserById(Long id);

  @Query("""
      select u from User u
              join EmailData ed on u.id = ed.user.id
      where ed.email = :email
      """)
  Optional<User> findByEmail(String email);
}
