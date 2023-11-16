package com.example.exercise_java_springboot.Controller;

import com.example.exercise_java_springboot.ApiResponse.ApiResponse;
import com.example.exercise_java_springboot.Model.TaskTrack;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/task")
public class TaskTrackController {
public ArrayList<TaskTrack> tasks = new ArrayList<TaskTrack>();

@PostMapping("/post")
public ApiResponse createTask(@RequestBody TaskTrack task){
    tasks.add(task);
return new ApiResponse("Task created",200);
}

@GetMapping("/get")
public ArrayList<TaskTrack> getTasks(){
    return tasks;
}

@PutMapping("/update/{index}")
public ApiResponse updateTask(@PathVariable int index, @RequestBody TaskTrack task){
    tasks.set(index, task);
    return  new ApiResponse("Task updated",200);
}

@DeleteMapping("/delete/{index}")
public ApiResponse deleteTask(@PathVariable int index){
    tasks.remove(index);
    return  new ApiResponse("Task Removed",200);
}

@GetMapping("/change/{index}")
public ApiResponse changeStat(@PathVariable int index){

    if (tasks.get(index).getStatus().equalsIgnoreCase("not done")){
        tasks.get(index).setStatus("done");
        return new ApiResponse("Task status is set to done",200);
    }else if (tasks.get(index).getStatus().equalsIgnoreCase("done")) {
        tasks.get(index).setStatus("not done");
        return new ApiResponse("Task status is set to not done",200);
    }
    return new ApiResponse("Invalid status, cannot change status if it's not (done) or (not done)",400);
}

@GetMapping("/search/{title}")
public ArrayList<TaskTrack> searchTask(@PathVariable String title){
    ArrayList<TaskTrack> temp = new ArrayList<>();
    for (TaskTrack v: tasks) {
        if (v.getTitle().equalsIgnoreCase(title)){
            temp.add(v);
        }
    }
    return temp;
}



}
