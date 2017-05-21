package algoritmos;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class VizinhoMaisProximo {

	public static void main(String[] args) throws IOException{
		Scanner reader = new Scanner(System.in);
		List<String> fileLines = null;
		
		fileLines = readFile(reader, fileLines);
		
		int n = getN(fileLines);
		int type = getType(fileLines);
		
		/*System.out.println(n);
		System.out.println(type);
		for(String line: fileLines){
			System.out.println(line);
		}*/
		
		reader.close();
	}

	private static int getType(List<String> fileLines) {
		int indexTipoStart = fileLines.get(0).indexOf("Tipo=")+5;
		int indexTipoEnd = fileLines.get(0).indexOf(" ", indexTipoStart);
		int type = Integer.parseInt((fileLines.get(0).substring(indexTipoStart, indexTipoEnd)));
		return type;
	}

	private static int getN(List<String> fileLines) {
		int indexNStart = fileLines.get(0).indexOf("N=")+2;
		int indexNEnd = fileLines.get(0).indexOf(" ", indexNStart);
		int n = Integer.parseInt((fileLines.get(0).substring(indexNStart, indexNEnd)));
		return n;
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
					System.out.println("--Arquivo não existe. Tentativas restantes (" + (maxTries - ++i) + ")--");
					
				}
				else{
					System.err.println("\nMáximo de tentativas excedido. Reinicie o programa.\n");
					throw e;
				}
			}
		}
		int lineIndex = 0;
		for(String line : fileLines){
			fileLines.set(lineIndex, fileLines.get(lineIndex).replaceAll(" +", " "));
			lineIndex++;
		}
		return fileLines;
	}

}
