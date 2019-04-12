package fr.eni.clinique.bll.factory;

import fr.eni.clinique.bll.manager.LoginMger;
import fr.eni.clinique.bll.manager.impl.LoginMgerImpl;

public class ManagerFactory {
	public static LoginMger loginMger() {
        return LoginMgerImpl.getInstance();
    }
}
