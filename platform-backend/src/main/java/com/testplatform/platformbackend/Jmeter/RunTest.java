package com.testplatform.platformbackend.Jmeter;

import com.testplatform.platformbackend.entity.MyRequest;
import com.testplatform.platformbackend.entity.RequestParams;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.jmeter.engine.JMeterEngine;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.property.TestElementProperty;
import org.apache.jorphan.collections.HashTree;
import org.apache.jmeter.util.JMeterUtils;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.*;

public class RunTest {
    static{

        {
            //  JMeterUtils.setJMeterHome(new File("C:\\Users\\86182\\Desktop\\apache-jmeter-5.4.3").getAbsolutePath());

//            JMeterUtils.loadJMeterProperties(new File("D:\\Jmeter\\apache-jmeter-5.4.3\\bin/jmeter.properties").getAbsolutePath());
//
//            JMeterUtils.setProperty("saveservice_properties", "D:\\Jmeter\\apache-jmeter-5.4.3\\bin/saveservice.properties");
//
//            JMeterUtils.setProperty("user_properties", "D:\\Jmeter\\apache-jmeter-5.4.3\\bin/user.properties");
//
//            JMeterUtils.setProperty("upgrade_properties", "D:\\Jmeter\\apache-jmeter-5.4.3\\bin/upgrade.properties");
//
//            JMeterUtils.setProperty("system_properties", "D:\\Jmeter\\apache-jmeter-5.4.3\\bin/system.properties");
            JMeterUtils.loadJMeterProperties(new File("src/main/resources/Jmeter/jmeter.properties").getAbsolutePath());

            JMeterUtils.setProperty("saveservice_properties", "src/main/resources/Jmeter/saveservice.properties");

            JMeterUtils.setProperty("user_properties", "src/main/resources/Jmeter/user.properties");

            JMeterUtils.setProperty("upgrade_properties", "src/main/resources/Jmeter/upgrade.properties");

            JMeterUtils.setProperty("system_properties", "src/main/resources/Jmeter/system.properties");

            JMeterUtils.setProperty("proxy.cert.directory", new File("").getAbsolutePath());

            JMeterUtils.setLocale(Locale.SIMPLIFIED_CHINESE);

            JMeterUtils.initLocale();
        }

    }

    public RunTest() throws FileNotFoundException {
    }

    public void test(MyRequest myRequest, List<RequestParams> params) throws IOException, DocumentException {
        String root =System.getProperty("user.dir");

        FileResourceLoader resourceLoader = new FileResourceLoader(root, "utf-8");
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        Template t = gt.getTemplate("http_request.jmx");

        t.binding("domain",myRequest.getDomain());
        t.binding("port",myRequest.getPort());
        t.binding("path",myRequest.getPath());
        t.binding("method",myRequest.getMethod());
        t.binding("logPath",root+File.separator+"log.xml");

        String jmxFilePath = "request.xml";
        OutputStream ops = new FileOutputStream(jmxFilePath);
        t.renderTo(ops);

        //???????????????
        SAXReader saxReader = new SAXReader();
        //??????document
        Document document = saxReader.read(jmxFilePath);


        Node collectionProp = document.selectSingleNode("/jmeterTestPlan/hashTree/hashTree/hashTree/HTTPSamplerProxy/elementProp/collectionProp");

        //???collectionProp???????????????
        HashMap<String,String> map = new HashMap<>();
        for(RequestParams param :params){
            map.put(param.getKey(),param.getValue());
        }
        String total = null;
        for(Map.Entry<String,String> entry:map.entrySet()){
//            System.out.println(entry.getKey());
            String son = "<elementProp name=\""+entry.getKey()+"\" elementType=\"HTTPArgument\">\n"+
                    "<boolProp name=\"HTTPArgument.always_encode\">false</boolProp>\n"+
                    "<stringProp name=\"Argument.value\">"+entry.getValue()+"</stringProp>\n"+
                    "<stringProp name=\"Argument.metadata\">=</stringProp>\n"+
                    "<boolProp name=\"HTTPArgument.use_equals\">true</boolProp>\n"+
                    "<stringProp name=\"Argument.name\">"+entry.getKey()+"</stringProp>\n"+
                    "</elementProp>\n";
            total = total+son;
            collectionProp.setText(total);

        }

        //??????xml
        String xml = StringEscapeUtils.unescapeXml(document.asXML());//??????xml???<>????????????
        // ?????????????????????????????????
        StringReader in = new StringReader(xml);
        Document doc = saxReader.read(in);
        OutputFormat format = OutputFormat.createPrettyPrint();//??????
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(jmxFilePath),format);
        xmlWriter.write(doc);
        xmlWriter.close();

        File file = new File(jmxFilePath);

        HashTree testPlanTree = SaveService.loadTree(file);


        JMeterEngine engine = new StandardJMeterEngine();
        engine.configure(testPlanTree);
        ((StandardJMeterEngine) engine).run();

    }
    //???????????????
    FileInputStream fis = new FileInputStream("log.xml");

    public List<String> getResponse(String content) throws DocumentException {
        //??????XML????????????
        SAXReader sr = new SAXReader();
        //????????????????????????
        Document doc = sr.read(fis);
        //???????????????????????????
        Element root = doc.getRootElement();
        //??????????????????
//        System.out.println(root.getName());
        //?????????????????????
        List<Element> es = root.elements();
        List<String> list = new ArrayList<>();
        for(int i=0; i<es.size(); i++) {
            Element element = es.get(i);
            String detial = element.elementText(content);
            list.add(detial);
        }
        return list;

    }

    public List<String> getBrief() throws DocumentException {
        SAXReader sr = new SAXReader();
        Document doc = sr.read(fis);
        Element root = doc.getRootElement();
        List<String> list = new ArrayList<>();
        Element httpSample = root.element("httpSample");
        String code = httpSample.attributeValue("rc");
        list.add(code);
        String time = httpSample.attributeValue("t");
        list.add(time);
        String size = httpSample.attributeValue("by");
        list.add(size);
        return list;
    }
}
