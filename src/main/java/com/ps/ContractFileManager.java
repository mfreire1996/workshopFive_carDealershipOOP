package com.ps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class ContractFileManager {
    public void saveContract(Contract contract) {
        try {
            String contractLine = "";

            if (contract instanceof  SalesContract){
                SalesContract sales = (SalesContract) contract;

            } else if (contract instanceof LeaseContract){

            }
            Files.write(Path.get("contracts.csv"), "the text".getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
