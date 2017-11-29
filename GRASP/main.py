#!/usr/bin/env python
# coding: utf-8

"""
Implementação da metaheurística GRASP para encontrar o Maximal Independent Subset de um grafo

Desenvolvido por: Anderson Bezerra Ribeiro
Data: 28/11/2017
"""

import random

def grasp(dataset):
	solucaoTemp = []
	candidatos = [x for x in sortedVertices]
	while candidatos:
		if not solucaoTemp:
			solucaoTemp.append(candidatos[0])
		else:
			solucaoTemp.append(random.choice(candidatos))
		candidatos = [x for x in range(numVertices)]
		for i in solucaoTemp:
			for j in range(numVertices):
				if(dataset[i][j] == 1 and j in candidatos):
					candidatos.remove(j)
	return solucaoTemp


if __name__ == "__main__":
	solucao = []
	entrada = [[1,1,0,0,0,0,0],[1,1,0,0,1,0,0],[0,0,1,1,0,0,0],[0,0,1,1,1,1,1],[0,1,0,1,1,0,0],[0,0,0,1,0,1,1],[0,0,0,1,0,1,1]]
	numVertices = len(entrada)
	grauVertices = [0 for x in range(numVertices)]
	for i in range(numVertices):
		for j in range(numVertices):
			if(i != j and entrada[i][j] == 1):
				grauVertices[j] += 1
	vertices = dict(zip([x for x in range(numVertices)], grauVertices))
	sortedVertices = sorted(vertices, key=vertices.get)
	it = 0
	while(it < 100):
		solucaoGrasp = grasp(entrada)
		if len(solucaoGrasp) > len(solucao):
			solucao = solucaoGrasp
		it += 1
	print(solucao)