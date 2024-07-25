package br.com.pix.stream;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.pix.modules.pix.dtos.PixDTO;
import br.com.pix.serdes.PixSerdes;

@Component
public class PixConsumer {

    @Autowired
    public void buildPipeline(StreamsBuilder streamsBuilder) {

        KStream<String, PixDTO> messageStream = streamsBuilder
			.stream("pix-topic", Consumed.with(Serdes.String(), PixSerdes.serdes()))
			.peek((key, value) -> System.out.println("Pix recebido: " + value.getOriginKey()))
			.filter((key, value) -> value.getValue() > 5000)
			.peek((key, value) -> System.out.println("Pix: " + key + " será verificado para possível fraude"));

        messageStream.print(Printed.toSysOut());
        messageStream.to("pix-topic-verificacao-fraude", Produced.with(Serdes.String(), PixSerdes.serdes()));

        KTable<String, Double> aggregateStream = streamsBuilder
			.stream("pix-topic", Consumed.with(Serdes.String(), PixSerdes.serdes()))
			.peek((key, value) -> System.out.println("Pix recebido: " + value.getOriginKey()))
			.filter((key, value) -> value.getValue() != null)
			.groupBy((key, value) -> value.getOriginKey())
			.aggregate(
					() -> 0.0,
					(key, value, aggregate) -> (aggregate + value.getValue()),
					Materialized.with(Serdes.String(), Serdes.Double())
			);

        aggregateStream.toStream().print(Printed.toSysOut());
        aggregateStream.toStream().to("pix-topic-agregacao", Produced.with(Serdes.String(), Serdes.Double()));
    }
}
