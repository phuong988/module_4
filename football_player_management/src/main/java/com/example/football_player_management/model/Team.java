package com.example.football_player_management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "team"  )
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="tenDoi",columnDefinition = "VARCHAR(100)" , nullable = false)
    private String name;

    @PastOrPresent(message = "Ngày thành lập không được lớn hơn ngày hiện tại")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "foundation_date", nullable = false)
    private LocalDate foundationDate;

    @Column(name = "home_stadium")
    private String homeStadium;

    @Column(name = "coach_name")
    private String coachName;
}
