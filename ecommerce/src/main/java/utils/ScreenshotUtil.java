package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class ScreenshotUtil {

    public static void takeScreenshot(WebDriver driver, String screenshotName) {
        try {
            // Timestamp
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            // Take screenshot
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(Constants.SCREENSHOT_DIR + screenshotName + "_" + timeStamp + ".png");

            // Copy file to destination
            Files.copy(srcFile.toPath(), destFile.toPath());

            System.out.println("📸 Screenshot saved: " + destFile.getAbsolutePath());

            // Clean up old screenshots if more than 10
            deleteOldScreenshots();

        } catch (IOException e) {
            System.err.println("❌ Failed to save screenshot: " + e.getMessage());
        }
    }

    private static void deleteOldScreenshots() {
        File folder = new File(Constants.SCREENSHOT_DIR);
        File[] screenshots = folder.listFiles((dir, name) -> name.endsWith(".png"));

        if (screenshots != null && screenshots.length > 10) {
            // Sort by oldest first
            Arrays.sort(screenshots, Comparator.comparingLong(File::lastModified));

            for (int i = 0; i < screenshots.length - 10; i++) {
                screenshots[i].delete();
            }
        }
    }
}