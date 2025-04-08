package com.example.syamplecommerceapp.repo;

import com.example.syamplecommerceapp.entity.Massage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepo extends JpaRepository<Massage, Long> {
}
