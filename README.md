
# Kafka Pix

Este projeto é uma solução baseada em microsserviços utilizando Kafka para gerenciar transações Pix, com funcionalidades de produção, consumo e processamento de streams de dados.

## Descrição da Solução

A solução é dividida em três projetos principais:

1. **pix-consumer**: Serviço responsável por consumir mensagens do Kafka.
2. **pix-producer**: API REST que atua como produtor de mensagens para o Kafka.
3. **pix-stream**: Serviço de stream de dados que processa transações Pix para detecção de fraude e agregação de valores.

### Arquitetura

- **pix-producer**: Envia transações Pix para o tópico Kafka `pix-topic`.
- **pix-stream**: Processa as transações recebidas de `pix-topic`, verifica fraudes em transações acima de R$5000, e agrega o valor total enviado por cada chave, publicando os resultados nos tópicos `pix-topic-verificacao-fraude` e `pix-topic-agregacao`.
- **pix-consumer**: Consome mensagens dos tópicos de verificação de fraude e agregação, executando ações conforme necessário.

## Setup Inicial

Para iniciar a aplicação, é necessário ter o Kafka e Java instalados na máquina. Siga os passos abaixo para configurar e executar os serviços:

### 1. Subir o Zookeeper

Execute o comando abaixo no terminal para iniciar o Zookeeper:

```bash
bin/zookeeper-server-start.sh config/zookeeper.properties
```

### 2. Subir o Kafka

Execute o comando abaixo no terminal para iniciar o Kafka:

```bash
bin/kafka-server-start.sh config/server.properties
```

### 3. Configurar e Iniciar os Serviços

- Clone o repositório e abra separadamente uma instância de cada um dos serviços `pix-consumer`, `pix-producer` e `pix-stream`.
- Altere as configurações do banco de dados no arquivo `application.properties` de cada serviço, conforme necessário.
- Execute cada projeto separadamente.

### 4. Testar os Endpoints do `pix-producer`

- **Cadastrar chaves Pix**: Envie duas requisições POST para `http://localhost:8080/key` cada uma com uma chave diferente. Essas chaves serão utiliazdas posteriormente no endpoint do Pix. Exemplo dos JSON's:

```json
{
    "chave": "123"
}
```
```json
{
    "chave": "456"
}
```

- **Realizar uma transação Pix**: Envie uma requisição POST para `http://localhost:8080/pix` com o seguinte JSON no corpo:

```json
{
    "originKey": "123",
    "targetKey": "456",
    "value": 6000
}
```

Após realização do Pix, os tópicos devem receber os dados corretamente. Pode ser conferido também no terminal das aplicações o registro dos eventos disparados. 

## Conclusão

Este projeto demonstra como utilizar Kafka para integrar e processar eventos em uma arquitetura de microsserviços, garantindo a escalabilidade e resiliência necessárias para sistemas de pagamento modernos. O uso de streams de dados e agregação permite detecção e resposta em tempo real a eventos críticos, como fraudes, proporcionando uma solução robusta para o gerenciamento de transações Pix.
