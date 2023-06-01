package PaqCalc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import misExcepciones.*;


public class Calculadora implements ActionListener {

    private final JTextField t = new JTextField();
    private final JButton[] botones = {new JButton("1"), new JButton("2"), new JButton("3"), new JButton("4"),
            new JButton("5"), new JButton("6"), new JButton("7"), new JButton("8"),
            new JButton("9"), new JButton("0"), new JButton("."), new JButton("+"),
            new JButton("-"), new JButton("*"), new JButton("/"), new JButton("=")
    };
    private final  JFrame f = new JFrame("Calculadora");
    private final JButton bdel = new JButton("Delete");
    private final JButton bclr = new JButton("Clear");
    private final JButton bserie = new JButton("Serie");

    // Desplegable (JComboBox) para seleccionar el tipo de calculadora con dos opciones: "Normal" o "Científica"
    private final JComboBox<String> miCombo = new JComboBox<>(new String[]{"Normal", "Científica"});


    private double a;
    private double b;
    private double resultado;
    private int operador;


    public Calculadora() {

        a = b = resultado = operador = 0;


        t.setBounds(30, 40, 280, 30);
        f.add(t);
        int indice = 0;
        for (int x = 40; x <= 250; x = x + 70) {
            for (int y = 100; y <= 310; y = y + 70) {
                botones[indice].setBounds(x, y, 50, 40);
                f.add(botones[indice]);
                indice++;
            }
        }

        bdel.setBounds(60, 380, 100, 40);
        bclr.setBounds(180, 380, 100, 40);
        bserie.setBounds(60, 420, 100, 40);


        f.add(bdel);
        f.add(bclr);
        f.add(bserie);
        bserie.setVisible(false);
        bserie.addActionListener(new Listener());




        f.setLayout(null);
        f.setVisible(true);
        f.setSize(350, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        Image icon = Toolkit.getDefaultToolkit().getImage("icon.png");
        f.setIconImage(icon);
        f.setLocationRelativeTo(null); //se posiciona en el centro de la pantalla


        for (JButton b : botones) {

            b.addActionListener(this);
        }

        bdel.addActionListener(this);
        bclr.addActionListener(this);




        t.setFocusable(true);
        t.requestFocus();

        //EXAMEN
        miCombo.setBounds(20,10,100,30);
        f.add(miCombo);
        miCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(miCombo.getSelectedIndex()==0){
                    bserie.setVisible(false);
                }else{
                    bserie.setVisible(true);
                }
            }
        });
    }

    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i <= 10; i++)
            if (e.getSource() == botones[i]) {
                t.setText(t.getText().concat(botones[i].getText()));
                break;
            }
        try {
            if (e.getSource() == botones[11]) {
                a = Double.parseDouble(t.getText());
                operador = 1;
                t.setText("");
            } else if (e.getSource() == botones[12]) {
                a = Double.parseDouble(t.getText());
                operador = 2;
                t.setText("");
            } else if (e.getSource() == botones[13]) {
                a = Double.parseDouble(t.getText());
                operador = 3;
                t.setText("");
            } else if (e.getSource() == botones[14]) {
                a = Double.parseDouble(t.getText());
                operador = 4;
                t.setText("");
            } else if (e.getSource() == botones[15]) {
                try {
                    Operar();
                } catch (NANExcepion x) {
                    t.setText("Resultado Indefinido");
                }

            } else if (e.getSource() == bclr)
                t.setText("");
            else if (e.getSource() == bdel) {
                StringBuilder s = new StringBuilder(t.getText());
                try {
                    t.setText(s.deleteCharAt(s.length() - 1).toString());
                } catch (StringIndexOutOfBoundsException x) {
                    // No se hace nada
                }
            }

        } catch (NumberFormatException x) {
            t.setText("");
        }

        t.requestFocus();

    }

    private void Operar() throws NANExcepion {
        try {
            b = Double.parseDouble(t.getText());
        } catch (NumberFormatException x) {
            t.setText("");
            return;
        }

        switch (operador) {
            case 1:
                resultado = a + b;
                break;
            case 2:
                resultado = a - b;
                break;
            case 3:
                resultado = a * b;
                break;
            case 4:
                resultado = a / b;
                if (Double.isNaN(resultado)) throw new NANExcepion();
                break;
            default:
                resultado = 0;
        }
        t.setText(String.valueOf(resultado));
    }


        private class Listener implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                try{
                    t.setText(dameSerie(Integer.parseInt(t.getText())));
                }catch(NumberFormatException ex){
                    t.setText("error");
                }
            }
        }

        private String dameSerie(int n){

            return"Prueba: "+n;
        }
   }






