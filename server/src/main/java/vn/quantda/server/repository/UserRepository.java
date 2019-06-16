package vn.quantda.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.quantda.server.entity.UserDetail;

public interface UserRepository extends JpaRepository<UserDetail, Long> {
	UserDetail findByEmail(String email);

}
