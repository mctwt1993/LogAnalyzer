# LogAnalyzer
This is repository for program, which will be used to identify long, time-consuming events from log file.

## Prerequisites:  
- custom-build server log file named logfile.txt, where each event has 2 entires in the file (one one event was started and another when event was finished)
- entires in the file have no specific order (finish can occur before a start event for a given id).
- every event in log file is in JSON format with data: 
  * id: the unique event identifier
  * state: "STARTED" or "FINISHED"
  * timestamp: the  timestamp of the event in miliseconds
Optional fields that may also be present: 
  * type: type of log
  * host: hostname 


Example content of logfie.txt :
```
{"id":"scsmbstgra", "state":"STARTED", "type":"APPLICATION_LOG", "host":"12345", "timestamp":1491377495212}
{"id":"scsmbstgrb", "state":"STARTED", "timestamp":1491377495213}
{"id":"scsmbstgrc", "state":"FINISHED", "timestamp":1491377495218}
{"id":"scsmbstgra", "state":"FINISHED", "type":"APPLICATION_LOG", "host":"12345", "timestamp":1491377495217}
{"id":"scsmbstgrc", "state":"STARTED", "timestamp":1491377495210}
{"id":"scsmbstgrb", "state":"FINISHED", "timestamp":1491377495216}
```


