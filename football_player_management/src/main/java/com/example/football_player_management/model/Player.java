package com.example.football_player_management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="hoTen",columnDefinition = "VARCHAR(100)" , nullable = false)
    private String fullName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Ngày sinh không được để trống")
    @PastOrPresent(message = "Ngày sinh không được lớn hơn này hiện tại")
    @Column(name="ngaySinh", nullable = false)
    private LocalDate dob;

    @NotNull(message = "Kinh nghiệm không được để trống")
    @Column(name="kinhNghiem",columnDefinition = "VARCHAR(255)" , nullable = false)
    private String experience;

    @NotNull(message = "vị trí không dược để trống")
    @Column(name="viTri",columnDefinition = "VARCHAR(100)" , nullable = false)
    private String position;

    @Column(name="anhDaiDien")
    private String image;
}
