package com.apiit.dmvapp;

import org.springframework.web.bind.annotation.*;

import java.io.*;

@RestController
@CrossOrigin("*")
public class LicenseValidityController {

    @GetMapping("/CheckLicenseValidity/{licenseNo}")
    public String checkLicenseValidity(@PathVariable("licenseNo") String licenseNo) {

        String line = "";
        String cvsSplitBy = ",";

        File csvFile = new File("C:\\Users\\rabiy\\Desktop\\DMVData.csv");

        if (csvFile.isFile()) {

            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                int iteration = 0;
                while ((line = br.readLine()) != null) {

                    if(iteration==0){
                        iteration++;
                        continue;
                    }

                    String[] licenseNums = line.split(cvsSplitBy);

                    if(licenseNums[0].equals(licenseNo)){
                        return "Suspended";
                    }else if(licenseNums[1].equals(licenseNo)){
                        return "Lost";
                    }else if(licenseNums[2].equals(licenseNo)){
                        return "Stolen";
                    }
                }
                return "Valid";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "Invalid File";
    }
}

