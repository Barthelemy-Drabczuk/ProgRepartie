public class Main {

    public static void main(String[] args) {
        ClientTcpEcho monClient = new ClientTcpEcho("10.203.9.145", 50007);
        //monClient.run();
        //ServeurTcpEcho salut = new ServeurTcpEcho (50007);
        //salut.run(5);
        //ServeurEchoThreade toto = new ServeurEchoThreade(50007);
        //toto.run(5);
        //ServeurTcpEchoPool maPiscine = new ServeurTcpEchoPool(5, 50007, 2);
        //maPiscine.run();
        //ClientSMTP monClientSympa = new ClientSMTP("pluton.aix.univ-amu.fr", 25, "drabczuk");
        //monClientSympa.sendMail("anonymousseEnCocholat", "prat", "test", "Salut, \n est ce que ça te dis un petit date demain ?\n");

        ClientPOP3 monClientPOP = new ClientPOP3("pluton.aix.univ-amu.fr", "drabczuk", "drabczuk");
        monClientPOP.readMailList();
        monClientPOP.readMail(3175);
    }
}
