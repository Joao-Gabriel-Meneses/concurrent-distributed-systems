Exercício 1:
• Um estádio tem capacidade para 200 pessoas e 4 catracas funcionando em paralelo, cada uma como uma thread. Implemente sem usar synchronized em nenhum ponto do controle de lotação. O sistema deve: 
• (a) manter o total de público em uma classe atômica, usando 
compareAndSet para nunca ultrapassar a capacidade; 
• (b) contar quantas pessoas passaram por cada catraca em uma coleção 
concorrente; 
• (c) manter um log compartilhado de todas as entradas em uma coleção 
sincronizada; 
• (d) ao final, imprimir o relatório e o primeiro e o último registro do log.