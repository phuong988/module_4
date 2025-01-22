package com.example.football_player_management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT")
    private int id;


    @NotBlank(message = "Tên không được để trống.")
    @Size(min = 5, max = 100, message = "Tên phải từ 5 đến 100 ký tự.")
    @Pattern(regexp = "^[a-zA-ZÀ-ỹ\\s]+$", message = "Tên không được chứa ký tự đặc biệt.")
    @Column(name = "ho_ten", columnDefinition = "VARCHAR(100)", nullable = false)
    private String fullName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Ngày sinh không được để trống")
    @PastOrPresent(message = "Ngày sinh không được lớn hơn ngày hiện tại")
    @Column(name="ngay_sinh", nullable = false)
    private LocalDate dob;

    @NotNull(message = "Kinh nghiệm không được để trống")
    @Column(name="kinhNghiem",columnDefinition = "VARCHAR(255)" , nullable = false)
    private String experience;

    @NotNull(message = "vị trí không dược để trống")
    @Column(name="vi_tri",columnDefinition = "VARCHAR(100)" , nullable = false)
    private String position;

    @Column(name="anh_dai_dien")
    private String image;

    @Column(name = "trang_thai", columnDefinition = "VARCHAR(20) DEFAULT 'Dự bị'")
    private String status = "Dự bị";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    @NotNull(message = "Đội tuyển không được để trống")
    private Team team;


    @Transient
    private String ageErrorMessage;
    @AssertTrue(message = "Tuổi phải từ 16 đến 100.")
    public boolean isAgeValid() {
        if (dob == null) {
            return false;
        }
        int age = Period.between(dob, LocalDate.now()).getYears();
        if (age < 16 || age > 100) {
            ageErrorMessage = "Tuổi phải từ 16 đến 100.";
            return false;
        }
        ageErrorMessage = null;
        return age >= 16 && age <= 100;
    }
}
