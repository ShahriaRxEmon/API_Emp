package com.storedata.Info_ofEmp.controller;


import com.storedata.Info_ofEmp.model.EmpInfo;
import com.storedata.Info_ofEmp.service.EmpInfoService;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "*") // Optional: enable CORS if frontend is hosted elsewhere
public class EmpInfoController {

    @Autowired
    private EmpInfoService service;

    // POST /employees
    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody EmpInfo employee) {
        service.saveEmployee(employee);
        return ResponseEntity.ok("✅ New Employee added successfully!");
    }

    // PUT /employees/{id}
    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable int id, @RequestBody EmpInfo updatedEmployee) {
        EmpInfo result = service.updateEmployee(id, updatedEmployee);
        if (result != null) {
            return ResponseEntity.ok("✅ Employee Information UPDATED successfully!");
        } else {
            return ResponseEntity.status(404).body("❌ Employee not found for update.");
        }
    }

    // DELETE /employees/{id}
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) {
        return service.deleteEmployee(id);
    }

    // GET /employees
    @GetMapping
    public List<EmpInfo> getAllEmployees() {
        return service.getAllEmployees();
    }

    // GET /For single employee
    @GetMapping("/{id}")
    public EmpInfo getEmployeeById(@PathVariable int id) {
        return service.getEmployeeById(id);
    }

    // GET /employees/check?email=...&employeeId=...
    @GetMapping("/check")
    public String checkExistence(@RequestParam(required = false) String email,
                                 @RequestParam(required = false) String employeeId) {
        boolean emailExists = false;
        boolean employeeIdExists = false;

        if (email != null && !email.isEmpty()) {
            emailExists = service.existsByEmail(email);
        }

        if (employeeId != null && !employeeId.isEmpty()) {
            employeeIdExists = service.existsByEmployeeId(employeeId);
        }

        if (emailExists || employeeIdExists) {
            return "Email or Employee ID already exists";
        } else {
            return "Email and Employee ID is not available";
        }
    }

    @ExceptionHandler(StaleObjectStateException.class)
    public ResponseEntity<String> handleOptimisticLockException(StaleObjectStateException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("The record you are trying to update has already been modified by another user.");
    }
}
//emon