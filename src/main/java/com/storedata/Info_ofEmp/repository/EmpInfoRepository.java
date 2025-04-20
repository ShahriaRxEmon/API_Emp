package com.storedata.Info_ofEmp.repository;


import com.storedata.Info_ofEmp.model.EmpInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpInfoRepository extends JpaRepository<EmpInfo, Integer> {

    // Check if email or employeeId already exists (for GET /employees/check)
    boolean existsByEmail(String email);
    boolean existsByEmployeeId(String employeeId);
}
//emon