package com.storedata.Info_ofEmp.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "empinfoparam", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "employeeId"),
        @UniqueConstraint(columnNames = "contactNumber")
})

public class EmpInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique = true)
    private String employeeId;

    private String designation;

    private String address;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String contactNumber;

    private String deviceId;

    private String salary;

    private String overtimeRate;

    private LocalDate startDate;

    private LocalTime startTime;

    @Lob
    private String imageFile;  // Base64-encoded image string

    @Lob
    @Column(name = "embedding")
    private String embeddingStr; // Stored in DB

    @Transient
    private List<Double> embedding; // Used in app

    // Custom getter/setter for embedding
    public List<Double> getEmbedding() {
        if (embedding == null && embeddingStr != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                embedding = mapper.readValue(embeddingStr, new TypeReference<List<Double>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return embedding;
    }

    public void setEmbedding(List<Double> embedding) {
        this.embedding = embedding;
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.embeddingStr = mapper.writeValueAsString(embedding);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Version
    private Long version;

    // Getters and Setters for all fields

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public String getDeviceId() { return deviceId; }
    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }

    public String getSalary() { return salary; }
    public void setSalary(String salary) { this.salary = salary; }

    public String getOvertimeRate() { return overtimeRate; }
    public void setOvertimeRate(String overtimeRate) { this.overtimeRate = overtimeRate; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public String getImageFile() { return imageFile; }
    public void setImageFile(String imageFile) { this.imageFile = imageFile; }

    public String getEmbeddingStr() { return embeddingStr; }
    public void setEmbeddingStr(String embeddingStr) { this.embeddingStr = embeddingStr; }
}
//emon