Para otimização no uso da memória do dispositivo temos um exemplo do uso de concatenação de strings. Concatenar strings da forma “string_1” + “string_2” além de ser custoso, realizando várias operações para juntar as duas, deixa muitos objetos soltos no heap, principalmente se este tipo de concatenação for feito dentro de um loop.
Para minimizar os custos de memória é aconselhável utilizar as classes StringBuilder e StringBuffer. O StringBuilder faz pré-alocações de memória e permite que o conteúdo seja mutável, evitando várias alocações de memória.
Uma alteração feita foi a mostrada a seguir, que forma uma lista de strings com informações do banco de dados para serem mostradas:


Antes:  
String dataBaseInfo = "";

while(res.moveToNext()){  
    dataBaseInfo = dataBaseInfo + res.getString(0) + ": " + res.getString(1) + "\n";  
}

Depois:  

StringBuilder dataBaseInfo = new StringBuilder();  

while(res.moveToNext()){  
    dataBaseInfo.append(res.getString(0));  
    dataBaseInfo.append(": ");  
    dataBaseInfo.append(res.getString(1));  
    dataBaseInfo.append("\n");  
}  

Para o teste seguinte inseri dezenas de dados no banco e depois pedi para fazer a leitura com os códigos anteriores. Mesmo a diferença não sendo 
grando por ser uma operação pequena, serve para análise e aprendizado para situações mais críticas. O segundo gráfico é o otimizado.

![memoria12](./images/memore12.png)
![memoria2](./images/memore2.png)
