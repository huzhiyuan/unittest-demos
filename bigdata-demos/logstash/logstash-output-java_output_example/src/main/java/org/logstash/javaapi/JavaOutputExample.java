package org.logstash.javaapi;

import co.elastic.logstash.api.Configuration;
import co.elastic.logstash.api.Context;
import co.elastic.logstash.api.Event;
import co.elastic.logstash.api.LogstashPlugin;
import co.elastic.logstash.api.Output;
import co.elastic.logstash.api.PluginConfigSpec;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

// class name must match plugin name
@LogstashPlugin(name = "java_output_example")
public class JavaOutputExample implements Output {

    public static final PluginConfigSpec<String> PREFIX_CONFIG =
            PluginConfigSpec.stringSetting("prefix", "");

    private final String id;
    private String prefix;
    private PrintStream printer;
    private final CountDownLatch done = new CountDownLatch(1);
    private volatile boolean stopped = false;
    Producer<String, String> producer = null;

    // all plugins must provide a constructor that accepts id, Configuration, and Context
    public JavaOutputExample(final String id, final Configuration configuration, final Context context) {
        this(id, configuration, context, System.out);
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        try {
            producer = new KafkaProducer<String, String>(properties);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    JavaOutputExample(final String id, final Configuration config, final Context context, OutputStream targetStream) {
        // constructors should validate configuration options
        this.id = id;
        prefix = config.get(PREFIX_CONFIG);
        printer = new PrintStream(targetStream);
    }

    @Override
    public void output(final Collection<Event> events) {
        Iterator<Event> z = events.iterator();
        while (z.hasNext() && !stopped) {
            String s = prefix + z.next();
            printer.println(s);
            printer.println("are you ok?");
            producer.send(new ProducerRecord<String, String>("test", s));

        }
    }

    @Override
    public void stop() {
        stopped = true;
        done.countDown();
    }

    @Override
    public void awaitStop() throws InterruptedException {
        done.await();
    }

    @Override
    public Collection<PluginConfigSpec<?>> configSchema() {
        // should return a list of all configuration options for this plugin
        return Collections.singletonList(PREFIX_CONFIG);
    }

    @Override
    public String getId() {
        return id;
    }
}
