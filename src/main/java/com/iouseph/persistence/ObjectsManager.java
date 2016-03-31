package com.iouseph.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.iouseph.model.User;

public class ObjectsManager {

	/**
	 * Cette methode permet de serialiser un objet de type User
	 * @param user
	 * @return true si la serialisation a ete effectue avec succes, false sinon
	 */
	public boolean SerializeUser(User user) {

		try {

			FileOutputStream fileOut = new FileOutputStream("user." + user.getId() + ".ser");
			ObjectOutputStream out;
			out = new ObjectOutputStream(fileOut);
			out.writeObject(user);
			out.close();
			fileOut.close();
			System.out.println("User has been serialized");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("serialising User");
			System.err.println("serialising User");
			return false;
		}
		return true;
	}
	/**
	 * Cette methode permet de deserialiser un objet de type User et recuperer son contenu
	 * @param userId
	 * @return l'utilisateur
	 */
	public User DeserializeUser(String userId)
	{
		User user=null;
		try {
			FileInputStream fileIn= new FileInputStream("user." + userId + ".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			user=(User) in.readObject();
			in.close();
			fileIn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return user;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return user;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return user;
		}
		System.out.println("User Loaded: ");
		System.out.println("Id: "+user.getId());

		return user;
	}
}
