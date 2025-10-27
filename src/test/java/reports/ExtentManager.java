package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import java.nio.file.Paths;

public class ExtentManager {
    private static ExtentReports extent;

    public synchronized static ExtentReports getInstance() {
        if (extent == null) {

            // Đường dẫn lưu report
            String reportDir = "target/extent";
            String reportPath = Paths.get(reportDir, "ExtentReport.html").toString();

            // Tạo Spark reporter
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

            // ===========================
            // 🎨 GIAO DIỆN & GIAO DIỆN NÂNG CAO
            // ===========================
            spark.config().setTheme(Theme.DARK);                          // Giao diện tối
            spark.config().setDocumentTitle("Automation Report");          // Tiêu đề tab trình duyệt
            spark.config().setReportName("Selenium + TestNG UI Report");   // Tiêu đề chính
            spark.config().setEncoding("UTF-8");                           // Hỗ trợ tiếng Việt
            spark.config().setTimeStampFormat("dd/MM/yyyy HH:mm:ss");      // Định dạng thời gian
            spark.config().enableOfflineMode(true);                        // Chạy offline không cần CDN

            // 🔹 Sắp xếp thứ tự các tab bên trái
            spark.viewConfigurer()
                    .viewOrder()
                    .as(new ViewName[]{
                            ViewName.DASHBOARD,
                            ViewName.TEST,
                            ViewName.CATEGORY,
                            ViewName.AUTHOR,
                            ViewName.DEVICE,
                            ViewName.EXCEPTION,
                            ViewName.LOG
                    }).apply();

            // ===========================
            // ⚙️ GẮN REPORTER VÀ THÔNG TIN HỆ THỐNG
            // ===========================
            extent = new ExtentReports();
            extent.attachReporter(spark);

            // Thông tin hiển thị ở mục "System Environment"
            extent.setSystemInfo("Framework", "Selenium + TestNG");
            extent.setSystemInfo("Environment", System.getProperty("env", "LOCAL"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("OS", System.getProperty("os.name"));
        }

        return extent;
    }
}
