package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializacao {
	
	public static void gravarObjeto(File file, Object object) {
		try {
			FileOutputStream fout = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(object);
			oos.close();
			System.out.println(String.format(
				"Objeto salvo com sucesso no arquivo %s",
				file.getCanonicalFile())
			);
		} catch (FileNotFoundException e) {
			System.out.println("Ocorreu um erro ao gravar o objeto");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao gravar o objeto");
			e.printStackTrace();
		}
	}
	
	public static Object recuperarObjeto(File file) {
		try {
			FileInputStream fin = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fin);
			Object object = ois.readObject();
			ois.close();
			System.out.println(String.format(
					"Objeto recuperado com sucesso do arquivo %s",
					file.getCanonicalFile())
			);
			
			return object;		
		} catch (FileNotFoundException e) {
			System.out.println("Ocorreu um erro ao recuperar o objeto");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao recuperar o objeto");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Ocorreu um erro ao recuperar o objeto");
			e.printStackTrace();
		}
		return null;
	}
	
}
