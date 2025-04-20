package com.storedata.Info_ofEmp.service;

import com.storedata.Info_ofEmp.model.EmpInfo;
import com.storedata.Info_ofEmp.repository.EmpInfoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpInfoService {

    @Autowired
    private EmpInfoRepository repository;

    // Add a single employee
    @Transactional
    public EmpInfo saveEmployee(EmpInfo employee) {
        return repository.save(employee);
    }

    // Update an existing employee
    @Transactional
    public EmpInfo updateEmployee(int id, EmpInfo updatedEmployee) {
        EmpInfo existing = repository.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(updatedEmployee.getName());
            existing.setEmployeeId(updatedEmployee.getEmployeeId());
            existing.setDesignation(updatedEmployee.getDesignation());
            existing.setAddress(updatedEmployee.getAddress());
            existing.setEmail(updatedEmployee.getEmail());
            existing.setContactNumber(updatedEmployee.getContactNumber());
            existing.setDeviceId(updatedEmployee.getDeviceId());
            existing.setSalary(updatedEmployee.getSalary());
            existing.setOvertimeRate(updatedEmployee.getOvertimeRate());
            existing.setStartDate(updatedEmployee.getStartDate());
            existing.setStartTime(updatedEmployee.getStartTime());
            existing.setEmbedding(updatedEmployee.getEmbedding());
            existing.setImageFile(updatedEmployee.getImageFile());
            return repository.save(existing);
        } else {
            return null;
        }
    }

    // Get all employees
    public List<EmpInfo> getAllEmployees() {
        return repository.findAll();
    }

    // Get by ID
    public EmpInfo getEmployeeById(int id) {
        return repository.findById(id).orElse(null);
    }

    // Delete employee
    @Transactional
    public String deleteEmployee(int id) {
        repository.deleteById(id);
        return "Employee with ID " + id + " deleted.";
    }

    // Check if employee with given email exists
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    // Check if employee with given employee ID exists
    public boolean existsByEmployeeId(String employeeId) {
        return repository.existsByEmployeeId(employeeId);
    }
}
//emon