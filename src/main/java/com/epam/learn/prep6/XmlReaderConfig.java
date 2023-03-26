package com.epam.learn.prep6;

import com.epam.learn.prep6.model.Ticket;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class XmlReaderConfig {


    @Bean
    public ItemReader<Ticket> itemReader() {
        Jaxb2Marshaller studentMarshaller = new Jaxb2Marshaller();
        studentMarshaller.setClassesToBeBound(Ticket.class);

        return new StaxEventItemReaderBuilder<Ticket>()
                .name("ticketsReader")
                .resource(new ClassPathResource("tickets.xml"))
                .addFragmentRootElements("ticket")
                .unmarshaller(studentMarshaller)
                .build();
    }
}
