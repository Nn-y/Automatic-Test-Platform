package com.testplatform.platformbackend.controller.IfTest;

import com.testplatform.platformbackend.Jmeter.RunTest;
import com.testplatform.platformbackend.entity.MyRequest;
import com.testplatform.platformbackend.entity.RequestParams;
import com.testplatform.platformbackend.service.IfTest.JmeterService;
import com.testplatform.platformbackend.service.IfTest.RequestParamsService;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Controller
public class JmeterController {
    @Autowired
    JmeterService jmeterService;
    @Autowired
    RequestParamsService requestParamsService;

    @ResponseBody
    @RequestMapping("/getrequest")
    @CrossOrigin
    public void getRequest(@RequestParam("id") int id) throws IOException, DocumentException {
        File log = new File("log.xml");
        FileWriter fileWriter = new FileWriter(log);
        fileWriter.write("");// 清空
        fileWriter.flush();
        fileWriter.close();
        MyRequest myRequest = jmeterService.getRequest(id);
        List<RequestParams> params = requestParamsService.getParams(id);
        RunTest runTest = new RunTest();
        runTest.test(myRequest,params);
    }

    @ResponseBody
    @RequestMapping("/getresponse")
    @CrossOrigin
    public List<String> getResponse(@RequestParam("content") String content) throws DocumentException, IOException {
        RunTest runTest = new RunTest();
//        System.out.println(runTest.getResponse(content));
        return runTest.getResponse(content);
    }

    @ResponseBody
    @RequestMapping("/getresponse/getbrief")
    @CrossOrigin
    public List<String> getBrief() throws FileNotFoundException, DocumentException {
        RunTest runTest = new RunTest();
        return runTest.getBrief();
    }


}
