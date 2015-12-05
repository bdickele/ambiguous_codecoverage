package org.bdickele.ambiguous.repository;

import org.bdickele.ambiguous.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by bdickele
 */
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    List<Department> findAllByOrderByNameAsc();

    Department findByCode(String code);
}
