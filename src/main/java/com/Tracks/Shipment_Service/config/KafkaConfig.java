package com.Tracks.Shipment_Service.config;

import com.Tracks.Shipment_Service.kafka.event.ShpimentEvent;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class KafkaConfig {
    @Bean
    public ProducerFactory<String, ShpimentEvent>producerFactory(){
        Map<String,Object> config = new HashMap<>();
        config.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092"
        );
        config.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class
        );
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }
    @Bean
    public KafkaTemplate<String,ShpimentEvent>
    kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }
}
/*
This is configuration -
@Bean
public ProducerFactory<String , ShipmentEvent > same as tehe producer producerfacory(){
Map<String,Object> config = new HashMap<>();
config.put(producerconfig.serverlocation- 9902)
config.put(string serialise ,  serilaize class ) so put the input of key you are getting but serializing them
config.put(value, searilaize value in json) put value in it.
return karo new DefaultKAfkaProduerFacorty<>(config);
idk what is this for  @Bean
    public KafkaTemplate<String,ShpimentEvent>
    kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }
}?
@Bean
kafkaTEmplate<
 */
