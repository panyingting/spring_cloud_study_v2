package com.common.server.istudy.controller;

import com.common.server.istudy.controller.model.Student;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @RequestMapping(method = RequestMethod.GET)
    public Student get(){
        return new Student();
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    public String add( Student student){
        System.out.println(student);
        return "ok";
    }


    @RequestMapping(method = RequestMethod.PATCH)
    public Student update(@RequestParam("name") String name){
        Student student = new Student();
        student.setName(name);
        return student;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String delete(String name){

        System.out.println("delete:"+name);
        return "delete";
    }

}
