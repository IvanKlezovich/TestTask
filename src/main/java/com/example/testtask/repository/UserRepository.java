package com.example.testtask.repository;

import com.example.testtask.dto.UserDto;
import com.example.testtask.entity.User;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

  @Query("""
      select u from User u
                  join PhoneData pd on u.id = pd.user.id
                  join EmailData ed on u.id = ed.user.id
      where (coalesce(:phone, '') = '' or pd.phone = :phone) and
            (coalesce(:email, '') = '' or ed.email = :email) and
            (coalesce(:name, '') = '' or lower(u.name) like lower(concat('%', :name, '%'))) and
            (coalesce(:dateOfBirth, null) is null or u.birthday > :dateOfBirth)
      """)
  Page<UserDto> findAllWithFilters(@Param("name") String name,
      @Param("email") String email,
      @Param("phone") String phone,
      @Param("dateOfBirth") LocalDate dateOfBirth,
      Pageable pageable);

  User getUserById(Long id);
}
