package com.example.springbatchsample.config;

import java.util.List;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.example.springbatchsample.prospect.ProspectService;
import com.example.springbatchsample.prospect.entity.Prospect;

@Component
public class ConsoleItemWriter implements ItemWriter<Prospect> {

    @Override
    public void write(Chunk<? extends Prospect> chunk) throws Exception {
        List<? extends Prospect> items = chunk.getItems();

        ProspectService prospectService = new ProspectService();
        prospectService.processProspects(items);
    }
}
