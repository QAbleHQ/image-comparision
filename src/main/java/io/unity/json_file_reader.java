package io.unity;


import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;

public class json_file_reader {


    public JSONObject getTestConfig() {


        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get("src/config/TestConfig.json").toAbsolutePath()));
        } catch (Exception e) {
            System.out.println("config file not found");
        }
        JSONObject testConfig = new JSONObject(data);

        return testConfig;
    }

    public String getEnv() {
        JSONObject object = getTestConfig();
        return object.getString("env");
    }

    public String get_execution_on() {
        JSONObject object = getTestConfig();
        return object.getString("executionOn");
    }


    public String get_platform_type() {
        JSONObject object = getTestConfig();
        JSONObject platform = (JSONObject) object.get("platform");
        return platform.getString("platformType");
    }


    public String get_browser() {
        JSONObject object = getTestConfig();
        JSONObject platform = (JSONObject) object.get("platform");
        return platform.getString("browser");
    }

    public String get_visual_test_mode()
    {
        JSONObject object = getTestConfig();
        JSONObject platform = (JSONObject) object.get("visual_test");
        return platform.getString("mode");
    }

    public String get_visual_test_assert_mode()
    {
        JSONObject object = getTestConfig();
        JSONObject platform = (JSONObject) object.get("visual_test");
        return platform.getString("assert");
    }

}
