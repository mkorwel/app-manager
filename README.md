Kompilacja: mvn package
Uruchomienie: java -jar target/app-manager-0.0.1-SNAPSHOT.war

Pobranie wniosków: curl http://127.0.0.1:8080/applications

Stworzenie wniosku: curl -X POST -i -H "Content-Type: application/json" http://127.0.0.1:8080/applications -d '{"name":"n", "content":"c"}'

Modyfikacja danych: curl -X PUT -i -H "Content-Type: application/json" http://127.0.0.1:8080/applications/1 -d '{"name":"n1", "content":"c1"}'

Pobranie wniosku po id: curl http://127.0.0.1:8080/applications/1

Usunięcie wniosku: curl -X DELETE -i -H "Content-Type: application/json" http://127.0.0.1:8080/applications/3 -d '{"reason":"r"}'

Weryfikacja wniosku: curl -X PATCH -i http://127.0.0.1:8080/applications/1/verify

Akcjeptacja wniosku: curl -X PATCH -i http://127.0.0.1:8080/applications/1/accept

Odrzucenie wniosku: curl -X PATCH -i -H "Content-Type: application/json" http://127.0.0.1:8080/applications/1/reject -d '{"reason":"r"}'

Publikacja wniosku: curl -X PATCH -i http://127.0.0.1:8080/applications/1/publish
