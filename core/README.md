# list error code
0 success
1 Argument not vali

# Build

```
mvn clean package
```
skip test
```
mvn clean package -Dmaven.test.skip
```

# Running
```   
java -jar core-0.0.1-SNAPSHOT.jar --spring.config.location=application.yml
```

# Service

Buatlah file koperasi-core.service letakan di 
```
[Unit]
Description=Koperasi Aneka Usaha Core
After=syslog.target network.target

[Service]
User=rizkir
Group=rizkir

ExecStart=/usr/bin/java -jar /home/rizkir/system/core-0.0.1-SNAPSHOT.jar --spring.config.location=/home/rizkir/system/application.yml
ExecStop=/bin/kill -15 $MAINPID
SuccessExitStatus=143

[Install]
WantedBy=multi-user.target
```

Jalankan :

```
systemctl start koperasi-core.service
```

matikan :

```
systemctl stop koperasi-core.service
```

status :

```
systemctl status koperasi-core.service
```

# Test

```
curl -v -d '{"username": "admin","password":"12345678"}' -H 'Content-Type: application/json' http://localhost:8888/api/v1/auth/login
```

```
curl -v -d '{"username": "admin","password":"12345678"}' -H 'Content-Type: application/json' https://koperasi.yacanet.com/api/v1/auth/login
```