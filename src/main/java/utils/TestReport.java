package utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestReport {
	
	static ExtentReports extent;

	public TestReport() {
		archiveReports();
	}

	public  ExtentReports GetReporter() {

		extent = new ExtentReports();
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(GeneralUtilities.GetProjectPath()
				+ "//Reports//CurrentTestResults//" + "TestResult" + GeneralUtilities.GetCurrentTimeStamp() + ".html");
		extent.attachReporter(sparkReporter);
		return extent;
	}

	private void archiveReports() {
		String currentTestResult = GeneralUtilities.GetProjectPath() + "\\Reports\\CurrentTestResults";
		String archivedTestResult = GeneralUtilities.GetProjectPath() + "\\Reports\\ArchivedTestResults";

		File dir= new File(currentTestResult) ;
		if(dir.exists()) {

		File currentfolder = new File(currentTestResult);
		File[] files2 = currentfolder.listFiles();
		int fileCount = files2.length;
		if (fileCount > 0) {
			File[] files = currentfolder.listFiles();

			for (File file : files) {
				Path currentFile = file.toPath();
				Path archivePath = Paths.get(archivedTestResult, currentFile.getFileName().toString());//, currentFile.getFileName().toString()
				try {

					Files.move(currentFile, archivePath, StandardCopyOption.REPLACE_EXISTING);
					TestLogger.info(String.format("File %s has been moved to ArchivedTestResults folder",
							archivePath.getFileName().toString()));
				} catch (Exception e) {
					TestLogger.error(e.getMessage());
				}
			}

		}
		}

	}

}
