package io.unity;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.unity.autoweb.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class image_comparison {

    WebDriver driver;

    public image_comparison(WebDriver driver) {
        this.driver = driver;
    }


    private String create_browser_directory() {
        json_file_reader config_reader = new json_file_reader();


        Path screen_shot_path = Paths.get("src/test/resources/visual_test/base_images/" + config_reader.get_browser());
        File file = new File(screen_shot_path.toAbsolutePath().toString());

        if (!file.exists()) {
            try {
                System.out.println(screen_shot_path.toAbsolutePath().toString());
                file.mkdir();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return screen_shot_path.toAbsolutePath().toString();
    }

    private String create_actual_directory() {
        json_file_reader config_reader = new json_file_reader();


        Path screen_shot_path = Paths.get("./src/test/resources/visual_test/actual_images/" + config_reader.get_browser());

        File file = new File(screen_shot_path.toAbsolutePath().toString());
        if (!file.exists()) {
            try {
                System.out.println(screen_shot_path.toAbsolutePath().toString());
                file.mkdir();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return screen_shot_path.toAbsolutePath().toString();
    }

    public void compare_image(String image_name) {

        json_file_reader config_reader = new json_file_reader();
        Browser browser = new Browser(driver);

        image_name = image_name + "_" + config_reader.get_browser();

        String base_dir_path = create_browser_directory();
        String actual_dir_path = create_actual_directory();
        if (config_reader.get_visual_test_mode().equalsIgnoreCase("learn")) {
            browser.take_page_screenshot(base_dir_path, image_name);
        }

        if (config_reader.get_visual_test_mode().equalsIgnoreCase("compare")) {
            browser.take_page_screenshot(actual_dir_path, image_name);

            try {

                BufferedImage expectedImage = ImageComparisonUtil.readImageFromResources(create_browser_directory() + "/" + image_name + ".png");

                BufferedImage actualImage = ImageComparisonUtil.readImageFromResources(create_actual_directory() + "/" + image_name + ".png");


                File resultDestination = new File(create_actual_directory() + "/" + image_name + "_result.png");
                ImageComparisonResult imageComparisonResult = new ImageComparison(expectedImage, actualImage, resultDestination).compareImages();

                System.out.println(imageComparisonResult.getImageComparisonState());


            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        //Create ImageComparison object with result destination and compare the images.

    }


}

