#include <stdio.h>
#include <stdlib.h>
#include "Huffman.c"
#include "LZW.c"
#include "select.c"
#include "inter_balanceada.c"


typedef struct exemplo
{
	int v; 
	struct exemplo *prox;
} t_exemplo;

t_exemplo *cabeca = NULL;
t_exemplo *corrente = NULL;

t_exemplo * criar_lista(int valor)
{
	t_exemplo * p = (t_exemplo*)malloc(sizeof(t_exemplo));

	if(p == NULL)
	{
		printf("\nFalha ao alocar memoria\n");
		return NULL;
	}

	p->v = valor;
	p->prox = NULL;
	cabeca = corrente = p;
	return p;
}

t_exemplo* adicionar_lista(int v)
{
	if(cabeca == NULL)
	{
		return criar_lista(v);
	}

	t_exemplo * p = (t_exemplo*)malloc(sizeof(t_exemplo));

	if(p == NULL)
	{
		printf("\nFalha ao alocar memoria\n");
		return NULL;
	}

	p->v = v;
	p->prox = NULL;

	corrente->prox = p;
	corrente = p;

	return p;
}

void imprimir_lista()
{
	t_exemplo *aux = cabeca;

	while(aux != NULL)
	{
		printf("%d\n", aux->v);
		aux = aux->prox;
	}
}

int lista_vazia()
{
	if(cabeca == NULL)
		return 1;
	return 0;
}

t_exemplo * buscar_elemento(int v, t_exemplo **ant)
{

	if(lista_vazia() == 1)
		return NULL;
	t_exemplo *p = cabeca;
	t_exemplo *aux_ant = NULL;
	int achou = 0;

	while(p != NULL)
	{
		if(p->v == v)
		{
			achou = 1;
			break;
		}
		aux_ant = p;
		p = p->prox;
	}

	if(achou == 1)
	{
		if(ant)
			*ant = aux_ant;
		return p;
	}
	return NULL;
}

int remover_elemento(int v)
{
	t_exemplo *ant = NULL;

	t_exemplo * elem = buscar_elemento(v, &ant);

	if(elem == NULL)
		return 0;

	if(ant != NULL)
		ant->prox = elem->prox;

	if(elem == corrente)
		corrente = ant;

	if(elem == cabeca)
		cabeca = elem->prox;

	free(elem);
	elem = NULL;

	return 1;
}

int tamanho_lista()
{
	if(lista_vazia())
		return 0;

	t_exemplo *aux = cabeca;
	int tam = 0;

	while(aux != NULL)
	{
		tam++;
		aux = aux->prox;
	}
	return tam;
}

int main(int argc, char * argv[])
{
	if(lista_vazia() == 0)
		printf("A lista NAO esta vazia!!\n\n");
	else
		printf("Lista vazia!!\n\n");
	
	printf("Tamanho da lista: %d\n\n", tamanho_lista());

	printf("Criando uma lista...\n\n");
	criar_lista(10);

	printf("Adicionando elementos na lista...\n\n");
	adicionar_lista(20);
	adicionar_lista(30);
	adicionar_lista(40);
	adicionar_lista(50);

	printf("Imprimindo os elementos da lista:\n");
	imprimir_lista();

	if(lista_vazia() == 0)
		printf("\nA lista NAO esta vazia!!\n");
	else
		printf("\nLista vazia!!\n");
	
	printf("\nTamanho da lista: %d\n", tamanho_lista());

	if(buscar_elemento(10, NULL) != NULL)
		printf("\nElemento 10 encontrado!\n");
	else
		printf("\nElemento 10 NAO encontrado.\n");

	if(buscar_elemento(100, NULL) != NULL)
		printf("\nElemento 100 encontrado!\n");
	else
		printf("\nElemento 100 NAO encontrado.\n");

	if(remover_elemento(30))
		printf("\nElemento 30 removido com sucesso!\n");
	else
		printf("\nNAO foi possivel remover o elemento 30.\n");

	printf("\nImprimindo os elementos da lista:\n");
	imprimir_lista();
	
	printf("\nTamanho da lista: %d\n", tamanho_lista());

	return 0;
}