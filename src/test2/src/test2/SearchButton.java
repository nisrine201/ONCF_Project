package test2.src.test2;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SearchButton extends JButton {
    public SearchButton() {
        // Appeler le constructeur de la classe parent (JButton)
        super("Rechercher");

        // Personnaliser l'apparence du bouton
        setFont(new Font("Arial", Font.BOLD, 16));
        setForeground(Color.WHITE);
        setBackground(new Color(0, 102, 204)); // Couleur bleue
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Ajouter une marge
        setCursor(new Cursor(Cursor.HAND_CURSOR)); // Changer le curseur au survol
        setFocusPainted(false); // Supprimer la mise en évidence du focus

        // Ajouter un écouteur d'événements pour gérer les clics sur le bouton
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Insérer ici la logique de recherche
                System.out.println("Bouton de recherche cliqué !");
            }
        });
    }
}
