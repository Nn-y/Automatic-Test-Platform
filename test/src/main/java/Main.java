import org.apache.cxf.bus.managers.HeaderManagerImpl;
import org.apache.cxf.headers.HeaderManager;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.engine.JMeterEngine;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.protocol.http.util.HTTPArgument;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.testelement.property.BooleanProperty;
import org.apache.jmeter.testelement.property.CollectionProperty;
import org.apache.jmeter.testelement.property.StringProperty;
import org.apache.jmeter.testelement.property.TestElementProperty;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.apache.jorphan.collections.ListedHashTree;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class Main {

    static{

        {
          //  JMeterUtils.setJMeterHome(new File("C:\\Users\\86182\\Desktop\\apache-jmeter-5.4.3").getAbsolutePath());

            JMeterUtils.loadJMeterProperties(new File("C:\\Users\\86182\\Desktop\\apache-jmeter-5.4.3\\bin/jmeter.properties").getAbsolutePath());

            JMeterUtils.setProperty("saveservice_properties", "C:\\Users\\86182\\Desktop\\apache-jmeter-5.4.3\\bin/saveservice.properties");

            JMeterUtils.setProperty("user_properties", "C:\\Users\\86182\\Desktop\\apache-jmeter-5.4.3\\bin/user.properties");

            JMeterUtils.setProperty("upgrade_properties", "C:\\Users\\86182\\Desktop\\apache-jmeter-5.4.3\\bin/upgrade.properties");

            JMeterUtils.setProperty("system_properties", "C:\\Users\\86182\\Desktop\\apache-jmeter-5.4.3\\bin/system.properties");

            JMeterUtils.setProperty("proxy.cert.directory", new File("").getAbsolutePath());

            JMeterUtils.setLocale(Locale.SIMPLIFIED_CHINESE);

            JMeterUtils.initLocale();
        }

    }


    public static void main(String[] args) throws IOException {
        //TestPlan
        TestPlan testPlan = new TestPlan("Test Plan");
        testPlan.setFunctionalMode(false);
        testPlan.setSerialized(false);
        testPlan.setTearDownOnShutdown(true);
        testPlan.setProperty(TestElement.TEST_CLASS, TestPlan.class.getName());
        testPlan.setProperty(TestElement.GUI_CLASS,"TestPlanGui");
        testPlan.setProperty(new BooleanProperty(TestElement.ENABLED, true));
        testPlan.setProperty(new StringProperty("TestPlan.comments", ""));
        testPlan.setProperty(new StringProperty("TestPlan.user_define_classpath", ""));
        Arguments arguments = new Arguments();
        testPlan.setProperty(new TestElementProperty("TestPlan.user_defined_variables",arguments));


        //ThreadGroup
        ThreadGroup threadGroup = new ThreadGroup();
        threadGroup.setNumThreads(1);
        threadGroup.setRampUp(1);
        threadGroup.setDelay(0);
        threadGroup.setDuration(0);
        threadGroup.setProperty(new StringProperty(ThreadGroup.ON_SAMPLE_ERROR, "continue"));
        threadGroup.setScheduler(false);
        threadGroup.setName("Group1");
        threadGroup.setProperty(TestElement.TEST_CLASS, ThreadGroup.class.getName());
        threadGroup.setProperty(TestElement.GUI_CLASS,"ThreadGroup");
        threadGroup.setProperty(new BooleanProperty(TestElement.ENABLED, true));


        HTTPSamplerProxy httpSamplerProxy = new HTTPSamplerProxy();
        Arguments HTTPsamplerArguments = new Arguments();
        HTTPArgument httpArgument = new HTTPArgument();
        httpArgument.setProperty(new BooleanProperty("HTTPArgument.always_encode",false));
     //   httpArgument.setProperty(new StringProperty("Argument.value", request));
        httpArgument.setProperty(new StringProperty("Argument.metadata","="));

        String url = "http://www.baidu.com";
        String port = "80";

        ArrayList<TestElementProperty> list1 = new ArrayList();
        list1.add(new TestElementProperty("",httpArgument));
        HTTPsamplerArguments.setProperty(new CollectionProperty("Arguments.arguments",list1));
       // httpSamplerProxy.setProperty(new CollectionProperty("HTTPsampler.Arguments",HTTPsamplerArguments));
        httpSamplerProxy.setProperty(new StringProperty("HTTPSampler.domain", url));
        httpSamplerProxy.setProperty(new StringProperty("HTTPSampler.port", port));
        httpSamplerProxy.setProperty(new StringProperty("HTTPSampler.protocol", "http"));
     //   httpSamplerProxy.setProperty(new StringProperty("HTTPSampler.path", api));
        httpSamplerProxy.setProperty(new StringProperty("HTTPSampler.method", "GET"));
        httpSamplerProxy.setProperty(new StringProperty("HTTPSampler.contentEncoding", ""));
        httpSamplerProxy.setProperty(new BooleanProperty("HTTPSampler.follow_redirects", true));
        httpSamplerProxy.setProperty(new BooleanProperty("HTTPSampler.postBodyRaw", true));
        httpSamplerProxy.setProperty(new BooleanProperty("HTTPSampler.auto_redirects", false));
        httpSamplerProxy.setProperty(new BooleanProperty("HTTPSampler.use_keepalive", true));
        httpSamplerProxy.setProperty(new BooleanProperty("HTTPSampler.DO_MULTIPART_POST", false));
        httpSamplerProxy.setProperty(new StringProperty("TestElement.gui_class", "org.apache.jmeter.protocol.http.control.gui.HttpTestSampleGui"));
        httpSamplerProxy.setProperty(new StringProperty("TestElement.test_class", "org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy"));
        httpSamplerProxy.setProperty(new StringProperty("TestElement.name", "HTTP Request"));
        httpSamplerProxy.setProperty(new StringProperty("TestElement.enabled", "true"));
        httpSamplerProxy.setProperty(new BooleanProperty("HTTPSampler.postBodyRaw", true));
        httpSamplerProxy.setProperty(new StringProperty("HTTPSampler.embedded_url_re", ""));
        httpSamplerProxy.setProperty(new StringProperty("HTTPSampler.connect_timeout", ""));
        httpSamplerProxy.setProperty(new StringProperty("HTTPSampler.response_timeout", ""));


        ResultCollector resultCollector = new ResultCollector();
        HeaderManager headerManager = new HeaderManagerImpl();
        ListedHashTree hashTreeResultCollectorAndHeaderManager = new ListedHashTree();
        hashTreeResultCollectorAndHeaderManager.add(resultCollector);
        hashTreeResultCollectorAndHeaderManager.add(headerManager);
        ListedHashTree hashTreeHTTPSamplerProxy = new ListedHashTree();
        hashTreeHTTPSamplerProxy.add(httpSamplerProxy,hashTreeResultCollectorAndHeaderManager);

        ListedHashTree hashTreeThreadGroup = new ListedHashTree();
        hashTreeThreadGroup.add(threadGroup,hashTreeHTTPSamplerProxy);

        ListedHashTree hashTreeTestPlan = new ListedHashTree();
        hashTreeTestPlan.add(testPlan,hashTreeThreadGroup);
        SaveService.saveTree(hashTreeTestPlan, new FileOutputStream("C:\\Users\\86182\\Desktop\\test\\test.jmx"));



        test();

     //   testCmd();
    }


    @Test
    public static void test() throws IOException {

        File file = new File("C:\\Users\\86182\\Desktop\\test\\Request.jmx");
        HashTree testPlanTree = SaveService.loadTree(file);

        Summariser summer = null;
        String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");//$NON-NLS-1$
        if (summariserName.length() > 0) {
            summer = new Summariser(summariserName);
        }

        String logFile = "C:\\Users\\86182\\Desktop\\test.jtl";
        ResultCollector logger = new ResultCollector(summer);
        logger.setFilename(logFile);
        testPlanTree.add(testPlanTree.getArray()[0], logger);

        JMeterEngine engine = new StandardJMeterEngine();
        engine.configure(testPlanTree);
        ((StandardJMeterEngine) engine).run();

    }

    @Test

    public static void testCmd() throws  IOException {

        String command = "C:\\Users\\86182\\Desktop\\apache-jmeter-5.4.3\\bin\\jmeter -n -t C:\\Users\\86182\\Desktop\\test\\Request.jmx" +
                "  -l C:\\Users\\86182\\Desktop\\test.jtl";

        Runtime.getRuntime().exec("cmd.exe /C start " + command);

    }


}
