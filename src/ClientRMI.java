import Interfaces.AnnuaireInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientRMI {
    private String hostname;
    private AnnuaireInterface annuaire;

    public ClientRMI(String hostname) {
         this.hostname = hostname;
        try {
            Registry registre = LocateRegistry.getRegistry(this.hostname);
            this.annuaire = (AnnuaireInterface) registre.lookup("annuaire");

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    public AnnuaireInterface getInstance () {
        return this.annuaire;
    }

}
