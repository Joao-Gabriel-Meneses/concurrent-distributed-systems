Exercício 2:
• Três sensores (threads produtoras) geram leituras continuamente e as publicam em uma fila compartilhada de capacidade 50. Uma thread painel consome as leituras e as exibe. Após 200 ms, a thread principal envia um sinal de encerramento. Implemente de 
modo que: 
• (a) o sinal de parada seja um campo volatile boolean; 
• (b) o painel aguarde por novas leituras sem consumir CPU em espera 
ativa; 
• (c) nenhuma leitura publicada seja perdida após o sinal de parada; 
• (d) os contadores de leituras produzidas e processadas usem classes 
atômicas