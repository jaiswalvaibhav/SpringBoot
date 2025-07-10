# Kafka Quickstart on macOS

A comprehensive guide to get Apache Kafka up and running on macOS systems.

## Prerequisites

### Java 17+ Installation
Java 17 or higher is required. Check your current Java version:

```bash
java -version
```

If Java is not installed or you need to upgrade, install OpenJDK 21 using Homebrew:

```bash
brew install openjdk@21
```

Add Java to your PATH by adding this line to your shell configuration file (`.zshrc` or `.bash_profile`):

```bash
export PATH="/usr/local/opt/openjdk@21/bin:$PATH"
```

Alternatively, follow the guidance provided by Homebrew after installation.

### Docker (Optional)
For Docker-based setup, install Docker Desktop:

```bash
brew install --cask docker
```

## Step 1: Get Kafka

Download the latest Kafka release from the [Kafka releases page](https://kafka.apache.org/downloads), then extract and navigate to the directory:

```bash
tar -xzf kafka_2.13-4.0.0.tgz
cd kafka_2.13-4.0.0
```

## Step 2: Start the Kafka Environment

### Option 1: Using Downloaded Files (No Docker)

#### 1. Generate a Cluster UUID
```bash
KAFKA_CLUSTER_ID="$(bin/kafka-storage.sh random-uuid)"
```

#### 2. Format Log Directories
```bash
bin/kafka-storage.sh format --standalone -t "$KAFKA_CLUSTER_ID" -c config/server.properties
```

#### 3. Start the Kafka Server
```bash
bin/kafka-server-start.sh config/server.properties
```

**Important:** Leave this terminal running. If you encounter permission errors, make scripts executable:

```bash
chmod +x bin/*.sh
```

### Option 2: Using Docker

#### Standard JVM Docker Image
```bash
docker pull apache/kafka:4.0.0
docker run -p 9092:9092 apache/kafka:4.0.0
```

#### GraalVM Native Kafka Docker Image
```bash
docker pull apache/kafka-native:4.0.0
docker run -p 9092:9092 apache/kafka-native:4.0.0
```

## Step 3: Create a Topic

Open a **new terminal window** and create a topic:

```bash
bin/kafka-topics.sh --create --topic quickstart-events --bootstrap-server localhost:9092
```

Verify the topic was created:

```bash
bin/kafka-topics.sh --describe --topic quickstart-events --bootstrap-server localhost:9092
```

## Step 4: Write Events

In another **new terminal**, start a producer:

```bash
bin/kafka-console-producer.sh --topic quickstart-events --bootstrap-server localhost:9092
```

Type some messages:

```
>This is my first event
>This is my second event
```

## Step 5: Read Events

Open yet another **new terminal** and start a consumer:

```bash
bin/kafka-console-consumer.sh --topic quickstart-events --from-beginning --bootstrap-server localhost:9092
```

You should see the messages you typed:

```
This is my first event
This is my second event
```

## Step 6: Kafka Connect (Import/Export Streams)

Kafka Connect allows you to import and export data streams.

### 1. Configure Connector
Add the connector JAR to your configuration:

```bash
echo "plugin.path=libs/connect-file-4.0.0.jar" >> config/connect-standalone.properties
```

### 2. Create Test Data
```bash
echo -e "foo\nbar" > test.txt
```

### 3. Run Kafka Connect
```bash
bin/connect-standalone.sh config/connect-standalone.properties config/connect-file-source.properties config/connect-file-sink.properties
```

### 4. Verify the Results
Check the sink file:

```bash
more test.sink.txt
```

Expected output:
```
foo
bar
```

Or consume the topic directly:

```bash
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic connect-test --from-beginning
```

### 5. Test Real-time Processing
Append more data to test real-time processing:

```bash
echo "Another line" >> test.txt
```

## Step 7: Kafka Streams Example

Here's a basic WordCount example using Kafka Streams:

```java
KStream<String, String> textLines = builder.stream("quickstart-events");

KTable<String, Long> wordCounts = textLines
    .flatMapValues(line -> Arrays.asList(line.toLowerCase().split(" ")))
    .groupBy((keyIgnored, word) -> word)
    .count();

wordCounts.toStream().to("output-topic", Produced.with(Serdes.String(), Serdes.Long()));
```

For complete examples and detailed implementation, refer to the [Kafka Streams documentation](https://kafka.apache.org/documentation/streams/).

## Step 8: Shut Down

### Stop Services
1. Stop the producer and consumer processes (Ctrl+C)
2. Stop the Kafka server (Ctrl+C)

### Clean Up Data (Optional)
If you want to delete all Kafka data:

```bash
rm -rf /tmp/kafka-logs /tmp/kraft-combined-logs
```

## macOS-Specific Notes

### Terminal Compatibility
- The `.sh` scripts work seamlessly in macOS Terminal or iTerm2
- Compatible with both `zsh` (default) and `bash` shells

### Script Permissions
Ensure all scripts have execution permissions:

```bash
chmod +x bin/*.sh
```

### Docker Desktop
If using Docker-based setup, ensure Docker Desktop is running before executing any `docker` commands.

## Troubleshooting

### Common Issues

**Permission Denied Errors:**
```bash
chmod +x bin/*.sh
```

**Java Not Found:**
Verify Java installation and PATH configuration:
```bash
java -version
echo $PATH
```

**Port Already in Use:**
Check if another process is using port 9092:
```bash
lsof -i :9092
```

**Docker Issues:**
Ensure Docker Desktop is running and has sufficient resources allocated.

## Next Steps

- Explore [Kafka documentation](https://kafka.apache.org/documentation/) for advanced configurations
- Learn about [Kafka Streams](https://kafka.apache.org/documentation/streams/) for stream processing
- Check out [Kafka Connect](https://kafka.apache.org/documentation/#connect) for data integration
- Consider [Confluent Platform](https://www.confluent.io/) for enterprise features

---

*This quickstart guide provides a foundation for running Kafka on macOS. For production deployments, consider additional configuration for security, monitoring, and scalability.*