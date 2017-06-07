package algoritmos;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VizinhoMaisProximo {

	public static void main(String[] args) throws IOException{
		Scanner reader = new Scanner(System.in);
		List<String> fileLines = new ArrayList<String>();
		
		fileLines = readFile(reader, fileLines);
		
		int n = getN(fileLines);
		int type = getType(fileLines);
		
		/*System.out.println(n);
		System.out.println(type);
		for(String line: fileLines){
			System.out.println(line);
		}*/
		
		reader.close();
		
		if(type == 1)
			type1(fileLines, n);
		else if(type == 2)
			type2(fileLines, n);
		else if(type == 3)
			type3(fileLines, n);
	}

	private static List<String> readFile(Scanner reader, List<String> fileLines) throws IOException{
		int maxTries = 3, i = 0;
		while(true){
			try{
				System.out.print("Digite o nome do arquivo: ");
				String filename = reader.nextLine();
				if(filename.endsWith(".txt"))
					filename = filename.substring(0, filename.length()-4);
				fileLines = Files.readAllLines(Paths.get(".\\src\\testes\\" + filename.toLowerCase() + ".txt"), Charset.defaultCharset());
				break;
			}catch(IOException e){
				if(i < maxTries-1){
					System.out.println("--Arquivo n�o existe. Tentativas restantes (" + (maxTries - ++i) + ")--");
					
				}
				else{
					System.err.println("\nM�ximo de tentativas excedido. Reinicie o programa.\n");
					throw e;
				}
			}
		}
		int lineIndex = 0;
		for(String line : fileLines)
			fileLines.set(lineIndex, fileLines.get(lineIndex++).trim().replaceAll(" +", " "));
		return fileLines;
	}

	private static int getN(List<String> fileLines) {
		int indexNStart = fileLines.get(0).indexOf("N=")+2;
		int indexNEnd = fileLines.get(0).indexOf(" ", indexNStart);
		int n = Integer.parseInt((fileLines.get(0).substring(indexNStart, indexNEnd)));
		return n;
	}

	private static int getType(List<String> fileLines) {
		int indexTipoStart = fileLines.get(0).indexOf("Tipo=")+5;
		int indexTipoEnd = fileLines.get(0).indexOf(" ", indexTipoStart);
		int type = Integer.parseInt((fileLines.get(0).substring(indexTipoStart, indexTipoEnd)));
		return type;
	}

	private static Double DistanciaEntrePontos(Double[] coord1, Double[] coord2) {
		return Math.sqrt(Math.pow(coord2[0]-coord1[0], 2.0) + Math.pow(coord2[1]-coord1[1], 2.0));
	}

	private static void type1(List<String> fileLines, int n) {
		List<Double[]> custos = new ArrayList<Double[]>();
		Double[] line = new Double[n];
		
		for(int i = 1; i < n; i++){
			int j = 0;
			while(i > j + 1)
				line[j] = custos.get(j++)[i-1];
			line[j++] = 0.0;
			for(String value: fileLines.get(i).split(" ")){
				line[j++] = Double.parseDouble(value);
			}
			custos.add(line.clone());
		}
		for(int j = 0; j < n-1; j++)
			line[j] = custos.get(j)[n-1];
		line[n-1] = 0.0;
		custos.add(line.clone());
		
		// TESTE - APAGAR DEPOIS
		for(int y = 0; y < n; y++){
			for(Double num: custos.get(y))
				System.out.print(num.toString() + " ");
			System.out.println();
		}
	}

	private static void type2(List<String> fileLines, int n) {
		List<Double[]> custos = new ArrayList<Double[]>();
		List<Double[]> coordenadas = new ArrayList<Double[]>();
		
		for(int i = 1; i <= n; i++){
			Double[] line = new Double[2];
			int j = 0;
			for(String value: fileLines.get(i).split(" ")){
				line[j++] = Double.parseDouble(value);
			}
			coordenadas.add(line.clone());
		}
		
		for(int i = 0; i < n; i++){
			int j = 0;
			Double[] line = new Double[n];
			while(i > j + 1)
				line[j] = custos.get(j++)[i-1];
			line[j++] = 0.0;
			while(j < n)
				line[j] = DistanciaEntrePontos(coordenadas.get(i), coordenadas.get(j++));
			custos.add(line.clone());
		}
		
		// TESTE - APAGAR DEPOIS
		for(int y = 0; y < n; y++){
			for(Double num: custos.get(y))
				System.out.print(num.toString() + " ");
			System.out.println();
		}
	}

	private static void type3(List<String> fileLines, int n) {
		List<Double[]> custos = new ArrayList<Double[]>();
		Double[] line = new Double[n];
		
		for(int i = 1; i <= n; i++){
			int j = 0;
			for(String value: fileLines.get(i).split(" "))
				line[j++] = Double.parseDouble(value);
			custos.add(line.clone());
		}
		
		// TESTE - APAGAR DEPOIS
		for(int y = 0; y < n; y++){
			for(Double num: custos.get(y))
				System.out.print(num.toString() + " ");
			System.out.println();
		}
	}

}
