# TesteMoip
Criação de aplicação para calcular valores Webhooks e status.
ntrodução

O Moip tem uma aplicação que envia webhooks para os ecommerces de seus clientes, esses webhooks possuem informações sobre pagamentos (se foram autorizados, cancelados, etc).

Esta aplicação gera logs bastante grandes, precisamos descobrir através do log quem são os clientes que mais recebem os webhooks e verificar todos o response status retornados pelo servidores dos clientes.

Task
O arquivo de log em anexo contém informações de envio de webhooks no format:

level=info response_body="" request_to"<url>" response_headers= response_status="<code>"

Onde:

url: é a url para onde foi enviado o webhook
code: é o status code retornado pelo servidor do cliente
As outras informações são irrelevantes para esta task.

Você deve parsear o arquivo e no final mostrar as seguintes informações na saída:

3 urls mais chamadas com a quantidade
Uma tabela mostrando a quantidade de webhooks por status
