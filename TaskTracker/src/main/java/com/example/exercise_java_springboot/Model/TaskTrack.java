package com.example.exercise_java_springboot.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskTrack {
private String id;
private String title;
private String description;
private String status;
}
