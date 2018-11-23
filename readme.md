# Dozor Game Repository

Dozor Game is opensource multiplayer game for 2 players with crossplatform GUI and dedicated server.

### Installation
 Dozor Game requires:
  - jdk from: http://jdk.java.net
  - gradle. Simple way is to install chocolatey: https://chocolatey.org/install and then https://chocolatey.org/packages/gradle
### Dedicated server
Build dedicated server:
```sh
$ gradle serverJar
```
and run
```sh
$ java -jar build\libs\Server-1.0-SNAPSHOT.jar
```
### Client
Build client:
```sh
$ gradle processResources clientJar
```
and run
```sh
$ java -jar build\libs\Client-1.0-SNAPSHOT.jar
```
To start game input:
  - dedicated server ip (lan or provider)
  - dedicated server port (30303 default)
  - nickname (visible to players)
Then if you want create session  
  - click "create"(создать)
If you wan join created session
  - session number visible to session creator(it will be improved later)
  - click "join"(присоединиться)

# Game Rules
[Russian localization](game_rules/ru/determenistic_dozor_game_rules.pdf)

### Todos

 - en localization of game and rules

License
----

Apache v2


**Thanks from Pavel Semushin and Igor Kachkovsky**

[//]: # (Thanks from Pavel Semushin and Igor Kachkovsky)


   [git-repo-url]: <https://github.com/kachkovsky/dozorgame.git>
