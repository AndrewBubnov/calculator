import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class CalculatorServlet extends HttpServlet {
    private Map<String, Object> model = new HashMap<>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setDirectoryForTemplateLoading(new File("./src/main/java/templates"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);

        model.putIfAbsent("result", "0");
        model.putIfAbsent("valueOne", "");
        Template template = cfg.getTemplate("template.ftlh");
        Writer out = resp.getWriter();
        try {
            String number = req.getParameter("number");
            if (number != null) {
                if (number.equals("AC")) {
                    model.put("result", "0");
                    model.put("valueOne", "0");
                }

                if (number.equals("C")) {
                    model.put("result", Integer.parseInt((String) model.get("result")) / 10 + "");
                }

                if (number.equals("+")) {
                    model.put("action", "+");
                    secondString(model);
                }

                if (number.equals("-")) {
                    model.put("action", "-");
                    secondString(model);
                }

                if (number.equals("*")) {
                    model.put("action", "X");
                    secondString(model);
                }

                if (number.equals("/")) {
                    model.put("action", "/");
                    secondString(model);
                }

                if (isNumeric(number)) {
                    if (model.get("calculated")!= null && model.get("calculated").equals("yes")) {
                        model.put("result", "0");
                        model.put("calculated", "no");
                    }
                    int intNumber = Integer.parseInt(number) + Integer.parseInt((String) model.get("result")) * 10;
                    model.put("result", intNumber + "");
                }

                if (number.equals("=") && model.get("action") != null) {
                    String s;
                    if (model.get("action").equals("+")) {
                        s = (String) model.get("result");
                        model.put("result", Integer.parseInt((String) model.get("valueOne")) + Integer.parseInt(s) + "");
                        model.put("valueOne", "0");
                    }

                    if (model.get("action").equals("-")) {
                        s = (String) model.get("result");
                        model.put("result", Integer.parseInt((String) model.get("valueOne")) - Integer.parseInt(s) + "");
                        model.put("valueOne", "0");

                    }

                    if (model.get("action").equals("X")) {
                        s = (String) model.get("result");
                        model.put("result", Integer.parseInt((String) model.get("valueOne")) * Integer.parseInt(s) + "");
                        model.put("valueOne", "0");

                    }

                    if (model.get("action").equals("/")) {
                        s = (String) model.get("result");
                        model.put("result", (Integer.parseInt((String) model.get("valueOne")) / Integer.parseInt(s)) + "");
                        model.put("valueOne", "0");

                    }
                    model.put("calculated", "yes");
                }
            }
            template.process(model, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
    private void secondString(Map<String, Object> model){
        int valueOne = Integer.parseInt((String) model.get("result"));
        model.put("valueOne", valueOne + "");
        model.put("result", "0");
    }
    public static boolean isNumeric(String str){
        try {
            Integer d = Integer.parseInt(str);
        }
        catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }
}
