package com.example.hirerightdemo;

import ch.qos.logback.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class CountService {

    @Autowired
    private final ReadService readService;

    public CountService(ReadService readService) {
        this.readService = readService;
    }

    public List<Result> count( String... args){

       List<String> words=readService.getWords(args[0].split(" "));

      return  words.stream().map(word -> count(word, args)).collect(Collectors.toList());




    }

    private Result count(String word, String... args){

        int words = 0;
        int symbols =0;

        if(args.length >1){
            for(int i =1; i<args.length; i++){
                if(args[i].contains("-S")){
                    String replaced = exclude(word, args[i]);
                    words= replaced.split(" ").length;

                }
                if(args[i].contains("-L")){
                     words =Long.valueOf(Arrays.stream(word.split(" ")).filter(w -> Character.isUpperCase(w.charAt(0))).count()).intValue();

                }
                if(args[i].contains("-C")){
                    symbols= word.toCharArray().length;
                }
            }
        }

        return new Result(words,symbols);
    }

    private String exclude(String word, String params){

        StringBuilder replaced = new StringBuilder();

        String paramTrimmed=params.substring(2);
        String[] split = paramTrimmed.split(",");

        for (String excluded : split){

            replaced.append(word.replace(excluded, " "));
        }
        return replaced.toString();
    }

}
