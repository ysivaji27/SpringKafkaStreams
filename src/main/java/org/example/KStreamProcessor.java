package org.example;

import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Predicate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class KStreamProcessor {

    @Value("${spring.kafka.topic.texassales}")
    private String texassalestopic;

    public void process(KStream<String, DataModel> stream){

        //KSTREAM FILTER: Filter the Stream to get Texas sales into a new Texas Topic
        stream.filter(new Predicate<String, DataModel>() {
            @Override
            public boolean test(String key, DataModel object) {
                if(object!=null && object.getState()!=null && object.getState().trim().equalsIgnoreCase("TEXAS")) {
                    return true;
                }else {
                    return false;
                }
            }
        }).to(texassalestopic);

    }
}