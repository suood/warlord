package com.suood.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import xyz.downgoon.snowflake.Snowflake;

public class CSVUtil {
  private static final String SAMPLE_CSV_FILE_PATH = "/Users/suguangqiang/Documents/无标题/11.csv";
  private static final String SAMPLE_CSV_W_FILE_PATH = "/Users/suguangqiang/Documents/无标题/22.csv";
  public static void main(String[] args) throws IOException {
    Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
    CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
    Snowflake snowflake = new Snowflake(1,1);
    List<Sku> totalList =  csvParser.getRecords().stream().map(e->{

      String spuId = snowflake.nextId()+"";
      String spiderNo = e.get(0);
      String spuArray = e.get(1);
      List<Sku> skuList = JSONArray.parseArray(spuArray,Sku.class) ;
      skuList =  skuList.stream().map($-> {
        $.setSpuId(spuId);
        $.setSpiderNo(spiderNo);
        return $;
      }).collect(Collectors.toList())  ;
      return    skuList;
    }).flatMap(List::stream).collect(Collectors.toList()) ;
    System.out.println();

    BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_W_FILE_PATH));

    CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL
        .withHeader("spiderNo","spuId", "skuId", "spec"));

    for (Sku sku : totalList) {
      csvPrinter.printRecord(sku.spiderNo,sku.spuId,sku.skuId,sku.spec);
    }
    csvPrinter.flush();
  }

}
@Data
class Sku {
  String spiderNo;
  String spuId;
  @JSONField (name = "sku_group")
  String  spec;
  @JSONField(name="sku_group-data-sku")
  String  skuId;

}
