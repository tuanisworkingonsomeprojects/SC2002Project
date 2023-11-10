package sce.sc2002.FinalProject;

import javax.swing.text.html.HTMLDocument.Iterator;

public class CampList extends Iterator{

    private Login currentUser;

    public CampList(Login user){
        currentUser = user;
    }
}
