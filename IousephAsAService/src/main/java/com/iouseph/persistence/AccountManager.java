package com.iouseph.persistence;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.iouseph.model.User;

public class AccountManager {
	private static AccountManager Instance= new AccountManager();

	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	// pour permettre un retracage facile la cle sera le username
	private Map<String,User> usersInformations=new HashMap<String,User>();
	/**
	 * Constructeur par defaut
	 * Le constructeur se charge a sa creation de charger les informations sur les utilisateurs si deja cree
	 */
	private AccountManager()
	{
		factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.loadAccountsInformations();
	}

	public static AccountManager getInstance()
	{
		return Instance;
	}

	/**
	 * Verfie si le fichier contenant les informations des utilisateurs existe
	 * @return
	 */
	private boolean AccountsFileExists()
	{
		URL location = AccountManager.class.getProtectionDomain().getCodeSource().getLocation();
		String path=location.getPath();
		return new File(path, "accounts.xml").exists();
	}
	/**
	 * La methode charge les informations des utilisateurs dans la map.
	 */
	// cela est fait independaement pour ne pas avoir a charger a chaque fois que l'utilisateur essaie un username
	public void loadAccountsInformations()
	{
		if(!this.AccountsFileExists())
		{
			return ;
		}
		// TODO a reverifier

		try {
			URL location = AccountManager.class.getProtectionDomain().getCodeSource().getLocation();
			String path=location.getPath();
			File fXmlFile = new File(path+"accounts.xml");
			Document doc = builder.parse(fXmlFile);
			NodeList nList= doc.getElementsByTagName("User");
			for(int i=0;i<nList.getLength();i++)
			{
				Node node=nList.item(i);

				if(node.getNodeType()==Node.ELEMENT_NODE)
				{
					User user=new User();
					Element elem = (Element) node;
					user.setId(elem.getAttribute("id"));
					user.setUsername(elem.getAttribute("username"));
					user.setPassword(elem.getAttribute("password"));
					usersInformations.put(user.getUsername(), user);
				}
			}
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * Methode qui permet l'authentification
	 * @param Username
	 * @param password
	 * @return id si les informations sont valides, null sinon
	 */
	public String Authentification(String Username, String password)
	{
		if(usersInformations.containsKey(Username))
		{
			if(usersInformations.get(Username).getPassword().contentEquals(password))
			{
				return usersInformations.get(Username).getId();
			}
		}

		return null;
	}
	/**
	 * methode permetant a un nouvel utilisateur de s'enregistrer
	 * @param username
	 * @param password
	 * @return
	 */
	public User Enregistrement(String username, String password)
	{
		if(usersInformations.containsKey(username))
			return null;

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		usersInformations.put(username, user);
		this.saveAccountsInformations();
		return user;
	}
	/**
	 * methode permettant d'enregistrer les informations en format XML
	 */
	public void saveAccountsInformations()
	{
		if(usersInformations.size()==0)
			return;

		Document doc = builder.newDocument();
		Element rootElement = doc.createElement("Accounts");
		for (Map.Entry<String, User> user : usersInformations.entrySet())
		{
			Element elem = doc.createElement("User");

			rootElement.appendChild(elem);
			elem.setAttribute("id", user.getValue().getId());
			elem.setAttribute("username", user.getValue().getUsername());
			elem.setAttribute("password", user.getValue().getPassword());
		}
		doc.appendChild(rootElement);
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			URL location = AccountManager.class.getProtectionDomain().getCodeSource().getLocation();
			String path=location.getPath();
			System.out.println(path);
			StreamResult result = new StreamResult(new File(path+"accounts.xml"));
			transformer.transform(source, result);
			System.out.println("File saved!");
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * Cette methode recupere l'utilisateur selon son id
	 * @param idUser
	 * @return user si existe , null sinon
	 */
	public User getUser(String idUser)
	{
		return this.usersInformations.get(idUser);
	}


}


