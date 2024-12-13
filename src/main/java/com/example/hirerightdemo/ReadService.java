package com.example.hirerightdemo;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

@Component
public class ReadService {


    public List<String> getWords(String... filePaths){

        List<String> lines = Collections.emptyList();

        try{
            for(String path : filePaths)
            lines = Files.readAllLines(Paths.get(Path.of(path).toUri()));
        }

        catch(IOException e){
            e.printStackTrace();
        }

        return lines;
    }

}
