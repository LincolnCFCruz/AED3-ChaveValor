# AED3-Banco-de-Dados-Chave-Valor

- [Desenvolvedores](https://github.com//LincolnCFCruz/BD-ChaveValor#desenvolvedores)
- [Enunciado](https://github.com//LincolnCFCruz/BD-ChaveValorr#enunciado)
- [Definição do problema](https://github.com/LincolnCFCruz/BD-ChaveValor#defini%C3%A7%C3%A3o-do-problema)
- [Estrutura Base](https://github.com//LincolnCFCruz/BD-ChaveValor#estrutura-base)
- [Build](https://github.com/LincolnCFCruz/BD-ChaveValorr#build)
- [CMD](https://github.com/LincolnCFCruz/BD-ChaveValor#cmd)

---

## Desenvolvedores:
- Gabriel França Oliveira | [@GabrielFOliveira](https://github.com/GabrielFOliveira)
- Milleny Teixeira de Souza | [@millenyteixeira](https://github.com/millenyteixeira)
- Rafael Felipe Silva Pereira | [@rfspereir](https://github.com/rfspereir)
- Lincoln Corrêa Figueiredo Cruz | [LincolnCFCruz](https://github.com/LincolnCFCruz)

---

## Professor:
- Prof. Pedro Henrique Penna

---

## Definição do Problema 

---

## Estrutura Base

---


## CMD
``simpledb [cmd]``

-  ``--insert=<sort-key,value>``

	Insere um objeto no banco de dados. A chave de ordenação (sort-key) é um inteiro positivo. O objeto é codificado em uma cadeia de caracteres pela aplicação cliente. Ao concluir a operação, a chave do objeto inserido é impresso na saída padrão. Objetos são gravados no arquivo ``simpledb.db``.
-  ``--remove=<key>``

  	Remove do banco de dados o objeto que é identificado pela chave key. Objetos são removidos do arquivo simpledb.db.
-  ``--search=<key>``

  	Busca no banco de dados objeto o que é identificado pela chave key. Caso o objeto seja encontrado, o objeto (codificado por uma cadeia de caracteres) e sua chave de ordenação são impressos na saída padrão. Objetos são buscados no arquivo ``simple.db``.
-  ``--update=<key,sort-key,value>``

  	Atualiza o objeto que é identificado pela chave key. A chave de ordenação (sort-key)
é um inteiro positivo. O objeto é codificado em uma cadeia de caracteres pela aplicação cliente. Objetos são buscados e alterados no arquivo ``simple.db``.
-  ``--list=<expr> | --reverse-list=<expr>``

  	Lista em ordem crescente (``--list``) ou em ordem decrescente (``--reverse-list``)
todos os objetos que possam uma chave de ordenação que atenda aos critérios especificados pela expressão ``expr``. Objetos são buscados no arquivo ``simple.db``. A
expressão expr deve aceitar qualquer operação lógica simples envolvendo a chave:
    - ``key>X``: objetos que possuem chave de ordenação maior que ``X``.
    - ``key<X``: objetos que possuem chave de ordenação menor que ``X``.
    - ``key=X``: objetos que possuem chave ordenação igual a ``X``.
    - ``key>=X``: objetos que possuem chave de ordenação maior ou igual que ``X``.
    - ``key<=X``: objetos que possuem chave de ordenação menor ou igual que ``X``.
-  ``--compress=[huffman|lzw]``

  	Compacta os registros do banco de dados usando o algoritmo de Codificação de Huffman ou o Algoritmo de Compressão LZW. O banco de dados compactado é salvo em
um arquivo nomeado ``simpledb.[huffman|lzw]``. Objetos a serem compactados
são lidos do arquivo simple.db.
-  ``--decompress=[huffman|lzw]``

  	Descompacta os registros do banco de dados usando o algoritmo de Codificação de
Huffman ou o Algoritmo de Compressão LZW. O banco de dados descompactado é
lido de um arquivo nomeado ``simpledb.[huffman|lzw]``. Objetos descompactados
são escritos no arquivo ``simple.db``
    




## Referência
[Canvas PUC Minas](https://pucminas.instructure.com/courses/82665/files/4326007?module_item_id=1771382)
