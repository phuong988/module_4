package com.example.validate_song_information.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Tên bài hát không được để trống")
    @Size(max = 800, message = "Tên bài hát không được vượt quá 800 ký tự")
    @Pattern(regexp = "^[^@;,.=\\-+]+$", message = "Tên bài hát không được chứa các ký tự đặc biệt")
    private String name;

    @NotBlank(message = "Tên nghệ sĩ không được để trống")
    @Size(max = 300, message = "Tên nghệ sĩ không được vượt quá 300 ký tự")
    @Pattern(regexp = "^[^@;,.=\\-+]+$", message = "Tên nghệ sĩ không được chứa các ký tự đặc biệt")
    private String artist;

    @NotBlank(message = "Thể loại nhạc không được để trống")
    @Size(max = 1000, message = "Thể loại nhạc không được vượt quá 1000 ký tự")
    @Pattern(regexp = "^[^@;.=\\-+]+$", message = "Thể loại nhạc chỉ được chứa dấu phẩy ',' là ký tự đặc biệt")
    private String genre;
}
