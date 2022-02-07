package com.chunfeng.controller;

import com.chunfeng.dao.pojo.Class;
import com.chunfeng.dao.pojo.Json;
import com.chunfeng.service.inter.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//解决跨域问题
@CrossOrigin
@RestController
@RequestMapping("/class")
public class ClassController {
    
    @Autowired
    private ClassService classService;

    /**
     * 查询所有班级，用于在输入框显示提示
     * @return Json
     */
    @GetMapping
    public Json selectAllClass(){
        boolean status=true;
        List<Class> classList=null;
        try {
            classList = classService.list();
        } catch (Exception e) {
            e.printStackTrace();
            status=false;
        }
        return new Json(status,classList);
    }
}
