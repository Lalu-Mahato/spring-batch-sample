package com.example.springbatchsample.prospect;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.springbatchsample.prospect.entity.Prospect;

@RestController
@RequestMapping("/api/v1/upload")
public class ProspectController implements ItemWriteListener<Prospect> {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job excelJob;

    @PostMapping("/test")
    public String test(@RequestParam("file") MultipartFile multipartFile) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
            XSSFSheet worksheet = workbook.getSheet("Sheet1");
            int totalRows = worksheet.getPhysicalNumberOfRows();
            System.out.println(totalRows);

            workbook.close();

            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("inputFile" + System.currentTimeMillis() / 1000, multipartFile.getOriginalFilename())
                    .toJobParameters();

            JobExecution jobExecution = jobLauncher.run(excelJob, jobParameters);

            BatchStatus batchStatus = jobExecution.getStatus();
            if (batchStatus == BatchStatus.COMPLETED) {
                return "Excel reading process initiated successfully.";
            } else {
                return "Excel reading process failed.";
            }
        } catch (Exception e) {
            return "Error occurred: " + e.getMessage();
        }
    }
}
