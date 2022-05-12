package com.testplatform.platformbackend.controller.IfTest;

import com.testplatform.platformbackend.entity.IfListDetial;
import com.testplatform.platformbackend.service.IfTest.IfListDetialService;
import com.testplatform.platformbackend.service.IfTest.IfListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@Controller
public class IfListDetialController {
    @Autowired
    IfListDetialService service;
    @Autowired
    IfListService ifListService;

    @ResponseBody
    @RequestMapping("/iflistdetial")
    public IfListDetial getDetial(@RequestParam("id") int id){
        return service.getDetial(id);
    }

    @ResponseBody
    @PostMapping("/iflistdetial/save")
    public int save(@RequestBody IfListDetial detial){
        ifListService.updateInfo(detial);
        return service.save(detial);
    }
}
