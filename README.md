# LOCALSTACK test

## Requirements
* `python` (Python 2.x up to 3.8 supported)
* `pip` (python package manager)
* `Docker`

## Installing

Via `pip`:

```
pip install localstack
```

## Running

By default, LocalStack gets started inside a Docker container using this command:

```
localstack start
```

## Running with docker

```
docker-compose up
```


## Test event
```
awslocal kinesis put-record --stream-name my-test-stream --partition-key 1 --data file://test-event.json
```
## View elements table Dynamo
```
awslocal dynamodb scan --table-name my-test
```
## Get records
First get shard-iterator:

```
awslocal kinesis get-shard-iterator --shard-id shardId-000000000000 --shard-iterator-type TRIM_HORIZON --stream-name my-test-stream 

{
    "ShardIterator": "AAAAAAAAAAH4/NNIbil253ccAdCy0+9WrW4LCQTwC/9JlfwcccuOOfeyv4VBpyyZVR0+sDslIZfA1AmDhA4MCTr4yI0hC2GGRTKoGs5IvI9jLH/2KgfnL9Sa3j0Y8ruPDsFO7PSnkKj2Pw1VHPeSZHqs2qM5wj8kTDbd8CEd34PlGiZPGhDcFsxLukf4yMV4XkqUTbRX18lFGCeIft8RYe/Rzy4pXTCv"
}
```


And then get records with shard-iterator obtained:

```
awslocal kinesis get-records --shard-iterator AAAAAAAAAAH4/NNIbil253ccAdCy0+9WrW4LCQTwC/9JlfwcccuOOfeyv4VBpyyZVR0+sDslIZfA1AmDhA4MCTr4yI0hC2GGRTKoGs5IvI9jLH/2KgfnL9Sa3j0Y8ruPDsFO7PSnkKj2Pw1VHPeSZHqs2qM5wj8kTDbd8CEd34PlGiZPGhDcFsxLukf4yMV4XkqUTbRX18lFGCeIft8RYe/Rzy4pXTCv

{
    "Records": [
        {
            "SequenceNumber": "49618749777551303327738698515421038968847136295827275778",
            "ApproximateArrivalTimestamp": 1622402311.804,
            "Data": "RXN0byBlcyBvdHJhIHBydWViYQ==",
            "PartitionKey": "1"
        },
        {
            "SequenceNumber": "49618749777551303327738698515422247894666827134901682178",
            "ApproximateArrivalTimestamp": 1622403420.895,
            "Data": "w6/Cu8K/eyJpZCI6IjEiLCJ0eXBlIjogIlRFU1QiLCJvcmlnaW5hdG9yIjoiQ09NQU5ET1MifQ==",
            "PartitionKey": "1"
        }
    ],
    "NextShardIterator": "AAAAAAAAAAEnDwO7VVZNMjiB9TOfp8CRlbbrdqcJqR/y4c7Ei8/b0Lk1IFsNMGVwU6CT7ielPSzLCUl+f78h/01ZFlpgO31Zx7mzWonA1UlZW1q5cY/6eQpSi3JuL2B4Se7qgVDTxMQvKUsHhW/219croCPQnNs2bKTqln+wODVEUNxkWZPLjVKJRj+tbHi82O06fTPI/5Tq3zQ22zu0xYa8uzihn+07",
    "MillisBehindLatest": 0
}