# Desafio prático de automação de testes
## Camada back-end

------

#### Ferramentas utilizadas
- Java 17;
- Maven 3.0;
- REST-Assured 6.0.0;
- JUnit 6.1.0;
- Allure 2.32.0;

#### Como executar os testes
1) Antes de tudo, você precisa ter o [Java 17](https://uanscarvalho.com.br/instalando-java-17-no-windows/) e o [Maven](https://dicasdeprogramacao.com.br/como-instalar-o-maven-no-windows/) instalados e configurados na sua máquina. Os links anteriores mostram este processo no Windows.

2) Na pasta de sua preferência, abra o terminal de comandos e cole este comando:

```bash
git clone https://github.com/cassiolinden/desafio-pratico-frontend.git
```

3) Ainda na mesma pasta, para executar os testes, você precisa rodar os seguintes comandos:

```bash
# Limpa e roda os testes (headless por padrão via config.properties)
mvn clean test

# Alternativamente, pode ser rodado com diferentes propriedades via linha de comando
mvn clean test -Dbrowser=chrome -Dheadless=true
```

4) Para visualizar o relatório da execução, rode os seguintes comandos:

```bash
# Gerar relatório Allure (requer Allure CLI instalado)
mvn allure:report
mvn allure:serve
```

#### Estrutura do projeto

```
├── .github/
│   └── workflows/
│       └── ci.yml
├── .gitignore
├── pom.xml
└── src/
    ├── main/
    │   └── java/
    │       ├── core/
    │       │   ├── ApiRequestHelper.java
    │       │   ├── Config.java
    │       │   └── RequestBuilder.java
    │       └── entities/
    │           ├── auth/
    │           │   ├── Auth.java
    │           │   └── Token.java
    │           ├── booking/
    │           │   ├── AvailableRoom.java
    │           │   ├── Booking.java
    │           │   ├── BookingDates.java
    │           │   ├── BookingSummaries.java
    │           │   ├── BookingSummary.java
    │           │   ├── Bookings.java
    │           │   └── CreatedBooking.java
    │           ├── branding/
    │           │   ├── Address.java
    │           │   ├── Branding.java
    │           │   ├── Contact.java
    │           │   ├── Map.java
    │           ├── message/
    │           │   └── Message.java
    │           ├── report/
    │           │   ├── Entry.java
    │           │   ├── Report.java
    │           └── room/
    │               └── Room.java
    └── test/
        ├── java/
        │   ├── BaseTest.java
        │   ├── BookingTest.java
        │   ├── BrandingTest.java
        │   ├── LoginTest.java
        │   ├── LogoutTest.java
        │   ├── MessageTest.java
        │   ├── ReportTest.java
        │   ├── RoomTest.java
        │   ├── TestSuite.java
        │   └── ValidateTest.java
        └── resources/
            ├── allure.properties
            ├── config.properties
            ├── room-schema.json
            └── rooms-schema.json
```

#### Estratégia adotada

Para a escolha do software a ser testado, fui atrás de algo que pudesse representar um fluxo de um software real, além da possibilidade de ser testado tanto no front quanto no back-end. Nas minhas pesquisas, cheguei [neste repositório](https://github.com/BMayhew/awesome-sites-to-test-on) com uma lista de sugestões. Nele, encontrei o [Sunny Meadows Bed & Breakfast](https://automationintesting.online/), um portal que simula a reserva de quartos numa pousada, atendendo, assim, meus requisitos. Ele também conta com um [repositório no GitHub](https://github.com/mwinteringham/restful-booker-platform).

Considerando os testes da camada back-end, eu aproveitei da disponibilização do repositório do sistema no GitHub e passei a criação dos casos de teste para o Copilot, seguindo os requisitos  do desafio, onde foram gerados cenários de sucesso e falha para todos os endpoints da aplicação. Como os cenários também foram escritos pelo Copilot, repeti o processo seguido para a camada front-end - todos eles também passaram por uma revisão criteriosa do preenchimento feito pela ferramenta de IA.

Para a automação de testes desta camada, pensei em utilizar o REST-Assured em Java, também pensando na minha familiaridade com a ferramenta, numa arquitetura baseada em Facade, com um Simple Factory de suporte para padronizar a criação do `RequestSpecification`. Os cenários escolhidos para a automação procuraram abranger aspectos que não foram cobertos inicialmente na automação front-end, focando na parte administrativa da aplicação (login, validação de token, logout e o CRUD dos quartos).

Tanto o projeto de automação front-end quanto o de back-end contam com a geração dos relatórios pelo Allure, cujos resultados são disponibilizados via GitHub Pages. Também incluí para ambos a execução via pipeline do GitHub Actions, de forma agendada para todas as manhãs de segunda a sexta, além da possibilidade de acionar a execução de forma manual.

Os cenários escolhidos para a automação e o status da última execução podem ser conferidos [aqui](https://cassiolinden.github.io/desafio-pratico-backend/).